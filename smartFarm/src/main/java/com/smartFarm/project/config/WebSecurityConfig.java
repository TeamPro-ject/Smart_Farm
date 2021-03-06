package com.smartFarm.project.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.smartFarm.project.security.LoginFailHandler;
import com.smartFarm.project.security.LoginSuccessHandler;
import com.smartFarm.project.security.SessionFilter;
import com.smartFarm.project.security.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailServiceImpl cus;
   
    @Autowired
    SessionFilter sessionFilter;
    
    @Autowired
    DataSource dataSource;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
        http
        .authorizeRequests()
        .antMatchers("/home","/explanation","/photo","/teamRole","/shamePoint","/arduino/","/app/monitoring").permitAll() 
            .antMatchers("/admin").hasAuthority("admin")
            .anyRequest().authenticated()
        .and()
            .formLogin()
            .loginPage("/home?error=false&exception=login")
            .loginProcessingUrl("/loginProc")
            .usernameParameter("user_id")
            .passwordParameter("user_password")
            
            .successHandler(loginSuccessHandler())
            .failureHandler(loginFailHandler())//????????? ?????? ??? ???????????? ????????? ??????.
            .permitAll()
        .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc"))
            .logoutSuccessUrl("/home")
        .and()
       		.sessionManagement()
        	.maximumSessions(1) //??????????????? 1?????????
        	.maxSessionsPreventsLogin(false)  // ?????????????????? ?????? ??????????????? ?????? ??????. true??? ?????? ?????? ??????
        	.expiredUrl("/home?error=false&exception=logout");
        
       http.addFilterAfter(sessionFilter, UsernamePasswordAuthenticationFilter.class);
    
      

	}	
	
	
	public LoginSuccessHandler loginSuccessHandler() {
	return new LoginSuccessHandler();
	}

	
	public LoginFailHandler loginFailHandler(){
	    return new LoginFailHandler();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception { //?????????????????? ?????????????????? ??????
	    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception { //????????? ??????????????? ??????
        auth.userDetailsService(cus);
    }
   
}