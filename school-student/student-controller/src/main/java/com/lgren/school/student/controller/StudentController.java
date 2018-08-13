package com.lgren.school.student.controller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lgren.common.CResult;
import com.lgren.school.student.dao.IStudentDao;
import com.lgren.school.student.pojo.Student;
import com.lgren.school.student.service.IActivitiCore;
import com.lgren.school.student.service.IStudentService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class StudentController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Date nowDate = new Date();

    @Autowired
    private IStudentService studentService;
    @Autowired
    private IActivitiCore activitiCore;
    @Autowired
    private IStudentDao studentDao;
    @RequestMapping("index")
    public String index() {
        System.out.println(studentService.selectByPrimaryKey(1L));
        return "index";
    }

    @RequestMapping("selectAllStudent.do")
    public CResult<List<Student>> selectAllStudent() {
        return CResult.newSuccess(studentService.selectAll());
    }

    @RequestMapping("activitiCore.do")
    public CResult activitiCore(String method, String param) {
        switch (StringUtils.isEmpty(method)? "" : method) {
            default:
            case "queryTaskList":return activitiCore.queryTaskList(StringUtils.isBlank(param) ? 0 : Integer.valueOf(param));
            case "queryDeployList":return activitiCore.queryDeployList();
            case "queryProcessList":return activitiCore.queryProcessList();
            case "queryTaskByTaskId":return activitiCore.queryTaskByTaskId(param);
            case "queryTaskByAssignee":return activitiCore.queryTaskByAssignee(param);
            case "startProcess":return activitiCore.startProcess(param);
            case "deployment":return activitiCore.deployment(param);
        }
    }

    @RequestMapping("activitiCoreComplete.do")
    public CResult activitiCoreComplete(String taskId, String message, String description) {
        Map<String,Object> variables = new HashMap<>();
        variables.put("createTime", new Date());
        variables.put("message", message);
        variables.put("description", description);
        if (StringUtils.isEmpty(taskId)) { CResult.newFailure(0, "参数不能为空"); }
        return activitiCore.completeTaskByTaskId(taskId, variables);
    }


    @RequestMapping("taskComplete.do")
    public CResult taskComplete(String taskId, String message, String description, String beginTime, String endTime) {
        Map<String,Object> variables = new HashMap<>();
        variables.put("createTime", new Date());
        if (StringUtils.isNotBlank(message)) {
            variables.put("message", message);
        }
        if (StringUtils.isNotBlank(description)) {
            variables.put("description", description);
        }
        if (StringUtils.isNotBlank(beginTime)) {
            variables.put("beginTime", beginTime);
        }
        if (StringUtils.isNotBlank(endTime)) {
            variables.put("endTime", endTime);
        }
        if (StringUtils.isEmpty(taskId)) {
            return CResult.newFailure(9999, "参数不能为空");
        }
        return activitiCore.completeTaskByTaskId(taskId, variables);
    }

    @GetMapping("aopTest.do")
    public List<Object> aopTest() {
        logger.debug(this.getClass() + "aopTest()");

        List<Integer> list = Lists.newArrayList(1,2,43,2);
        Set<Integer> set = Sets.newHashSet(4,3,6,2);
        Map<Integer, Integer> map = ImmutableMap.of(1,3,2,2,3,1,5,3);
        return (List<Object>) studentService.aopTest(1,"32","43",nowDate,3232L,map,list,set).getData();
    }

    @RequestMapping("insertStudent.do")
    public String insertStudent(Student student) {
        System.out.println(student);
        return "OK";
    }
}
