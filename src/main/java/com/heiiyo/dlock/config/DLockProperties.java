package com.heiiyo.dlock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.lock")
public class DLockProperties {

    private String lockType = "redis";
    private String isReentrant;

    public String getLockType() {
        return lockType;
    }

    public void setLockType(String lockType) {
        this.lockType = lockType;
    }

    public String getIsReentrant() {
        return isReentrant;
    }

    public void setIsReentrant(String isReentrant) {
        this.isReentrant = isReentrant;
    }
}
