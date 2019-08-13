package com.xiaobi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xiaobi.entity.User;
import com.xiaobi.service.UserRepository;

@Controller
@RequestMapping(value = "/uc")
public class HomeController {
	
	@Autowired
	private UserRepository userService;
	
	@RequestMapping(value="/home")
	public String home(){
		System.out.println("redirect to home page!");
		return "index";
	}
	
	@RequestMapping(value="/home/page")
	@ResponseBody
	public ModelAndView goHome(){
		System.out.println("go to the home page!");
		User user = userService.findOne(1);
		ModelAndView mode = new ModelAndView();
		mode.addObject("name", user.getName());
		mode.setViewName("index");
		return mode;
	}
}