package com.model.dto;

import java.io.Serializable;
import java.util.List;

import com.model.entity.Vga;
import com.model.entity.Videogame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class ProductsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7538720514434482987L;

	private List<Vga> vgaList;
	private List<Videogame> videogameList;

}
