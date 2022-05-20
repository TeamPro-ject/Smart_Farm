package com.smartFarm.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.smartFarm.project.security.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailServiceImpl cus;
   

	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/home","/explanation","/photo","/teamRole","/shamePoint").permitAll()    // LoadBalancer Chk
            .antMatchers("/admin").hasAuthority("admin")
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/home")
            .loginProcessingUrl("/loginProc")
            .usernameParameter("user_id")
            .passwordParameter("user_password")
            .defaultSuccessUrl("/home", true)
            .failureForwardUrl("/shamePoint")
            .permitAll()
        .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc"));
//        .and()
 //       	.sessionManagement()
//        	.maximumSessions(1) //최대세션을 1로설정
 //       	.maxSessionsPreventsLogin(false);  // 중복로그인시 이전 로그인했던 세션 만료. true시 이전 세션 유지
        
       //http.addFilterAfter(sessionFilter, UsernamePasswordAuthenticationFilter.class);
	}	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(cus);
    }
   
}