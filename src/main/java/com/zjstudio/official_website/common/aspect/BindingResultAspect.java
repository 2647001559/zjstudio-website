package com.zjstudio.official_website.common.aspect;

import com.zjstudio.official_website.common.ReturnBean;
import com.zjstudio.official_website.tool.enums.StatusEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * HibernateValidator错误结果处理切面
 * Created by macro on 2018/4/26.
 */
@Aspect
@Component
@Order(2)
public class BindingResultAspect {
    @Pointcut("execution(public * com.zjstudio.official_website.controller.*.*(..))")
    public void BindingResult() {
    }

    @Around("BindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        ReturnBean<Object> responsesResult = new ReturnBean<>();
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if(fieldError!=null){
                        return responsesResult.returnBean(StatusEnum.FAIL,null,fieldError.getDefaultMessage());
                    }else{
                        return responsesResult.returnBean(StatusEnum.FAIL,null,"");
                    }
                }
            }
        }
        return joinPoint.proceed();
    }
}
