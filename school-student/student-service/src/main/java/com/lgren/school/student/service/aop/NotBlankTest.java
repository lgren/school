package com.lgren.school.student.service.aop;

import java.lang.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
public @interface NotBlankTest {
    /**
     * @Description 反馈类型 0:打印控制台 1:空指针异常 2:返回null 3:返回空返回值 4:如果是 有 成功与否判断 以及数据输出 的包装类 则可以设置对应值返回例如{"isSuccess":false,"errorMessage":"参数错误"}
     * @Author Lgren
     * @Date 2018/7/27 17:58
     */
    int feedbackType() default 1;
    String errorMessage() default "";
}
