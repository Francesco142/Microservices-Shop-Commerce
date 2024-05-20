package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.entity.Customer;
import com.model.entity.Invoice;
import com.repository.CustomerRepository;
import com.repository.InvoiceRepository;

@Service
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private CustomerRepository customerRepo;

	@Autowired
	private InvoiceRepository invoiceRepo;

	@Override
	public Invoice insertCustomerInvoice(Customer customer, Invoice invoice) {
		customerRepo.save(customer);
		invoice.setCustomer(customer);
		return invoiceRepo.save(invoice);
	}

}
