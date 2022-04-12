package com.project.honeycombi.service;

import java.util.List;
import java.util.Optional;

import com.project.honeycombi.model.Vegan;
import com.project.honeycombi.repository.VeganRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class VeganServiceImpl implements VeganService {
    
    @Autowired
    VeganRepository veganRepository;

    @Override
    public Optional<Vegan> detail(Long vId) {
        Optional<Vegan> opt = veganRepository.findById(vId);
        return opt;
    }

    @Override
    public List<Vegan> list(int page) {
        Page<Vegan> p = veganRepository.findAll(PageRequest.of(page -1, 10, Sort.Direction.DESC, "createDate"));
        List<Vegan> list = p.getContent();
        return list;
    }

    @Override
    public String deleteById(Long vId) {
        veganRepository.deleteById(vId);
        return null;
    }

  

    
}
