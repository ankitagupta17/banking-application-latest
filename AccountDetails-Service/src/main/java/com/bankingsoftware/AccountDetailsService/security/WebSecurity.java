package com.bankingsoftware.AccountDetailsService.security;

import com.bankingsoftware.AccountDetailsService.service.AccountDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
class WebSecurity extends WebSecurityConfigurerAdapter {

    private Environment environment;
    private AccountDetailsService accountDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public WebSecurity(Environment environment, AccountDetailsService accountDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.environment = environment;
        this.accountDetailsService = accountDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/**").permitAll()
        .and()
        .addFilter(getAuthenticationFilter());
        http.headers().frameOptions().disable();
    }

    private Filter getAuthenticationFilter() throws Exception{
        AuthenticationFilter authenticationFilter=new AuthenticationFilter(accountDetailsService, environment, authenticationManager());
        authenticationFilter.setFilterProcessesUrl(environment.getProperty("login.url.path"));
        return authenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
