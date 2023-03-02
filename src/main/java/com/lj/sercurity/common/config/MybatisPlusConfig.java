package com.lj.sercurity.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.lj.sercurity.dao")
public class MybatisPlusConfig {
}
