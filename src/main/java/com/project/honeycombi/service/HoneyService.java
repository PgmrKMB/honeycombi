package com.project.honeycombi.service;

import java.util.List;
import java.util.Optional;

import com.project.honeycombi.model.Honey;



public interface HoneyService {

    Optional<Honey> detail(Long hId);

    List<Honey> list(int page);

    void delete(Long hId);

    Optional<Honey> update(Long hId);

    void updatePost(Long hId, Honey honey);

    void hit(Long hId);



    
    
}
