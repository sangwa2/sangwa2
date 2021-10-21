/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codeguru.g_ultimate;

import java.sql.DriverManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author SANGWA
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.codeguru.g_ultimate")
public class Config extends WebMvcConfigurerAdapter {

    public static String uname = "sangwa", psw = "A.manigu125";
    @Autowired
    DataSource datasource;

    @Bean
    public NamedParameterJdbcTemplate getnameJdbcTemplate() {
        return new NamedParameterJdbcTemplate(datasource);
    }
    
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(datasource);
    }

    @Bean
    public DataSource getDataSource() {
        DriverManagerDataSource datasource = new DriverManagerDataSource();
       
        datasource.setDriverClassName("com.mysql.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/giga_java");
        datasource.setUsername("sangwa");
        datasource.setPassword("A.manigu125");
        
        
        
        
//      datasource.setDriverClassName("com.mysql.jdbc.Driver");
//      datasource.setUrl("jdbc:mysql://localhost:3306/codeguru_gigaflex");
//      datasource.setUsername("codeguru_sangwa");
//      datasource.setPassword("A.manigu122");
        return datasource;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("WEB-INF/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("classpath:/statics/");
    }
}
