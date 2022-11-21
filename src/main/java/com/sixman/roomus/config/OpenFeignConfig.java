package com.sixman.roomus.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(basePackages = "com.sixman.roomus")
@Configuration
public class OpenFeignConfig {
}
