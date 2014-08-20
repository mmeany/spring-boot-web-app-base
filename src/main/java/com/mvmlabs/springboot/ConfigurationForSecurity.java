package com.mvmlabs.springboot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.mvmlabs.springboot.domain.User;
import com.mvmlabs.springboot.service.UserService;

/**
 * Sources of information for this:
 * 
 * http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#boot-features-security
 * http://docs.spring.io/spring-security/site/docs/3.2.4.RELEASE/reference/htmlsingle/#jc
 * https://github.com/spring-projects/spring-boot/blob/master/spring-boot-samples/spring-boot-sample-web-secure/
 * http://stackoverflow.com/questions/19530768/configuring-spring-security-with-spring-boot
 * 
 * Have to look in so many places!
 * 
 * @author Mark Meany
 *
 */
@Configuration
public class ConfigurationForSecurity {

    @Bean
    public ApplicationSecurity applicationSecurity() {
        return new ApplicationSecurity();
    }

    @Bean
    public AuthenticationSecurity authenticationSecurity() {
        return new AuthenticationSecurity();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new ProcessAuthSuccess();
    }
    
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

        @Autowired
        private SecurityProperties security;
        
        @Autowired
        private AuthenticationSuccessHandler authenticationSuccessHandler;

        /**
         * Configure routes that should be ignored by the security implementation, making
         * them publicly available.
         */
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                .antMatchers("/static/**")
                .antMatchers(HttpMethod.GET, "/public/**")
                .antMatchers(HttpMethod.GET, "/index.html");
        }

        /**
         * Configure routes requiring admin privileges to access.
         * Declare all other routes as requiring an authenticated user.
         * Override POST only handling of the logout root and redirect to login on successful logout.
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")                                      
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error").permitAll()
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
        }

    }

    /**
     * Configure a customised UserDetailsService to be used for authentication. The service implementation will be
     * injected by Spring at runtime.
     * 
     * @author Mark Meany
     *
     */
    @Order(Ordered.HIGHEST_PRECEDENCE + 10)
    protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

        @Autowired
        private UserDetailsService userDetailsService;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }
    }

    protected static class ProcessAuthSuccess extends SimpleUrlAuthenticationSuccessHandler {

        /** Logger implementation. */
        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        private UserService userService;
        
        @Override
        public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException,
                ServletException {
            super.onAuthenticationSuccess(request, response, authentication);
            if (authentication.getPrincipal() instanceof User) {
                final User user = (User) authentication.getPrincipal();
                final User updated = userService.registerVisit(user);
                request.setAttribute("currentUser", updated);
                logger.debug("Authenticated user '%s' added to request as 'currentUser' attribute.", updated.getUsername());
            }
        }
    }
}
