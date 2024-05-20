package com.model.dto;

import java.io.Serializable;

import com.model.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BuyerDTO implements Serializable {

	private static final long serialVersionUID = -6173154928418348129L;

	private Customer customer;

	private ProductsDTO productsDTO;
}
