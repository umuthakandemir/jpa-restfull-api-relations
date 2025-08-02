package com.boomliapps.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boomliapps.controller.IHomeController;
import com.boomliapps.dto.DtoHome;
import com.boomliapps.service.IHomeService;


@RestController
@RequestMapping("/rest/api/home")
public class HomeControllerImpl implements IHomeController{

	@Autowired
	private IHomeService iHomeService;
	
	@GetMapping(path = "/{id}")
	@Override
	public DtoHome getDtoHomeById(@PathVariable(name = "id") Long id) {
		return iHomeService.getDtoHomeById(id);
	}

}
