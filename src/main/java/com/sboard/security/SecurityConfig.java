package com.sboard.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    /*
        스프링 시큐리티
        -스프링 프레임워크에서 제공하는 보안관련 처리를 윈한 프레임워크
        -스프링기반 프로젝트는 스프링히큐리티로 보안(인증, 인가)처리 권장

        인증설정
        -로그인, 로그아웃 페이지 및 요청주소 사용자 설정
        -기본 인증(로그인, 로그아웃)은 시큐리티가 제공하는 기본 인증페이지 동작
        -REST API 기반 인증에서는 토큰방식을 이용하기 때문에 로그인, 로그아웃 비화성

        인가설정
        -MyUserDetails의 사용자 권한 목록을 제공하는 getAuthorities()에서 반드시
        접두어로 ROLE_ 븥여야 함
        -ROLE_ 접두어를 안붇이면 hasAuthority()-좀더 상세하게 세분화-사용시 ROLE제거, hasAnyAuthority()로 권한 설정
        -존재하지 않은 요청주소에 대해서 시큐리티는 로그인페이지로 기본 redirect 수행하기 때문에
         마지막에 anyRequest().permitAll() 권한 설정

     */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        //로그인 설정
        http.formLogin(login->login
                                    .loginPage("/user/login")
                                    .defaultSuccessUrl("/user/success")  //컨트롤러 요청주소
                                    .failureUrl("/user/login?success=100")
                                    .usernameParameter("uid")
                                    .passwordParameter("pass"));

        //로그아웃 설정
        http.logout(logout -> logout
                .invalidateHttpSession(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/user/login?success=200"));  //성공시 리다이렉트

        //인가 설정
        http.authorizeHttpRequests(authorize->authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/manager/**").hasAnyRole("ADMIN","MANAGER")
                .requestMatchers("/staff/**").hasAnyRole("ADMIN","MANAGER","STAFF")
                .anyRequest().permitAll());

        //기타 보안 설정
        http.csrf(configure->configure.disable());

        return http.build();
    }
        @Bean
        public PasswordEncoder passwordEncoder() {  //비밀번호 암호화
            return new BCryptPasswordEncoder();
        }

}
