package com.boomliapps.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boomliapps.controller.impl.CustomerControllerImpl;
import com.boomliapps.dto.DtoAddress;
import com.boomliapps.dto.DtoCustomer;
import com.boomliapps.dto.DtoCustomerIU;
import com.boomliapps.entities.Address;
import com.boomliapps.entities.Customer;
import com.boomliapps.repository.CustomerRepository;
import com.boomliapps.service.ICustomerService;

@Service
public class CustomerSeviceImpl implements ICustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public DtoCustomer getCustomerById(Long Id) {
		Optional<Customer> optCustomer = customerRepository.findById(Id);
		if (optCustomer.isEmpty()) {
			return null;
		}

		Customer customer = optCustomer.get();
		Address address = optCustomer.get().getAddress();
		DtoCustomer dtoCustomer = new DtoCustomer();
		DtoAddress dtoAddress = new DtoAddress();
		BeanUtils.copyProperties(customer, dtoCustomer);
		BeanUtils.copyProperties(address, dtoAddress);
		dtoCustomer.setDtoAddress(dtoAddress);
		return dtoCustomer;
	}

	@Override
	public List<DtoCustomer> getCustomerList() {
		List<Customer> customers = customerRepository.findAll();
		List<DtoCustomer> dtoCustomers = new ArrayList<>();
		customers.forEach(it -> {
			DtoCustomer dtoCustomer = new DtoCustomer();
			DtoAddress dtoAddress = new DtoAddress();
			BeanUtils.copyProperties(it, dtoCustomer);
			BeanUtils.copyProperties(it.getAddress(), dtoAddress);
			dtoCustomer.setDtoAddress(dtoAddress);
			dtoCustomers.add(dtoCustomer);
		});
		return dtoCustomers;
	}

	@Override
	public DtoCustomer postCustomer(DtoCustomerIU dtoCustomerIU) {
		// DTO'dan Entity'ye manuel aktarım
		Address address = new Address();
		address.setDescription(dtoCustomerIU.getDtoAddressIU().getDescription());

		Customer customer = new Customer();
		customer.setName(dtoCustomerIU.getName());
		customer.setAddress(address);

		// Cascade yoksa önce adresi kaydet:
		// addressRepository.save(address);

		// Customer + Address birlikte kaydedilir (Cascade.ALL varsa)
		customer = customerRepository.save(customer);

		// Entity'den DTO'ya dönüşüm
		DtoAddress dtoAddress = new DtoAddress(address.getId(), address.getDescription());
		DtoCustomer dtoCustomer = new DtoCustomer(customer.getId(), customer.getName(), dtoAddress);

		return dtoCustomer;
	}

	@Override
	public DtoCustomer putCustomer(Long id, DtoCustomerIU dtoCustomerIU) {
		Customer dbCustomer = customerRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Customer not found"));

		dbCustomer.setName(dtoCustomerIU.getName());

		// Mevcut adres nesnesini alıp güncelle
		Address dbAddress = dbCustomer.getAddress();
		dbAddress.setDescription(dtoCustomerIU.getDtoAddressIU().getDescription());

		// Kayıt işlemi (Cascade varsa Address otomatik kaydolur)
		dbCustomer.setAddress(dbAddress);
		customerRepository.save(dbCustomer);
		DtoAddress dtoAddress = new DtoAddress(dbAddress.getId(), dbAddress.getDescription());
		DtoCustomer dtoCustomer = new DtoCustomer(dbCustomer.getId(), dbCustomer.getName(), dtoAddress);

		return dtoCustomer;

	}

}
