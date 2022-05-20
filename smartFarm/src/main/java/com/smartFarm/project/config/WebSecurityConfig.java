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

import com.smartFarm.project.service.LoginIdPwValidator;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginIdPwValidator loginIdPwValidator;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests()
        .antMatchers("/home","/explanation","/photo","/teamRole","/shamePoint").permitAll()    // LoadBalancer Chk
            .antMatchers("/admin").hasAuthority("admin")
            .anyRequest().authenticated()
        .and()
            .formLogin()
            //.loginPage("/home")
            .loginProcessingUrl("/loginProc")
            .usernameParameter("login_id")
            .passwordParameter("login_password")
            .defaultSuccessUrl("/home", true)
            .failureForwardUrl("/shamePoint")
            .permitAll()
        .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logoutProc"));
    }
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}
	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginIdPwValidator);
    }
   
}