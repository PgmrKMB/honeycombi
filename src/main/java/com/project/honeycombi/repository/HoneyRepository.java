package com.project.honeycombi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.honeycombi.model.Honey;

@Repository
public interface HoneyRepository extends JpaRepository<Honey, Long>{

    @Query(
        nativeQuery = true,
        value = " update honeycombi.honey" + 
                " set `h_hit` = `h_hit` + 1 " +
                " where h_id = ?1")
                
    void count(Long vId); 


}
