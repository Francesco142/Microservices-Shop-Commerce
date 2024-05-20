package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.model.dto.BuyerDTO;
import com.model.dto.ProductsDTO;
import com.model.dto.Vga;
import com.model.dto.Videogame;
import com.model.entity.Customer;
import com.model.entity.Invoice;
import com.model.vo.InvoiceVO;
import com.service.BuyerService;

@RequestMapping("/api/buyer-service")
@RestController
public class BuyerController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private BuyerService buyerService;

	@PostMapping("/order")
	public ResponseEntity<?> order(@RequestBody BuyerDTO buyerDTO) {

		ProductsDTO productsDTO = buyerDTO.getProductsDTO();

		String stockerEndpoint = "http://localhost:8083/api/stocker-service/checkOrder";
		
	
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<ProductsDTO> entity = new HttpEntity<>(productsDTO, headers);

		// TODO: Attenzione: ci servono le quantita' di prodotti richiesti dal Customer
		// che in teoria stanno dentro le liste all'interno del dto che arriva in input
		
		System.out.println("Prima chiamata post");

		ResponseEntity<ProductsDTO> result = restTemplate.exchange(stockerEndpoint, HttpMethod.POST, entity,
				ProductsDTO.class);
		
		System.out.println("Dopo chiamata stocker");

		List<Vga> vgaList = result.getBody().getVgaList();
		List<Videogame> videogameList = result.getBody().getVideogameList();

		if (vgaList == null || videogameList == null) {
			String message = "Ordinazione rifiutata: quantita' in magazzino non disponibili!";
			Map<String, String> resultBody = new HashMap<>();
			resultBody.put("error", message);
			return new ResponseEntity<Map<String, String>>(resultBody, HttpStatus.OK);
		}

		Double amount = 0d;
		Map<String, Double> cart = new HashMap<>();

		for (int i = 0; i < vgaList.size(); i++) {
			Integer partialQnt = productsDTO.getVgaList().get(i).getQnt();
			Double partialTotal = vgaList.get(i).getPrice() * partialQnt;
			amount += partialTotal;
			String key = vgaList.get(i).getName() + " x" + partialQnt;
			cart.put(key, partialTotal);
		}

		for (int i = 0; i < videogameList.size(); i++) {
			Integer partialQnt = productsDTO.getVideogameList().get(i).getQnt();
			Double partialTotal = videogameList.get(i).getPrice() * partialQnt;
			amount += partialTotal;
			String key = videogameList.get(i).getName() + " x" + partialQnt;
			cart.put(key, partialTotal);
		}

		// TODO:
		// 1. gestione customer (inserire/aggiornare)
		// 2. creazione/inserimento invoice
		// 3. costruzione della response

		Customer customer = buyerDTO.getCustomer();
		Invoice invoice = new Invoice();
		invoice.setAmount(amount);

		Invoice insertedInvoice = buyerService.insertCustomerInvoice(customer, invoice);

		InvoiceVO responseBody = new InvoiceVO();
		responseBody.setCustomerName(customer.getName());
		responseBody.setCreatedDate(insertedInvoice.getCreatedDate());
		responseBody.setCart(cart);
		responseBody.setInvoiceId(insertedInvoice.getId());

		return new ResponseEntity<>(responseBody, HttpStatus.OK);
	}

}
