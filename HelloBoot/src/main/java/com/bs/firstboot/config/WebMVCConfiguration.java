package com.bs.firstboot.config;

import java.util.Properties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.bs.firstboot.common.LoggerInterceptor;
import com.bs.firstboot.model.dto.Member;

@Configuration //WebMvcConfigurer 인터페이스 재정의해서 씀 
//@EnableAspectJAutoProxy -> 없어도 aop 올라감 ㅋ
@MapperScan("com.bs.firstboot.common.mapper") //@Configuration 안에다가 선언해야한다 
public class WebMVCConfiguration implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//인터셉터 등록할 때 사용하는 메소드  
		registry.addInterceptor(new LoggerInterceptor()).addPathPatterns("/**");
	}

	
	//단순 페이지 전환은 여기다가 하면 됨 !!!!! 매핑주소링 뷰 이름 
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// view 연결해주는 로직을 코드로 작성할 때 사용 
		//단순 페이지 연결하는 것은 여기다가 좌랄라ㅏㄱ 적어도 됨
		registry.addViewController("board/boardlist").setViewName("board/boardlist");
//											     	  매핑주소                                           뷰 이름 
		registry.addViewController("/chatpage").setViewName("chatting");
	}
	
	@Bean 
	LoggerInterceptor loggerInter() {
		return new LoggerInterceptor();
	}
	
	@Bean
	Member member() {
		return new Member();
	}
	
	
	@Bean
	@Primary
	HandlerExceptionResolver handlerExceptionResolver2() {
		Properties exceptionProp=new Properties();
		exceptionProp.setProperty(IllegalArgumentException.class.getName(),
				"error/argumentException");
		
		SimpleMappingExceptionResolver resolve=new SimpleMappingExceptionResolver();
		resolve.setExceptionMappings(exceptionProp);
		resolve.setDefaultErrorView("error/error");
		return resolve;
	}

	
	
	//외부에서 js로 요청힌 것에 대한 허용하기 
	//cors : 다른 서버에서 js로 요청한 내용을 차단함 서버측에서 허용해야할 ip나 도메인이 있으면 안막히게 하는거 
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:3000"); //리액트가 3000번 씀 
		//모든 요청은 허용한다
	  //이 도메인에서 오는 애들을 allowedOrigins("http://localhost:3000"); 
		
	}
	
	
	
	
}
