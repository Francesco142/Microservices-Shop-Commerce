package com.service;

import java.util.List;

import com.model.dto.ProductsDTO;
import com.model.entity.Vga;
import com.model.entity.Videogame;

public interface StockerService {

	ProductsDTO restock(List<Vga> vgaList, List<Videogame> videogameList);

	List<Vga> checkVgaOrder(List<Vga> vgaList);

	List<Videogame> checkVideogameOrder(List<Videogame> videogameList);

	Boolean updateShop(List<Vga> vgaList, List<Videogame> videogameList);

}
