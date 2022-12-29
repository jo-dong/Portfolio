package himedia.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	protected void configure(HttpSecurity http) throws Exception{
//        http
//            .csrf().disable()
//            .authorizeRequests()
//            // 페이지 권한 설정
//            .antMatchers("/").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .defaultSuccessUrl("/main")
//            .failureHandler(failureHandler()) // 이부분이 실패했을때 이 핸들러를 타겠다 라는 뜻
////          .successHandler(successHandler()) // 이부분이 성공했을때 이 핸들러를 타겠다 라는 뜻
//            .permitAll()
//            .and() 
//            // 로그아웃 설정
//            .logout()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
//            .logoutSuccessUrl("/")
//            .invalidateHttpSession(true)
//            .deleteCookies("JSESSIONID")
//            .permitAll();
//        http.sessionManagement()
//            .maximumSessions(1)
//            .maxSessionsPreventsLogin(false);
//    }
//
//	// 빈주입을 통해 AuthenticationFailureHandler를 탈 경우에 내가 생성한 LoginFailHandler에서
//    // 추가적인 행동을 하겠다 라는 뜻의 선언이다.
//    @Bean
//    public AuthenticationFailureHandler failureHandler() {
//        return new CustomAuthFailureHandler();
//    }
}
