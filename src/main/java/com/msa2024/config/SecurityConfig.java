package com.msa2024.config;

import com.msa2024.users.AuthFailureHandler;
import com.msa2024.users.AuthSuccessHandler;
import com.msa2024.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import java.net.URLEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AuthSuccessHandler authSuccessHandler;
    private final UserService userService;
    private final AuthFailureHandler authFailureHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 권한에 따라 허용하는 url 설정
        // /login, /signup 페이지는 모두 허용, 다른 페이지는 인증된 사용자만 허용
        http.csrf().disable()

                .authorizeRequests()
                .antMatchers("/users/admin/**").hasAuthority("ROLE_ADMIN")
                .antMatchers("/", "/login", "/signup", "/users/signup", "/users/loginForm", "/users/signupForm", "/resources/**").permitAll()
                .anyRequest().authenticated();

        // login 설정
        http
                .formLogin()
                .loginPage("/users/loginForm")
                .loginProcessingUrl("/login")    // POST 요청 (login 창에 입력한 데이터를 처리)
                .usernameParameter("member_id")	// login에 필요한 id 값을 email로 설정 (default는 username)
                .passwordParameter("member_pwd")	// login에 필요한 password 값을 password(default)로 설정
                .defaultSuccessUrl("/")	// login에 성공하면 /로 redirect
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler);

        // logout 설정
        http
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")// logout에 성공하면 /로 redirec
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/users/loginForm?error=true&exception=" + URLEncoder.encode("세션이 만료되었습니다. 다시 로그인 해주세요", "UTF-8"));

        return http.build();
    }}