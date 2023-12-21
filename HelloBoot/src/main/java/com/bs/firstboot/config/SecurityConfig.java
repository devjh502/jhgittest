package com.bs.firstboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsUtils;

import com.bs.firstboot.common.MyAuthority;
import com.bs.firstboot.security.DBConnectionProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	//SecurityFilterChain클래스를 bean으로 등록
	@Autowired
	private DBConnectionProvider dbprovider;
	
	@Bean 
	SecurityFilterChain authenticationPath(HttpSecurity http) 
			throws Exception{
		return http
				.csrf(csrf->csrf.disable()) // CSRF 보호 비활성화 (CSRF 공격을 막기 위해서 설정함) 
				.authorizeHttpRequests(request->{ // HTTP 요청 권한 설정
					request.requestMatchers("/").permitAll() // 루트 경로 ("/")에 대한 모든 사용자 허용
					.requestMatchers(req->CorsUtils.isPreFlightRequest(req)).permitAll()
					//.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
					.requestMatchers("/WEB-INF/views/**").permitAll() // "/WEB-INF/views/**" 경로에 대한 모든 사용자 허용
					.requestMatchers("/members").hasAnyAuthority(MyAuthority.ADMIN.name())
					//메소드 방식으로 선언할 수도 있다. 
					.anyRequest().authenticated(); //권한이 있어야 해!  // 나머지 모든 요청에 대해 인증이 필요함
					// 폼 로그인 설정
				}).formLogin(formlogin->{
					formlogin.loginProcessingUrl("/logintest");
				//	.failureForwardUrl("/loginfail")
				//	.successForwardUrl("/loginsuccess");
				})
				.logout(logout->logout.logoutUrl("/logout"))
				
				// 인증 제공자(DB 연동)
				.authenticationProvider(dbprovider)// DB와 연동하여 인증 처리
					
				.build();   // SecurityFilterChain 빌드 및 반환
//				.csrf()
//					.disable()
//				.formLogin()
//					.loginPage("")
//					.successForwardUrl(null)
	}
	
	
}






