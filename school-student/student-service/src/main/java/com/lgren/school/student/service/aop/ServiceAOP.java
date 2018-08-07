package com.lgren.school.student.service.aop;

import com.lgren.common.vo.CommonResult;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Collection;
import java.util.HashMap;
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

    @Around("allParamNotNull()")
    public Object notNullValidate(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Object proceed = null;//程序返回值
        CommonResult commonResult = null;
        Object[] params = joinPoint.getArgs();// 得到所有参数
        Method targetMethod = ((MethodSignature) joinPoint.getSignature()).getMethod();// 得到方法对象
        Method realMethod = joinPoint.getTarget().getClass().getDeclaredMethod(targetMethod.getName(), targetMethod.getParameterTypes());//获取真实方法对象
        int type = 0;//0:正常 1:参数为空 2:集合内部为空
        int number = 0;
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
        int feedbackType = 0;
        String returnMsg = null;
        boolean isAllParamOpen = isMethodNotNull || isMethodNotBlank || isMethodNotEmpty;// 是否开启所有参数的认证
        //endregion
        try {
            //region 参数判断主体
            for (int i = 0; i < realMethod.getParameters().length; i++) {
                number = i + 1;
                Parameter parameter = realMethod.getParameters()[i];
                if (ArrayUtils.isNotEmpty(parameter.getAnnotations()) || isAllParamOpen) {//判断参数是否包含注解
                    Object param = params[i];
                    //region 如果有@NotNullTest注解
                    NotNullTest notNullParam = parameter.getAnnotation(NotNullTest.class);
                    if (notNullParam != null || isMethodNotNull) {//如果有@NotNullTest注解
                        if (param == null) {
                            type = 1;//参数不能为空
                            if (notNullParam != null) {
                                notNull = notNullParam;
                            }
                            break;
                        }
                    }//endregion
                    //region 如果有@NotBlankTest注解
                    NotBlankTest notBlankParam = parameter.getAnnotation(NotBlankTest.class);
                    if (notBlankParam != null || isMethodNotBlank) {//如果有@NotBlankTest注解
                        if (param == null) {
                            type = 1;//参数不能为空
                            if (notBlankParam != null) {
                                notBlank = notBlankParam;
                            }
                            break;
                        } else if(param instanceof String) {
                            if (StringUtils.isBlank((String) param)) {
                                type = 1;//参数不能为空
                                if (notBlankParam != null) {
                                    notBlank = notBlankParam;
                                }
                                break;
                            }
                        } else {
    //                        System.out.println("@NotBlankTest修饰了非String");
                        }
                    }//endregion
                    //region 如果有@NotEmptyTest注解
                    NotEmptyTest notEmptyParam = parameter.getAnnotation(NotEmptyTest.class);
                    if (notEmptyParam != null || isMethodNotEmpty) {//如果有@NotEmptyTest注解
                        if (param == null) {
                            type = 1;//参数不能为空
                            if (notEmptyParam != null) {
                                notEmpty = notEmptyParam;
                            }
                            break;
                        } else {
                            if (param instanceof Map) {
                                if (CollectionUtils.isEmpty((Map) param)) {
                                    type = 2;//集合内部为空
                                    if (notEmptyParam != null) {
                                        notEmpty = notEmptyParam;
                                    }
                                    break;
                                }
                            } else if (param instanceof Collection) {
                                if (CollectionUtils.isEmpty((Collection<?>) param)) {
                                    type = 2;//集合内部为空
                                    if (notEmptyParam != null) {
                                        notEmpty = notEmptyParam;
                                    }
                                    break;
                                }
                            } else {
    //                            System.out.println("@NotEmptyTest修饰了非Map 或者 Collection");
                            }
                        }
                    }//endregion
                }
            }//endregion
            if (notNull != null) {
                if (StringUtils.isBlank(notNull.errorMessage())) {
                    returnMsg = notNull.errorMessage();
                }
                if (feedbackType != 0){
                    feedbackType = notNull.feedbackType();
                }
            } else if (notBlank != null) {
                if (StringUtils.isBlank(notBlank.errorMessage())) {
                    returnMsg = notBlank.errorMessage();
                }
                if (feedbackType != 0) {
                    feedbackType = notBlank.feedbackType();
                }
            } else if (notEmpty != null) {
                if (StringUtils.isBlank(notEmpty.errorMessage())) {
                    returnMsg = notEmpty.errorMessage();
                }
                if (feedbackType != 0) {
                    feedbackType = notEmpty.feedbackType();
                }
            }
            if (StringUtils.isBlank(returnMsg)) {
                returnMsg = "参数错误";
            }
            proceed = joinPoint.proceed(params);
            if (type != 0) {
                switch (feedbackType) {
                    case 0:System.out.println(returnMsg);break;
                    case 1:throw new RuntimeException(returnMsg);
                    case 2:proceed = null;break;
                    case 3:proceed = proceed.getClass().newInstance();break;
                    case 4:{
                        if (type == 1) {
                            commonResult = new CommonResult(false, returnMsg);
//                commonResult = new CommonResult(false, "第" + number +"个参数为空");
                        } else if (type == 2) {
                            commonResult = new CommonResult(false, returnMsg);
//                commonResult = new CommonResult(false, "第" + number +"个参数集合为空");
                        }
                    }break;
                    default:break;
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
        if (commonResult != null) {
            return commonResult;
        }
        return proceed;
    }
}
