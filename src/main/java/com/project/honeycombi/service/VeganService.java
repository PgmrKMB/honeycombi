package com.project.honeycombi.service;

import java.util.List;
import java.util.Optional;

import com.project.honeycombi.model.Vegan;



public interface VeganService {

    Optional<Vegan> detail(Long vId);

    List<Vegan> list(int page);

    void delete(Long vId);

    Optional<Vegan> update(Long vId);

    void updatePost(Long vId, Vegan vegan);



    
    
}
