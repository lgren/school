package com.lgren.school.student.service.serviceImpl;

import com.lgren.common.CResult;
import com.lgren.school.student.service.IActivitiCore;
import org.activiti.engine.*;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.apache.commons.lang3.StringUtils;
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

    /**
     * @author Lgren
     * @createDate 2018/8/10 9:26
     * @param isHistory (是否查看历史任务 0:查看当前所任务 1:查看历史所有任务)
     * @return List任务
     */
    @Override
    public CResult<List<Map>> queryTaskList(Integer isHistory) {
        List<Map> list = new ArrayList<>();
        List<TaskInfo> taskList = isHistory == 0 ? taskService.createTaskQuery().list().stream().map(t -> (TaskInfo) t).collect(Collectors.toList())// 获取正在运行的所有任务
                : historyService.createHistoricTaskInstanceQuery().list().stream().map(t -> (TaskInfo) t).collect(Collectors.toList());// 获取历史的所有任务
        if (CollectionUtils.isEmpty(taskList)) {
            return CResult.newFailure("未查找到");
        }
        taskList.forEach(task -> list.add(taskAndVariables(task, isHistory)));
        return CResult.newSuccess(list);
    }

    @Override
    public CResult<Map> queryTaskByTaskId(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            return CResult.newFailure("未查找到");
        }
        return CResult.newSuccess(taskAndVariables(task, 0));
    }

    @Override
    public CResult<Map> queryTaskByAssignee(String assignee) {
        Task task = taskService.createTaskQuery().taskAssignee(assignee).singleResult();
        if (task == null) {
            return CResult.newFailure("未查找到");
        }
        return CResult.newSuccess(taskAndVariables(task, 0));
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
                .forEach(t -> historyMap.put(t.getVariableName(),t.getValue() instanceof Date ? DateFormatUtils.format(task.getCreateTime(), "yyyy-MM-dd HH:mm:ss") : t.getValue()));
        map.put("variables", isHistory == 0 ? taskService.getVariables(task.getId()) : historyMap);
        return map;
    }

    @Override
    public CResult<String> completeTaskByTaskId(String taskId, Map<String, Object> variables) {
        variables.put("message", Objects.equals(variables.get("message"), "重要")? "重要" : "不重要");
        try {
            taskService.complete(taskId, variables);
        } catch (Exception e) {
            e.printStackTrace();
            return CResult.newFailure(9999, "完成提交失败");
        }
        return CResult.<String>newSuccess("提交成功");
    }

    @Override
    public CResult queryProcessList() {
        return CResult.newSuccess(repositoryService.createProcessDefinitionQuery().list());
    }

    @Override
    public CResult queryDeployList() {
        return CResult.newSuccess(repositoryService.createDeploymentQuery().list());
    }

    @Override
    public CResult startProcess(String processKey) {
        try {
            runtimeService.startProcessInstanceByKey(processKey);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return CResult.newSuccess("启动成功");
    }

    @Override
    public CResult deployment(String deployName) {
        repositoryService.createDeployment().name(deployName)
                .addClasspathResource("diagrams/leave/leaveComplexOne.bpmn")
                .addClasspathResource("diagrams/leave/leaveComplexOne.png").deploy();
        return CResult.newSuccess("部署成功");
    }

    @Override
    public String getSuperior(String person) {
        switch (person) {
            case "applicant": return "person1";
            case "person1": return "person2";
            case "person2": return "person3";
            case "person3": return "person4";
            case "person4": return "person5";
            case "person5": return "person6";
            default: return null;
        }
    }

}
