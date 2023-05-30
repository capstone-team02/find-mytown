package com.team2.findmytown.config;



import com.team2.findmytown.security.JwtAuthenticationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;


@EnableWebSecurity
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //http 시큐리티 빌더
        http.cors() //기본 cors
                .and()
                .csrf()
                    .disable() // 사용 안함
                .httpBasic()
                    .disable()
                .sessionManagement()//session 기반이 아님을 선언
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()//아래 경로는 인등 안 해도 됨
                    .antMatchers("/","/mytown/v1/auth/**","/error","/addData/add-table").permitAll()
                .anyRequest()//이외는 전부 인증해야함
                    .authenticated();

        http.addFilterAfter(
                jwtAuthenticationFilter,
                CorsFilter.class
        );
    }


}
