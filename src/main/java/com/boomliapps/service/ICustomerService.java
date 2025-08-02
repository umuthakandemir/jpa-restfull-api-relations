package com.boomliapps.service;

import java.util.List;

import com.boomliapps.dto.DtoCustomer;
import com.boomliapps.dto.DtoCustomerIU;

public interface ICustomerService {
	public DtoCustomer getCustomerById(Long Id);
	
	public List<DtoCustomer> getCustomerList();
	
	public DtoCustomer postCustomer(DtoCustomerIU dtoCustomerIU);
	
	public DtoCustomer putCustomer(Long id,DtoCustomerIU dtoCustomerIU);
}
