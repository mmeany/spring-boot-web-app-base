package com.mvmlabs.springboot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.WebMvcAutoConfigurationAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class ConfigurationForWeb extends WebMvcAutoConfigurationAdapter {

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(userDetailInterceptor());
        super.addInterceptors(registry);
    }
    
    public UserDetailInterceptor userDetailInterceptor() {
        return new UserDetailInterceptor();
    }

    /**
     * An intereptor that pushes the current user UserDetails object into the request as an attribute
     * named 'currentUser'.
     * 
     * @author Mark Meany
     */
    protected class UserDetailInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
            final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                if (!(auth instanceof AnonymousAuthenticationToken)) {
                    if (auth.getPrincipal() != null) {
                        request.setAttribute("currentUser", auth.getPrincipal());
                    }
                }
            }
            return super.preHandle(request, response, handler);
        }
    }
}
