package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

}
