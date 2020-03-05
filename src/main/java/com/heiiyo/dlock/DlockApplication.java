package com.heiiyo.dlock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.zookeeper.discovery.ConditionalOnZookeeperDiscoveryEnabled;

@SpringBootApplication
@EnableDiscoveryClient
public class DlockApplication {

    public static void main(String[] args) {
        SpringApplication.run(DlockApplication.class, args);
    }

}
