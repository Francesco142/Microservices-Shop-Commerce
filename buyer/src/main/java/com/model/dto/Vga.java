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

public class Vga implements Serializable {

	private static final long serialVersionUID = 7399039606873842077L;

	private Integer id;

	private String name;

	private Double price;

	private Integer qnt;

}