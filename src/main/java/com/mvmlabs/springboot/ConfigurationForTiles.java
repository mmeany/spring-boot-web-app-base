package com.mvmlabs.springboot;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.view.tiles3.SpringBeanPreparerFactory;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

/**
 * Tiles configuration.
 * 
 * References: http://docs.spring.io/spring/docs/4.0.6.RELEASE/spring-framework-reference/html/view.html#view-tiles-integrate
 * 
 * @author Mark Meany
 */
@Configuration
public class ConfigurationForTiles {

    /**
     * Initialise Tiles on application startup and identify the location of the tiles configuration file, tiles.xml.
     * 
     * @return tiles configurer
     */
    @Bean
    public TilesConfigurer tilesConfigurer() {
        final TilesConfigurer configurer = new TilesConfigurer();
        configurer.setDefinitions(new String[] { "WEB-INF/tiles/tiles.xml" });
        configurer.setCheckRefresh(true);
        
        // Provide Spring Beans as view preparers
        configurer.setPreparerFactoryClass(SpringBeanPreparerFactory.class);
        
        return configurer;
    }

    /**
     * A Tiles View Preparer that makes the authenticated user object available as an attribute called user.
     * 
     * @return
     */
    @Bean
    public UsernamePreparer usernamePreparer() {
        return new UsernamePreparer();
    }
    
    /**
     * Introduce a Tiles view resolver, this is a convenience implementation that extends URLBasedViewResolver.
     * 
     * @return tiles view resolver
     */
    @Bean
    public TilesViewResolver tilesViewResolver() {
        final TilesViewResolver resolver = new TilesViewResolver();
        resolver.setViewClass(TilesView.class);
        return resolver;
    }
    
    /**
     * A View Preparer that queries the spring security context. If it finds an authenticated user object
     * then it makes it available as a cascading attribute that can be accessed in a view thus:
     * 
     * <tiles:getAsString name="user" />
     * 
     * @author Mark Meany
     */
    protected class UsernamePreparer implements ViewPreparer {

        @Override
        public void execute(Request arg0, AttributeContext arg1) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (!(auth instanceof AnonymousAuthenticationToken)) {
                final UserDetails userDetails = (UserDetails) auth.getPrincipal();
                arg1.putAttribute("user", new Attribute("signed in as " + userDetails.getUsername()), true);
            } else {
                arg1.putAttribute("user", new Attribute("not signed in"), true);
            }
        }
    }
}
