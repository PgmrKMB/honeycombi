package com.project.honeycombi.repository;

import com.project.honeycombi.model.Mania;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManiaRepository extends JpaRepository<Mania, Long> {
    
}
