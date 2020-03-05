package com.heiiyo.dlock.aspect;

import com.heiiyo.dlock.annotation.DLock;
import com.heiiyo.dlock.lock.Lock;
import com.heiiyo.dlock.lock.LockDetails;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
public class DLockAop {

    @Autowired
    private Lock lock;

    @Pointcut(value = "@annotation(com.heiiyo.dlock.annotation.DLock)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void obtainLock(JoinPoint joinPoint) {
        String key = joinPoint.getSignature().getName();
        LockDetails lockDetails = new LockDetails();
        lockDetails.setEffectiveTime(500);

        lockDetails.setUnitTime(TimeUnit.MILLISECONDS);
        lockDetails.setCurrentInstance(String.valueOf(Thread.currentThread().getId()));
        lock.lock(key);
    }



}
