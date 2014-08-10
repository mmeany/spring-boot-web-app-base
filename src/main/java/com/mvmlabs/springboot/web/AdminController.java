package com.mvmlabs.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Only here to test access restrictions for Spring Security.
 * 
 * @author Mark Meany
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    /** Logger implementation. */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/greet/{name}", method = RequestMethod.GET)
    public String greet(@AuthenticationPrincipal UserDetails user, @PathVariable(value = "name") final String name, final Model model) {
        if (user == null) {
            throw new RuntimeException("Authentication error");
        }
        model.addAttribute("username", user.getUsername());
        model.addAttribute("name", name);
        logger.info("The authenticated user '" + user.getUsername() + "' is masquarading as '" + name + "'.");
        return "site.admin.greet";
    }
}
