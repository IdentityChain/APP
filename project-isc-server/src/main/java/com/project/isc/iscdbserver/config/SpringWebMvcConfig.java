package com.project.isc.iscdbserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.project.isc.iscdbserver.util.UserLoginSetting;

@Configuration
@CrossOrigin
public class SpringWebMvcConfig extends WebMvcConfigurerAdapter {
	@Autowired
	private UserLoginSetting userLoginSetting;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").exposedHeaders("loginStatus");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html")
				.addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}

	@Bean
	public LoginFilterHandler loginFilterHandler() {
		return new LoginFilterHandler();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if(userLoginSetting.isUseInterceptor()) {
			if(userLoginSetting.isVisitSwagger()){
				registry.addInterceptor(loginFilterHandler()).addPathPatterns("/**").excludePathPatterns("/user/login","/user/firstsave","/sms/getCodeByPhone/*","/swagger-resources","/v2/api-docs");
			}else{
				registry.addInterceptor(loginFilterHandler()).addPathPatterns("/**").excludePathPatterns("/user/login","/user/firstsave","/sms/getCodeByPhone/*");
			}
		}
		
		super.addInterceptors(registry);
	}

}
