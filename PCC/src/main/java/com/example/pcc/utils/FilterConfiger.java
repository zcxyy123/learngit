package com.example.pcc.utils;

/**
 * @作者 钟先崟
 * @时间 2023-01-12 22:42
 * @功能
 */
import com.example.pcc.mapper.UserStudentMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class FilterConfiger {

    @Autowired   // 一定要注入，才会让 DemoFilter中的 @Autowrie生效
    private LogCostFilter logCostFilter;

    @Autowired
    public UserStudentMapper userStudentMapper;

    @Bean
    // @Order(1)    //spring boot会按照order值的大小，越小越先执行。(选用)
    // 如果有多个filter , 写多一个这方法，用不同的方法名 ,setName也要不同
    public FilterRegistrationBean configFilter(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(logCostFilter);
        filterRegistrationBean.addUrlPatterns("/*");  // 配置需要过滤的路径
        filterRegistrationBean.setName("logCostFilter");
//        System.out.println(userStudentMapper);
        JwtUitls.userStudentMapper=userStudentMapper;//重新赋值！！！！！！！！！！！！！，防止mapper还没加载的时候，@Autowired
        return filterRegistrationBean;
    }
}