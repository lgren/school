package com.lgren.school.student.service.serviceImpl;

import com.lgren.common.vo.CommonResult;
import com.lgren.school.student.service.IActivitiCore;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ActivitiCoreImpl implements IActivitiCore {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Override
    public CommonResult<List<TaskInfo>> queryTaskList(Integer isHistory) {
        List list = new ArrayList();
        List<TaskInfo> taskList = null;

        taskList = isHistory == 0 ? taskService.createTaskQuery().list().stream().map(t -> (TaskInfo) t).collect(Collectors.toList())
                : historyService.createHistoricTaskInstanceQuery().list().stream().map(t -> (TaskInfo) t).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(taskList)) {
            return new CommonResult<List<TaskInfo>>(false, "未查找到");
        }


        taskList.forEach(task -> list.add(taskAndVariables(task, isHistory)));
        return new CommonResult<List<TaskInfo>>(list);
    }

    @Override
    public CommonResult<Map> queryTaskByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return new CommonResult(false, "未查找到");
        }
        return new CommonResult<Map>(taskAndVariables(task, 0));
    }

    @Override
    public CommonResult<Map> queryTaskByAssignee(String assignee) {
        Task task = taskService.createTaskQuery().taskAssignee(assignee).singleResult();
        if (task == null) {
            return new CommonResult(false, "未查找到");
        }
        return new CommonResult<Map>(taskAndVariables(task, 0));
    }

    private Map taskAndVariables(TaskInfo task, Integer isHistory) {
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("id", task.getId());
        taskMap.put("name", task.getName());
        taskMap.put("assignee", task.getAssignee());
        taskMap.put("createTime", DateFormatUtils.format(task.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        taskMap.put("processInstanceId", task.getProcessInstanceId());
        taskMap.put("executionId", task.getExecutionId());
        taskMap.put("processDefinitionId", task.getProcessDefinitionId());
        map.put("task", taskMap);
        Map historyMap = new HashMap();
        historyService.createHistoricVariableInstanceQuery().processInstanceId(task.getProcessInstanceId()).list()
                .forEach(t -> historyMap.put(t.getVariableName(),t.getValue()));
        map.put("variables", isHistory == 0 ? taskService.getVariables(task.getId()) : historyMap);
        return map;
    }

    @Override
    public CommonResult completeTaskByTaskId(String taskId, Map<String, Object> variables) {
        try {
            taskService.complete(taskId, variables);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(false, "完成提交失败");
        }
        return new CommonResult("完成提交成功");
    }

    @Override
    public CommonResult queryProcessList() {
        return null;
    }

    @Override
    public CommonResult queryDeployList() {
        return null;
    }

    @Override
    public CommonResult startProcess(String processKey) {
        runtimeService.startProcessInstanceByKey(processKey);
        return new CommonResult(true, "启动成功");
    }

    @Override
    public CommonResult deployment(String deployName) {
        repositoryService.createDeployment().name(deployName)
                .addClasspathResource("diagrams/leave/leaveComplexOne.bpmn").addClasspathResource("diagrams/leave/leaveComplexOne.png").deploy();
        return new CommonResult(true, "部署成功");
    }

}
