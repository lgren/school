package com.lgren.school.student.service;

import com.lgren.common.CResult;

import java.util.List;
import java.util.Map;

public interface IActivitiCore {
    CResult<List<Map>> queryTaskList(Integer isHistory);
    CResult<Map> queryTaskByTaskId(String taskId);
    CResult<Map> queryTaskByAssignee(String assignee);
    CResult completeTaskByTaskId(String taskId, Map<String,Object> variables);
    CResult queryProcessList();
    CResult queryDeployList();

    CResult startProcess(String processKey);
    CResult deployment(String deployName);
}
