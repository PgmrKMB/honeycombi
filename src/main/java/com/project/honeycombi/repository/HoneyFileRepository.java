package com.project.honeycombi.repository;

import java.util.List;

import com.project.honeycombi.model.Honey;
import com.project.honeycombi.model.HoneyFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoneyFileRepository extends JpaRepository<HoneyFile, Long> {
  
  List<HoneyFile> findByHoney(Honey honey);
}
