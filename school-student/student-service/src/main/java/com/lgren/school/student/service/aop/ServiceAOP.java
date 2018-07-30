package com.lgren.school.student.service.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.Map;

@Aspect
@Component
public class ServiceAOP {
//    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.lgren.school.student.service..*(..))")
    public void paramValidateArea() {
    }

    //    @Pointcut(value = "execution(* com.lgren.school.student.service.serviceImpl..*(..)) && @annotation(notNullTest)",argNames = "joinPoint,notNullTest")
    //    public void allParamNotNull(JoinPoint joinPoint,NotNullTest notNullTest) {}
    @Pointcut(value = "execution(* com.lgren.school.student.service.serviceImpl..aopTest(..))")
    public void allParamNotNull() {
    }

    /**
     * 判断是否为基本类型：包括String
     *
     * @param clazz clazz
     * @return true：是;   false：不是
     */
    private boolean isPrimite(Class<?> clazz) {
        return clazz.isPrimitive() || clazz == String.class;
    }

    @Before("allParamNotNull()")
    public void notNullValidate(JoinPoint joinPoint) throws NoSuchMethodException {
        Object[] params = joinPoint.getArgs();// 得到所有参数
        Method targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();// 得到方法对象
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(targetMethod.getName(), targetMethod.getParameterTypes());//获取真实方法对象
        //region 方法体上的注解判断
        boolean isMethodNotNull ;// 如果方法有@NotNullTest注解 给所有参数加上
        boolean isMethodNotBlank;// 如果方法有@NotBlankTest注解 给所有参数加上
        boolean isMethodNotEmpty;// 如果方法有@NotEmptyTest注解 给所有参数加上
        NotNullTest notNull = realMethod.getAnnotation(NotNullTest.class);
        NotBlankTest notBlank = realMethod.getAnnotation(NotBlankTest.class);
        NotEmptyTest notEmpty = realMethod.getAnnotation(NotEmptyTest.class);
        isMethodNotNull = notNull != null;
        isMethodNotBlank = notBlank != null;
        isMethodNotEmpty = notEmpty != null;
        boolean isAllParamOpen = isMethodNotNull || isMethodNotBlank || isMethodNotEmpty;// 是否开启所有参数的认证
        //endregion
        //region 参数判断主体
        for (int i = 0; i < realMethod.getParameters().length; i++) {
            Parameter parameter = realMethod.getParameters()[i];
            if (ArrayUtils.isNotEmpty(parameter.getAnnotations()) || isAllParamOpen) {//判断参数是否包含注解
                Object param = params[i];
                //region 如果有@NotNullTest注解
                if (parameter.getAnnotation(NotNullTest.class) != null || isMethodNotNull) {//如果有@NotNullTest注解
                    if (param == null) {
                        System.out.println("参数不能为空");
                        continue;
                    }
                }//endregion
                //region 如果有@NotBlankTest注解
                if (parameter.getAnnotation(NotBlankTest.class) != null || isMethodNotBlank) {//如果有@NotBlankTest注解
                    if (param == null) {
                        System.out.println("参数不能为空");
                        continue;
                    } else if(param instanceof String) {
                        if (StringUtils.isBlank((String) param)) {
                            System.out.println("参数不能为空");
                            continue;
                        }
                    } else {
                        System.out.println("@NotBlankTest修饰了非String");
                    }
                }//endregion
                //region 如果有@NotEmptyTest注解
                if (parameter.getAnnotation(NotEmptyTest.class) != null || isMethodNotEmpty) {//如果有@NotEmptyTest注解
                    if (param == null) {
                        System.out.println("参数不能为空");
                        continue;
                    } else {
                        if (param instanceof Map) {
                            if (CollectionUtils.isEmpty((Map) param)) {
                                System.out.println("集合内部为空");
                            }
                        } else if (param instanceof Collection) {
                            if (CollectionUtils.isEmpty((Collection<?>) param)) {
                                System.out.println("集合内部为空");
                            }
                        } else {
                            System.out.println("@NotEmptyTest修饰了非Map 或者 Collection");
                        }
                    }
                }//endregion
            }
        }//endregion
    }
}
