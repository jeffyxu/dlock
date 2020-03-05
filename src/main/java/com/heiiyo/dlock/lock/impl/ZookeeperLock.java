package com.heiiyo.dlock.lock.impl;

import com.heiiyo.dlock.lock.Lock;

public class ZookeeperLock implements Lock {
    @Override
    public boolean lock(String key) {
        return false;
    }

    @Override
    public void unlock(String key) {

    }

    @Override
    public int lockStatus(String key) {
        return 0;
    }

    @Override
    public boolean isValid(String key) {
        return false;
    }
}
