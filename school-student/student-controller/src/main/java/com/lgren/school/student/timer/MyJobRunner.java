package com.lgren.school.student.timer;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;

public class MyJobRunner implements JobRunner{
    @Autowired
    private MyTaskService myTaskService;

    @Override
    public Result run(JobContext jobContext) throws Throwable {
        Job job = jobContext.getJob();
        String method = job.getParam("method");
        if (StringUtils.isEmpty(method)) {
            return new Result(Action.EXECUTE_FAILED, "params method is null");
        }
        for (String m : StringUtils.split(method, ",")) {
            try {
                Method taskMethod = myTaskService.getClass().getMethod(m);
                taskMethod.invoke(myTaskService);
            } catch (NoSuchMethodException e) {
                try {
                    Method taskMethod = myTaskService.getClass().getMethod(method, Job.class);
                    taskMethod.invoke(myTaskService, job);
                } catch (NoSuchMethodException e1) {
                    return new Result(Action.EXECUTE_FAILED, "method:" + method + " not found");
                }
            }
        }
        return new Result(Action.EXECUTE_SUCCESS);
    }
}
