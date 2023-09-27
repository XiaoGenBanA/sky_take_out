package com.sky.aspect;

import com.sky.annotation.autofill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Aspect
@Component
@Slf4j
public class AutofillAspect {

    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.autofill)")
    public void autofill(){
    }
    @Before("autofill()")
    public void autoFill(JoinPoint joinPoint){
        //获取当前数据库操作类型
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        autofill annotation = signature.getMethod().getAnnotation(autofill.class);
        OperationType operationType = annotation.value();
        //获取当前实体
        Object[] objects = joinPoint.getArgs();
        Object object = objects[0];
        //反射赋值
        if (operationType == OperationType.INSERT){

            try {
                Method setCreateTime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = object.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateTime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser= object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setCreateTime.invoke(object,LocalDateTime.now());
                setCreateUser.invoke(object, BaseContext.getCurrentId());
                setUpdateTime.invoke(object,LocalDateTime.now());
                setUpdateUser.invoke(object,BaseContext.getCurrentId());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }else if(operationType == OperationType.UPDATE){

            try {
                Method setUpdateTime = object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser= object.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateTime.invoke(object,LocalDateTime.now());
                setUpdateUser.invoke(object,BaseContext.getCurrentId());
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
