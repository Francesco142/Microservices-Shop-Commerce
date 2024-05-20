package com.service;

import com.model.entity.Customer;
import com.model.entity.Invoice;

public interface BuyerService {

	Invoice insertCustomerInvoice(Customer customer, Invoice invoice);

}
