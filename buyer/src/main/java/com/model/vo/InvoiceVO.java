package com.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class InvoiceVO implements Serializable {

	private static final long serialVersionUID = -6977251785816635371L;

	private String customerName;
	private Timestamp createdDate;
	private Map<String, Double> cart;
	private Integer invoiceId;
	private String message = "Arrivederci e grazie!";

}
