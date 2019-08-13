package com.xiaobi.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaobi.controller"})
@EnableJpaRepositories(basePackages = "com.xiaobi.service")
@EntityScan(basePackages = "com.xiaobi.entity")
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class);
    }
}
