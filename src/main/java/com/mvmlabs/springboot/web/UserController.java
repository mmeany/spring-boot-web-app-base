package com.mvmlabs.springboot.web;

import java.util.Calendar;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mvmlabs.springboot.domain.User;
import com.mvmlabs.springboot.service.UserService;
import com.mvmlabs.springboot.web.form.UserForm;

/**
 * Controller that demonstrates:
 *  tiles mapping,
 *  request parameters and path variables.
 *  Constructor injection of autowired services
 * 
 * @author Mark Meany
 */
@Controller
public class UserController {
    /** Logger implementation. */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public static final String LIST_USERS_VIEW_NAME  = "user.list";
    
    public static final String VIEW_USERS_VIEW_NAME  = "user.view";
    
    public static final String USER_DETAILS_FORM_VIEW_NAME = "user.form";
    
	public static final String SUCCESS_REDIRECT_TEMPLATE = "redirect:/admin/user/view/%d";
	
	private final UserService userService;
	
	@Autowired
	public UserController(final UserService userService) {
	    this.userService = userService;
	}
			
    @RequestMapping(value = "/admin/user/list", method=RequestMethod.GET)
	public ModelAndView list(@PageableDefault(page = 0, value = 5) final Pageable pageable) {
        logger.debug("List requested.");
	    return new ModelAndView(LIST_USERS_VIEW_NAME, "page", userService.getAllRegisteredUsers(pageable));
	}

    @RequestMapping(value="/admin/user/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") final User user) {
        logger.debug("View requested for user %d.", user.getId());
        return new ModelAndView(VIEW_USERS_VIEW_NAME, "user", user);
    }

    @RequestMapping(value="/admin/user/add", method = RequestMethod.GET)
    public ModelAndView addForm() {
        logger.debug("Add new user form requested.");
        return new ModelAndView(USER_DETAILS_FORM_VIEW_NAME, "user", new User());
    }
    
    @RequestMapping(value="/admin/user/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable("id") final User user) {
        logger.debug("Edit user form requested for user %d.", user.getId());
        return new ModelAndView(USER_DETAILS_FORM_VIEW_NAME, "userForm", new UserForm(user));
    }

    @RequestMapping(value="/admin/user/add", method=RequestMethod.POST)
    public String saveAddUser(@Valid final UserForm form, final BindingResult binding) {
        logger.debug("New user form has been submitted.");
        if (binding.hasErrors()) {
            return USER_DETAILS_FORM_VIEW_NAME;
        }
        final User user = new User();
        user.setCreateDate(Calendar.getInstance());
        form.update(user);
        userService.save(user);
        
        return getViewUserRedirectUrl(form.getId());
    }

    @RequestMapping(value="/admin/user/edit/{id}", method=RequestMethod.POST)
    public String saveEditUser(@Valid final UserForm form, final BindingResult binding) {
        logger.debug("Edit user form has been submitted for user %d.", form.getId());
        if (binding.hasErrors()) {
            return USER_DETAILS_FORM_VIEW_NAME;
        }
        final User user = userService.loadUserById(form.getId());
        form.update(user);
        userService.save(user);
        return getViewUserRedirectUrl(form.getId());
    }

    private String getViewUserRedirectUrl(final long id) {
        logger.debug("Building view user redirect for user %d.", id);
        return String.format(SUCCESS_REDIRECT_TEMPLATE, id);
    }
}
