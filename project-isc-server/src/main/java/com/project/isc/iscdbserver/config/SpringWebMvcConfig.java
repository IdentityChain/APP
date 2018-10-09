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

	@Bean
	public LoginInterceptor loginInterceptor() { return  new LoginInterceptor(); }

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		if(userLoginSetting.isUseInterceptor()) {
			if(userLoginSetting.isVisitSwagger()){
				registry.addInterceptor(loginInterceptor()).addPathPatterns("/**").excludePathPatterns("/swagger-resources","/v2/api-docs");
			}else{
				registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
			}
		}
		super.addInterceptors(registry);
	}

}
