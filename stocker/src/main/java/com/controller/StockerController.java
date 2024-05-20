package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.dto.ProductsDTO;
import com.model.entity.Vga;
import com.model.entity.Videogame;
import com.service.StockerService;

@RequestMapping("/api/stocker-service")
@RestController

public class StockerController {

	@Autowired
	private StockerService stockerService;

	@PostMapping("/restock")
	public ResponseEntity<?> restock(@RequestBody ProductsDTO dto) {

		List<Vga> vgaList = dto.getVgaList();
		List<Videogame> videogameList = dto.getVideogameList();
		
		System.out.println("DEBUG AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\n\n");

		ProductsDTO result = stockerService.restock(vgaList, videogameList);
		
		System.out.println("DEBUG 2222222 \n");
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	@PostMapping("/checkOrder")
	public ResponseEntity<?> checkOrder(@RequestBody ProductsDTO myDTO) {

		List<Vga> vgaList = myDTO.getVgaList();
		List<Videogame> videogameList = myDTO.getVideogameList();

		vgaList = stockerService.checkVgaOrder(vgaList);
		videogameList = stockerService.checkVideogameOrder(videogameList);

		if (vgaList == null || videogameList == null) {
			return new ResponseEntity<ProductsDTO>(new ProductsDTO(vgaList, videogameList), HttpStatus.OK);
		}

		Boolean restock = stockerService.updateShop(vgaList, videogameList);

		return new ResponseEntity<ProductsDTO>(new ProductsDTO(vgaList, videogameList), HttpStatus.OK);

	}

}
