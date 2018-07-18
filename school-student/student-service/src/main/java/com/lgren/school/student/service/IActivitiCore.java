package com.lgren.school.student.service;

import com.lgren.common.vo.CommonResult;

import java.util.Map;

public interface IActivitiCore {
    CommonResult queryTaskList();
    CommonResult queryTaskByTaskId(String taskId);
    CommonResult queryTaskByAssignee(String assignee);
    CommonResult completeTaskByTaskId(String taskId, Map<String,Object> variables);
    CommonResult queryProcessList();
    CommonResult queryDeployList();

    CommonResult startProcess(String processKey);
    CommonResult deployment(String deployName);
}
