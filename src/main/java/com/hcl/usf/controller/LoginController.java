package com.hcl.usf.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hcl.usf.domain.User;
import com.hcl.usf.dto.SystemUserDto;
import com.hcl.usf.dto.UserDto;
import com.hcl.usf.service.AlertService;
/***
 * 
 * @author X3O6026
 *
 */
@Controller
public class LoginController {
	
	
	@Autowired
	Environment env;
	@Autowired
	AlertService alertService;
	/**
	 * will do redirect to login pages
	 * @return {@link String}
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	
	@RequestMapping("/welcome")
	public String welcome() {
		return "welcome";
	}

	/***
	 * This method will logged in with credentials
	 * @param session {@link HttpSession}
	 * @param userDto {@link UserDto}
	 * @param model {@link Model}
	 * @return {@link String}
	 */
	@RequestMapping(value = "/dologin", method = RequestMethod.POST)
	public String doLogin(HttpSession session, @ModelAttribute("user") UserDto userDto, Model model) {

		try {
			User users = null;
			boolean isFound = false;
			if (users == null) {
				// check default login for system only
				isFound = checkSystemAdmin(userDto);

			} /*else {
				// again check if system is logging or not
				isFound = checkSystemAdmin(userDto);
			}*/

			if (isFound) {
				// Set user dummy data
				session.setAttribute("user", userDto);
				session.setAttribute("projName", env.getProperty("default.project.name"));
				int sessionTime = Integer.valueOf(env.getProperty("session.timeout"));
				session.setMaxInactiveInterval(sessionTime);

			} else {
				model.addAttribute("msg", alertService.error("Login failed. Try again."));
				return "login";
			}
		} catch (Exception e) {
			return "redirect:/login?msg=System Error!!! Please contact to System Admin.";

		} //
		return "redirect:/home";
	}
	

	/****
	 * @param userDto {@link UserDto}
	 * @return {@link Boolean}
	 */
	private boolean checkSystemAdmin(UserDto userDto) {
		SystemUserDto system = new SystemUserDto();
		if (userDto.getEmail().equals(system.getEmail()) && userDto.getUserpass().equals(system.getUserpass())) {
			userDto.setUsername("Admin");
			userDto.setUserpass("");
			return true;
		} else {
			return false;
		}
	}

	
	/**
	 * This will registered user
	 * @param session {@link HttpSession}
	 * @param model {@link Model}
	 * @return {@link String}
	 */
	@RequestMapping("/reguser")
	public String userRegis(HttpSession session, ModelMap model) {
		model.addAttribute("user", new UserDto());
		return "reguser";
	}

	

	/***
	 * Logout
	 * @param session {@link HttpSession} 
	 * @return {@link String}
	 */
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.setAttribute("user", null);
		session.invalidate();
		return "redirect:/login?msg=You are now signed out!!!";
	}

}
