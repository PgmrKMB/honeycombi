package com.project.honeycombi.repository;

import com.project.honeycombi.model.ManiaRecommend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManiaRecommendRepository extends JpaRepository<ManiaRecommend, Long>{

    void findByUser_uId(Long uId);
    
}
