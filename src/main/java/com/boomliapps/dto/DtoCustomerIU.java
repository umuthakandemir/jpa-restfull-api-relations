package com.boomliapps.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoCustomerIU {
	private String name;
	private DtoAddressIU dtoAddressIU;
}
