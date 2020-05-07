package com.example.springbootdemo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {


    /**
     * 获取yml数据库配置
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DruidDataSource druidDataSource() {
        return new DruidDataSource();
    }


    /**
     * druid监控
     * 1.配置管理后台的servlet
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String,String> params = new HashMap<>();//可以传设置 参考ResourceServlet
        params.put("loginUsername", "test");
        params.put("loginPassword", "test");
        params.put("allow",""); //不写允许所有
//        params.put("deny")
        bean.setInitParameters(params);
        return bean;
    }

    /**
     * druid监控
     * 1.配置web监控的filter
     * @return
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String,String> params = new HashMap<>();//配置参考WebStatFilter
        params.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(params);

        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
