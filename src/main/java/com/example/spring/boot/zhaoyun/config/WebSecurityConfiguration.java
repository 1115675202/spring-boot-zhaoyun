//package com.example.spring.boot.zhaoyun.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Objects;
//
///**
// * @author LiuBing
// * @date 2020/12/9
// */
//@Configuration
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//	private static final String[] SWAGGER_URL = {
//			"/swagger-resources/**",
//			"/swagger-ui.html",
//			"/swagger-ui/**",
//			"/v2/api-docs",
//			"/webjars/**",
//	};
//
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers(
//				"/swagger-ui/**",
//
//				"/swagger-ui.html",
//				"/v2/api-docs", // swagger api json
//				"/swagger-resources/configuration/ui", // 用来获取支持的动作
//				"/swagger-resources", // 用来获取api-docs的URI
//				"/swagger-resources/configuration/security", // 安全选项
//				"/swagger-resources/**"
//		);
//	}
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//				.authorizeRequests()
//				.antMatchers(SWAGGER_URL)
//				.permitAll()
//		;
//	}
//
//
//
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//				.withUser("user").password("123456").roles("USER");
//	}
//
//	@Bean
//	@Override
//	public AuthenticationManager authenticationManagerBean() throws Exception {
//		return super.authenticationManagerBean();
//	}
//
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new PasswordEncoder() {
//			@Override
//			public String encode(CharSequence charSequence) {
//				return charSequence.toString();
//			}
//
//			@Override
//			public boolean matches(CharSequence charSequence, String s) {
//				return Objects.equals(charSequence.toString(),s);
//			}
//		};
//	}
//}
