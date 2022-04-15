package com.project.honeycombi.repository;

import java.util.List;

import com.project.honeycombi.model.Answer;
import com.project.honeycombi.model.Vegan;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>{
    List<Answer> findByVegan(Vegan vegan);
    
}
