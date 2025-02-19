package com.web.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
public class CharacterSetConfig {

	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean() {

		FilterRegistrationBean<CharacterEncodingFilter> registrationBean = new FilterRegistrationBean<CharacterEncodingFilter>();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("EUC-KR");
		characterEncodingFilter.setForceEncoding(true);

		registrationBean.setName("payMngOnlineResultFilter");

		registrationBean.setFilter(characterEncodingFilter);

		registrationBean.addUrlPatterns("/common/payMngOnlineResult");

		return registrationBean;
	}

}
