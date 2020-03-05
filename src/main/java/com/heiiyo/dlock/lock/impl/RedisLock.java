package com.heiiyo.dlock.lock.impl;

import com.heiiyo.dlock.lock.Lock;
import com.heiiyo.dlock.lock.LockDetails;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

public class RedisLock implements Lock {

    private RedisTemplate<String,Object> redisTemplate;

    private ValueOperations valueOperations;

    public RedisLock(RedisTemplate<String,Object> redisTemplate){
        this.redisTemplate = redisTemplate;
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public boolean lock(String key) {
        Assert.notNull(key, "non null hash key required");
        long startTime = System.currentTimeMillis();
        boolean isLock = false;
        while(!isLock){
            if(this.valueOperations.setIfAbsent(key,new LockDetails(),300l, TimeUnit.MILLISECONDS)){
                isLock = true;
                return isLock;
            }
            isLock = isValid(key);
            //获取锁时间超过两秒直接退出
            if (System.currentTimeMillis() - startTime > 2000)
                break;
        }
        return isLock;
    }

    @Override
    public void unlock(String key) {
        Assert.notNull(key, "non null hash key required");
        LockDetails lockDetails = (LockDetails)this.valueOperations.get(key);
        if(lockDetails == null){
            return;
        }
        if(String.valueOf(Thread.currentThread().getId()).equals(lockDetails.getCurrentInstance())
                && lockDetails.getCurrentService().equals("")){
            this.redisTemplate.delete(key);
        }
    }

    @Override
    public int lockStatus(String key) {
        return 0;
    }

    @Override
    public boolean isValid(String key) {
        Assert.notNull(key, "non null hash key required");
        LockDetails lockDetails = (LockDetails)this.valueOperations.get(key);
        //锁实例不存在
        if (lockDetails == null){
            unlock(key);
            return false;
        }
        long validTime = lockDetails.getCreateTime() + lockDetails.getEffectiveTime();
        //锁已经超时过去
        if(System.currentTimeMillis() > validTime){
            unlock(key);
            return false;
        }
        //锁不属于当前应用
        if(!String.valueOf(Thread.currentThread().getId()).equals(lockDetails.getCurrentInstance())
                || !lockDetails.getCurrentService().equals("")){
            unlock(key);
            return false;
        }
        return true;
    }
}
