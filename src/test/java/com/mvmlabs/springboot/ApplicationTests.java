package com.mvmlabs.springboot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mvmlabs.springboot.Application;
import com.mvmlabs.springboot.domain.User;
import com.mvmlabs.springboot.service.UserService;

/**
 * For testing to work as-is, out of the box, I had to introduce a configuration class
 * that replaces the Apache Tiles configurer with one that locates the configuration file
 * as a file system resource relative to the project root directory. Other than that, this
 * is the test created by Spring Source Tool Suite.
 * 
 * @author Mark Meany
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class, TestConfigurtaion.class})
@WebAppConfiguration
public class ApplicationTests {

    @Autowired
    UserService userService;
    
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testTheIssue() throws Exception {
	    
	    User user = userService.loadUserById(1L);
	    int i = user.getNumberOfVisits();
	    
	    User userUpdated = userService.registerVisit(user);
	    int j = userUpdated.getNumberOfVisits();
	    
	    Assert.assertEquals(i + 1,  j);
	}
}
