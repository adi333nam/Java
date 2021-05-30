package com.example.demo.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienDao;
import com.example.demo.model.Alien;

import java.util.*;


@RestController
public class HomeController 
{
	@Autowired
	AlienDao alienDao;
	
	@GetMapping("/alien")
	public List<Alien> getAlien()
	{
		List<Alien> list=alienDao.findAll();
		return list;
	}
	
	
}
