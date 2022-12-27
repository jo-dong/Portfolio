package himedia.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/user/**").authenticated()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/", "/main", "/member/sign-up")	// 설정한 리소스의 접근을 인증 절차 없이 허용
				.permitAll() 
				.anyRequest().authenticated() // 그 외 모든 리소스를 의미하며 인증 필요
				.and()
			.formLogin()
				.permitAll()
				.loginPage("/member/login") // 기본 로그인 페이지
				.loginProcessingUrl("redirect:/main")
				.defaultSuccessUrl("/main", true); // 로그인 성공 시 보여줄 URL
//				.successForwardUrl("/main");
		
		return http.build();
	}
}
