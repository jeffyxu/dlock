package com.heiiyo.dlock.lock;

import com.heiiyo.dlock.lock.impl.RedisLock;

import java.util.concurrent.TimeUnit;

/**
 * 锁的消息信息
 * @author kaixu
 */
public class LockDetails{

    /**
     * 锁创建的时间戳
     */
    private long createTime;

    /**
     * 当前锁所属的服务
     */
    private String currentService;

    /**
     * 当前锁所属实例
     */
    private String currentInstance;

    /**
     * 锁的有效时间
     */
    private int effectiveTime;

    /**
     * 有效时间单位m,s,millis
     */
    private TimeUnit timeUnit;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCurrentService() {
        return currentService;
    }

    public void setCurrentService(String currentService) {
        this.currentService = currentService;
    }

    public String getCurrentInstance() {
        return currentInstance;
    }

    public void setCurrentInstance(String currentInstance) {
        this.currentInstance = currentInstance;
    }

    public int getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(int effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public TimeUnit getUnitTime() {
        return timeUnit;
    }

    public void setUnitTime(TimeUnit unitTime) {
        this.timeUnit = timeUnit;
    }
}