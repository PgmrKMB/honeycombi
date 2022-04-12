package com.project.honeycombi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.honeycombi.model.Vegan;

@Repository
public interface VeganRepository extends JpaRepository<Vegan, Long>{


}
