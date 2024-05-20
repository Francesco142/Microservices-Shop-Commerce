package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.entity.Videogame;

public interface VideogameRepository extends JpaRepository<Videogame, Integer> {

}
