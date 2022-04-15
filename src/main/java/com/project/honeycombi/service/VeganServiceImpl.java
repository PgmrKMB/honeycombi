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
        Page<Vegan> p = veganRepository.findAll(PageRequest.of(page -1, 5, Sort.Direction.DESC, "createDate"));
        List<Vegan> list = p.getContent();
        return list;
    }

    @Override
    public void delete(Long vId) {
        veganRepository.deleteById(vId);
    }

    

    @Override
    public Optional<Vegan> update(Long vId) {
        Optional<Vegan> opt = veganRepository.findById(vId);
        
        return opt;
    }

    @Override
    public void updatePost(Long vId, Vegan vegan) {
        Optional<Vegan> opt = veganRepository.findById(vId);
        Vegan dbVegan = opt.get();
        dbVegan.setVSubject(vegan.getVSubject());
		dbVegan.setVContent(vegan.getVContent());
		veganRepository.save(dbVegan);
        
    }

    @Override
    public void hit(Long vId) {
         veganRepository.count(vId);
        
    }

    @Override
    public List<Vegan> list() {
        Page<Vegan> p = veganRepository.findAll(PageRequest.of(0, 3, Sort.Direction.DESC, "createDate"));
        List<Vegan> vlist = p.getContent();
        return vlist;
    }

  

    
}
