package com.heiiyo.dlock.lock;

/**
 * 分布式锁实例的接口
 * @author kaixu
 */
public interface Lock {

    /**
     * 获取锁
     * @return
     */
    boolean lock(String key);

    /**
     * 释放锁
     * @return
     */
    void unlock(String key);

    /**
     * 获取锁的状态
     * @return
     */
    int lockStatus(String key);

    /**
     * 获取锁的是否有效
     * @param key
     * @return
     */
    boolean isValid(String key);
}
