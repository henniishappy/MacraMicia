package com.macramicia;

import com.macramicia.user.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final BCryptEncoderConfig bCryptEncoderConfig;
    private final MyUserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(MyUserDetailsService userDetailsService, BCryptEncoderConfig bCryptEncoderConfig) {
        this.bCryptEncoderConfig = bCryptEncoderConfig;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/courses/new").hasAuthority("ADMIN")
                .antMatchers("/courses/create").hasAuthority("ADMIN")
                .antMatchers("/courses/remove").hasAuthority("ADMIN")
                .and()
            .formLogin()
                .loginProcessingUrl("/user/login/authenticate")
                .and()
            .logout()
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/logoutSuccess")
                .permitAll();
    }

    @Autowired
    protected void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptEncoderConfig.passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}