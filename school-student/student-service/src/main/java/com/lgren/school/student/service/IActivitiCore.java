package com.lgren.school.student.service;

import com.lgren.common.vo.CommonResult;
import org.activiti.engine.task.TaskInfo;

import java.util.List;
import java.util.Map;

public interface IActivitiCore {
    CommonResult<List<TaskInfo>> queryTaskList(Integer isHistory);
    CommonResult<Map> queryTaskByTaskId(String taskId);
    CommonResult<Map> queryTaskByAssignee(String assignee);
    CommonResult completeTaskByTaskId(String taskId, Map<String,Object> variables);
    CommonResult queryProcessList();
    CommonResult queryDeployList();

    CommonResult startProcess(String processKey);
    CommonResult deployment(String deployName);
}
