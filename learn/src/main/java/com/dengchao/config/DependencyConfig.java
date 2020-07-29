package com.dengchao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : dengchao
 * @date : 2020 07 29
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.dengchao.dependency")
public class DependencyConfig {
}
