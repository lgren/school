package com.lgren.school.student.controller;

import com.lgren.common.vo.CommonResult;
import com.lgren.school.student.service.IActivitiCore;
import com.lgren.school.student.service.IStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class StudentController {
    @Autowired
    private IStudentService studentService;
    @Autowired
    private IActivitiCore activitiCore;

    @RequestMapping("index")
    public String index() {
        System.out.println(studentService.selectByPrimaryKey(1L));
        return "index";
    }

    @RequestMapping("selectAllStudent.do")
    public CommonResult selectAllStudent() {
        return new CommonResult(studentService.selectAll());
    }

    @RequestMapping("activitiCore.do")
    public CommonResult activitiCore(String method, String param) {
        switch (StringUtils.isEmpty(method)? "" : method) {
            default:
            case "queryTaskList":return activitiCore.queryTaskList();
            case "queryDeployList":return activitiCore.queryDeployList();
            case "queryProcessList":return activitiCore.queryProcessList();
            case "queryTaskByTaskId":return activitiCore.queryTaskByTaskId(param);
            case "queryTaskByAssignee":return activitiCore.queryTaskByAssignee(param);
            case "startProcess":return activitiCore.startProcess(param);
            case "deployment":return activitiCore.deployment(param);
        }
    }

    @RequestMapping("activitiCoreComplete.do")
    public CommonResult activitiCoreComplete(String taskId, String message, String description) {
        Map<String,Object> variables = new HashMap<>();
        variables.put("createTime", new Date());
        variables.put("message", message);
        variables.put("description", description);
        if (StringUtils.isEmpty(taskId)) { return new CommonResult(0, "参数不能为空"); }
        return activitiCore.completeTaskByTaskId(taskId, variables);
    }

}
