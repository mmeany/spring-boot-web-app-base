package com.mvmlabs.springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    /** Logger implementation. */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value="/")
    public String root() {
        logger.debug("Root requested, going home.");
        return home();
    }
    
    @RequestMapping(value = "/home", method=RequestMethod.GET)
    public String home() {
        logger.debug("Home requested.");
        return "site.homepage";
    }
}
