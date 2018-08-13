package com.lgren.school.student.timer;

import com.github.ltsopensource.core.domain.Action;
import com.github.ltsopensource.core.domain.Job;
import com.github.ltsopensource.tasktracker.Result;
import com.github.ltsopensource.tasktracker.runner.JobContext;
import com.github.ltsopensource.tasktracker.runner.JobRunner;
import com.google.common.base.Splitter;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.lgren.school.student.common.SpringContextUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
/**
 * @Description 接收 LTS传入的参数 格式可以是{"method":"method1,method2"} OR {"testService":"method1" ,"test1Service":"method1"} 或者结合
 *                  如果使用 method方式需要在此类配置默认类, 结合
 * @Author Lgren
 * @Date 2018/8/7 8:50
 * @Param 
 * @return 
 */
public class MyJobRunner implements JobRunner {
    private String defaultServiceStr = "myTaskService";//默认需要调用的类

    @Override
    public Result run(JobContext jobContext) {
        Job job = jobContext.getJob();//获取LTS传来的Job
        Map<String, String> extParams = job.getExtParams();//获取LTS传来的参数

        Multimap<String, String> serviceAndMethodMap = ArrayListMultimap.create();//需要调用的方法坐标
        //region 获取需要调用的方法坐标
        String methodVar = extParams.get("method");
        if (StringUtils.isNotBlank(methodVar)) {
            List<String> ServiceAndMethodList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(methodVar);
            ServiceAndMethodList.forEach(o -> {
                String[] KV = o.split("\\.");
                if (ArrayUtils.isNotEmpty(KV)) {
                    serviceAndMethodMap.put(KV.length == 1 ? defaultServiceStr : KV[0], KV.length == 1 ? KV[0] : KV[1]);
                }
            });
        }
        extParams.forEach((k, v) -> {
            if (k.contains("Service")) {
                List<String> ServiceMethodList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(v);
                serviceAndMethodMap.putAll(k, ServiceMethodList);
            }
        });
        if (serviceAndMethodMap.isEmpty()) {
            return new Result(Action.EXECUTE_FAILED, "参数method不能为空");
        }
        //endregion
        for (Map.Entry<String, String> entry : serviceAndMethodMap.entries()) {
            Object service = null;
            try {
                service = SpringContextUtil.getBean(entry.getKey());
            } finally {
                if (service == null) {
                    return new Result(Action.EXECUTE_FAILED, "未查询到service:" + entry.getKey());
                }
            }
            Method taskMethod = null;
            try {
                taskMethod = service.getClass().getMethod(entry.getValue());
                taskMethod.invoke(service);
            } catch (NoSuchMethodException e) {
                return new Result(Action.EXECUTE_FAILED, "未找到method:" + entry.getValue());
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return new Result(Action.EXECUTE_SUCCESS);
    }
}
