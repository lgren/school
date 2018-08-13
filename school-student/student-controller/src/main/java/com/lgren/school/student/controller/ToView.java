package com.lgren.school.student.controller;

import com.lgren.common.CResult;
import com.lgren.school.student.pojo.Student;
import com.lgren.school.student.service.IActivitiCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Controller
public class ToView {
    @Autowired
    private IActivitiCore activitiCore;

    @GetMapping(value = "toActivitiTest")
    public String toActivitiTest(Map map) {

        map.put("testttt", "tescfdsf");
        return "activitiTest";
    }

    @GetMapping(value = "toTask/{assigneeOrTaskIdOrIsHistory}")
    public String toTask(Map map, @PathVariable("assigneeOrTaskIdOrIsHistory") String assigneeOrTaskIdOrIsHistory) {
        if (assigneeOrTaskIdOrIsHistory.toLowerCase().equals("yes")) {
            map.put("taskList", activitiCore.queryTaskList(1));
            return "task";
        }
        if (assigneeOrTaskIdOrIsHistory.toLowerCase().equals("no")) {
            map.put("taskList", activitiCore.queryTaskList(0));
            return "task";
        }
            CResult<Map> serviceResult = activitiCore.queryTaskByTaskId(assigneeOrTaskIdOrIsHistory);
        if (!serviceResult.isSuccess()) {
            serviceResult = activitiCore.queryTaskByAssignee(assigneeOrTaskIdOrIsHistory);
        }
        map.put("task", serviceResult);
        return "task";
    }

    @GetMapping(value = "toTask")
    public String toTaskList(Map map) {
        map.put("taskList", activitiCore.queryTaskList(0));
        return "task";
    }

    @GetMapping(value = "toInsertStudent")
    public String toInsertStudent(Map<String, Student>map) {
        map.put("student", new Student(1L, "tesd", "sdfsdt", "stdsd" , new Date()));
        return "insertStudent";
    }
}
