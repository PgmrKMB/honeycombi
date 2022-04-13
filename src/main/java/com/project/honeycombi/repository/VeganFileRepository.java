package com.project.honeycombi.repository;

import java.util.List;

import com.project.honeycombi.model.Vegan;
import com.project.honeycombi.model.VeganFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeganFileRepository extends JpaRepository<VeganFile, Long> {
  
  List<VeganFile> findByVegan(Vegan vegan);
}
