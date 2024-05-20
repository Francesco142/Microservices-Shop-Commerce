package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.dto.ProductsDTO;
import com.model.entity.Vga;
import com.model.entity.Videogame;
import com.repository.VgaRepository;
import com.repository.VideogameRepository;

@Service
public class StockerServiceImpl implements StockerService {

	@Autowired
	private VgaRepository vgaRepo;

	@Autowired
	private VideogameRepository videogameRepo;

	@Override
	public ProductsDTO restock(List<Vga> vgaList, List<Videogame> videogameList) {
		
		System.out.println("DEBUG 3333\n\n");
		
		System.out.println(vgaList);

		for (Vga vga : vgaList) {
			if (vga.getId() != null) {
			
				Vga dbVga = vgaRepo.findById(vga.getId()).get();
				vga = updateVgaFields(vga, dbVga);
			}
		}
		

		for (Videogame videogame : videogameList) {
			if (videogame.getId() != null) {
				Videogame dbVideogame = videogameRepo.findById(videogame.getId()).get();
				videogame = updateVideogameFields(videogame, dbVideogame);
			}
		}
		
		System.out.println("Prima update saveAll:" + vgaList );
		
		
		vgaList = vgaRepo.saveAll(vgaList);
		System.out.println("Dopo update saveAll:" + vgaList );
		
		videogameList = videogameRepo.saveAll(videogameList);

		return new ProductsDTO(vgaList, videogameList);

	}

	@Override
	public List<Vga> checkVgaOrder(List<Vga> vgaList) {
		boolean isAvailable = true;
		for (Vga vga : vgaList) {
			Vga vgaDb = vgaRepo.findById(vga.getId()).get();
			if (vgaDb.getQnt() < vga.getQnt()) {
				isAvailable = false;
				break;
			}
			vga.setQnt(-vga.getQnt());
			updateVgaFields(vga, vgaDb);
		}

		return isAvailable ? vgaList : null;
	}

	@Override
	public List<Videogame> checkVideogameOrder(List<Videogame> videogameList) {
		boolean isAvailable = true;
		for (Videogame videogame : videogameList) {
			Videogame videogameDb = videogameRepo.findById(videogame.getId()).get();
			if (videogameDb.getQnt() < videogame.getQnt()) {
				isAvailable = false;
				break;
			}
			videogame.setQnt(-videogame.getQnt());
			updateVideogameFields(videogame, videogameDb);
		}

		return isAvailable ? videogameList : null;
	}

	@Override
	public Boolean updateShop(List<Vga> vgaList, List<Videogame> videogameList) {
		vgaRepo.saveAll(vgaList);
		videogameRepo.saveAll(videogameList);
		return true;
	}

	/*
	 * METODI PRIVATI
	 */
	private Vga updateVgaFields(Vga vga, Vga dbVga) {
		if (vga.getName() == null) {
			vga.setName(dbVga.getName());
		}
		if (vga.getPrice() == null) {
			vga.setPrice(dbVga.getPrice());
		}
		vga.setQnt(dbVga.getQnt() + vga.getQnt());

		return vga;
	}

	private Videogame updateVideogameFields(Videogame videogame, Videogame dbVideogame) {
		if (videogame.getName() == null) {
			videogame.setName(dbVideogame.getName());
		}
		if (videogame.getPrice() == null) {
			videogame.setPrice(dbVideogame.getPrice());
		}
		videogame.setQnt(dbVideogame.getQnt() + videogame.getQnt());

		return videogame;
	}

}
