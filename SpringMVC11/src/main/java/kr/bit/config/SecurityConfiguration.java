package kr.bit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private UserDetailsServiceImpl UserDetailsService;
	
	// password 인코딩에 필요한 빈 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	// 가장 중요 
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.csrf().disable(); // 인증토큰 disable
		http.authorizeHttpRequests() // 클라이언트의 요청에 대해 핸들링 하겠다
		.antMatchers("/","member/**").permitAll() // 해당 url은 모두 허용 
		.antMatchers("/board/**").authenticated() // 해당 url은 인증필요 
		
		.and()
		.formLogin() // 로그인 폼을 쓰겠다 
		.loginPage("/member/login") // 해당 url은 로그인 페이지로 
		.defaultSuccessUrl("/board/list") // 로그인 성공시 해당 url로 이동 
		
		.and()
		.logout() 
		.logoutUrl("/member/logout") // 해당 url은 로그아웃 
		.logoutSuccessUrl("/"); // 로그아웃 성공시 루트 페이지로 가겠다 
		
		http.userDetailsService(UserDetailsService);
		
		return http.build();
	}
	

}
