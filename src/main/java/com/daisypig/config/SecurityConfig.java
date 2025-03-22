package com.daisypig.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity//开启方法级别的安全
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        // 允许访问静态资源
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/user/list").hasAuthority("USER_LIST")
 //                       .requestMatchers("/user/add").hasAuthority("USER_ADD")
//                        .requestMatchers("/user/add2").hasRole("ADMIN123")
//                        .requestMatchers("/user/list2").hasRole("USER123")
                        .anyRequest()
                                .authenticated()
                );
        //登录
        http.formLogin(form -> form.loginPage("/login")
                        .successHandler(new MyAuthSuccessHandler())//成功处理，返回json
                        .failureHandler(new MyAuthFailureHandler())//失败处理，返回json
                        .permitAll()
                );
        //登出
        http.logout(logout -> {
                    logout.logoutSuccessHandler(new MyLogoutSuccessHandle());
                });
        //异常处理认证
        http.exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(new MyAuthEntryPointHandler());
                    //权限不足处理
                    exception.accessDeniedHandler(new MyAuthAccessDeniedHandler());
        });
        //针对同一账号，只允许一个人登录
        http.sessionManagement(session -> {
                    session.maximumSessions(1).expiredSessionStrategy(new MySessionInformationExpiredStrategy());
        });
        //跨域
        //http.cors(Customizer.withDefaults());

                //.httpBasic(Customizer.withDefaults());
        http.csrf(csrf ->csrf.disable());

        return http.build();
    }



}