package com.model.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Videogame implements Serializable {

	private static final long serialVersionUID = -246011908875917424L;

	private Integer id;

	private String name;

	private Double price;

	private Integer qnt;

}