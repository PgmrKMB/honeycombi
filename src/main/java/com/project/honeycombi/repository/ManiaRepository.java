package com.project.honeycombi.repository;

import com.project.honeycombi.model.Mania;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ManiaRepository extends JpaRepository<Mania, Long> {

    @Query(
        nativeQuery = true,
        value = " update mania " +
                " set `m_count` = `m_count` + 1 " +
                " where m_id = ?1 ")
    void count(Long mId);
    
}
