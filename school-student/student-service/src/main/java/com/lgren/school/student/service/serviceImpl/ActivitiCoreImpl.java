package com.lgren.school.student.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.lgren.common.vo.CommonResult;
import com.lgren.school.student.service.IActivitiCore;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public CommonResult queryTaskList() {
//        JSONArray jsonArray = new JSONArray();
        List list = new ArrayList();
        List<Task> taskList = taskService.createTaskQuery().list();
        taskList.forEach(task -> {
            Map<String, Object> map = new HashMap<>();
            Map<String, Object> taskMap = new HashMap<>();
            taskMap.put("id", task.getId());
            taskMap.put("name", task.getName());
            taskMap.put("assignee", task.getAssignee());
            taskMap.put("createTime", task.getCreateTime());
            taskMap.put("processInstanceId", task.getProcessInstanceId());
            taskMap.put("executionId", task.getExecutionId());
            taskMap.put("processDefinitionId", task.getProcessDefinitionId());
            map.put("task", taskMap);
            map.put("variables", taskService.getVariables(task.getId()));
            list.add(map);
        });
        return new CommonResult(list);
    }

    @Override
    public CommonResult queryTaskByTaskId(String taskId) {
        return new CommonResult<Task>(taskService.createTaskQuery().taskId(taskId).singleResult());
    }

    @Override
    public CommonResult queryTaskByAssignee(String assignee) {
        return new CommonResult<Task>(taskService.createTaskQuery().taskAssignee(assignee).singleResult());
    }

    @Override
    public CommonResult completeTaskByTaskId(String taskId, Map<String, Object> variables) {
        try {
            taskService.complete(taskId,variables);
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResult(false,"完成提交失败");
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
        return new CommonResult(true,"启动成功");
    }

    @Override
    public CommonResult deployment(String deployName) {
        repositoryService.createDeployment().name(deployName)
                .addClasspathResource("diagrams/leave/leaveComplexOne.bpmn").addClasspathResource("diagrams/leave/leaveComplexOne.png").deploy();
        return new CommonResult(true,"部署成功");
    }


//    public CommonResult deployment(String deployZipResource) {
//        CommonResult result = new CommonResult();
//        InputStream in = this.getClass().getClassLoader().getResourceAsStream(deployZipResource);
//
//        return result;
//    }
}
