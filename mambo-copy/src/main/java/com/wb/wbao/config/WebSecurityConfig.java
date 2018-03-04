package com.wb.wbao.config;

import com.h3c.common.config.DefaultAuthenticationTokenFilter;
import com.wb.wbao.filter.JWTLoginFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * Created by dell on 2018/3/4.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 由于使用的是JWT，我们这里不需要csrf
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                // 允许对于网站静态资源的无授权访问
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.ico",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/**/*.html",
                        "/**/**/*.js",
                        "/**/**/*.ico",
                        "/**/**/*.css"
                ).permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
//                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                .failureForwardUrl("/logout")
                .defaultSuccessUrl("/app/welcome")
                .and()
                .logout().logoutUrl("/logout").permitAll().logoutSuccessUrl("/login")
                .and().addFilter(new JWTLoginFilter(authenticationManager()));

        http.addFilterBefore(new DefaultAuthenticationTokenFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class);

        // 禁用缓存
        http.headers().cacheControl();
    }

}
