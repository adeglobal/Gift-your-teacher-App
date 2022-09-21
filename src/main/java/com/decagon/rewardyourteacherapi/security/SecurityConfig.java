package com.decagon.rewardyourteacherapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{


    private final AuthUserService authUserService;
    private final JwtFilter jwtFilter;
    @Autowired
    public SecurityConfig(AuthUserService authUserService, JwtFilter jwtFilter){
        this.authUserService = authUserService;
        this.jwtFilter = jwtFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests().antMatchers("/api/v1/login/**","/api/v1/register/**",
                        "/index","/api/oauth2/student/callback","/app/**","/api/oauth2/teacher/callback","/api/v1/student/**")
                .permitAll()
                .antMatchers("/api/**").authenticated().and()
                .exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                . logout()
                .logoutUrl("/api/v1/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/logout"))
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .logoutSuccessHandler((httpServletRequest, httpServletResponse, authentication) -> {
                    httpServletResponse.setStatus(200);
                })
                .and()
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        super.configure(http);
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(authUserService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8081", "google.com","facebook.com"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET","PUT","POST","UPDATE","DELETE"));
        corsConfiguration.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", corsConfiguration); // Global for all paths

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
