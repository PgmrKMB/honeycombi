package com.project.honeycombi.repository;

import com.project.honeycombi.model.ManiaFile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManiaFileRepository extends JpaRepository<ManiaFile, Long>{
    
}
