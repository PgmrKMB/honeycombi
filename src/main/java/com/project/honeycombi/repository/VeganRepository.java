package com.project.honeycombi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.honeycombi.model.Vegan;

@Repository
public interface VeganRepository extends JpaRepository<Vegan, Long>{

    @Query(
        nativeQuery = true,
        value = " update honeycombi.vegan" + 
                " set `v_hit` = `v_hit` + 1 " +
                " where v_id = ?1")
                
    void count(Long vId); 


}
