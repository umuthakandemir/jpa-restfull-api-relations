package com.boomliapps.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boomliapps.controller.ICustomerController;
import com.boomliapps.dto.DtoCustomer;
import com.boomliapps.dto.DtoCustomerIU;
import com.boomliapps.service.ICustomerService;

@RestController
@RequestMapping("/rest/api/customer")
public class CustomerControllerImpl implements ICustomerController{

	@Autowired
	private ICustomerService iCustomerService;
	
	@GetMapping(path = "/{id}")
	@Override
	public DtoCustomer getCustomerById(@PathVariable(name = "id") Long Id) {
		return iCustomerService.getCustomerById(Id);
	}

	@GetMapping(path = "/")
	@Override
	public List<DtoCustomer> getCustomerList() {
		return iCustomerService.getCustomerList();
	}

	@PostMapping(path = "/")
	@Override
	public DtoCustomer postCustomer(@RequestBody DtoCustomerIU dtoCustomerIU) {
		// TODO Auto-generated method stub
		return iCustomerService.postCustomer(dtoCustomerIU);
	}

	@PutMapping(path = "/{id}")
	@Override
	public DtoCustomer putCustomer(@PathVariable(name = "id")Long id, @RequestBody DtoCustomerIU dtoCustomerIU) {
		// TODO Auto-generated method stub
		return iCustomerService.putCustomer(id, dtoCustomerIU);
	}

}
