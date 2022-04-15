package com.project.honeycombi.service;

import java.util.List;
import java.util.Optional;

import com.project.honeycombi.model.Honey;
import com.project.honeycombi.repository.HoneyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class HoneyServiceImpl implements HoneyService {
    
    @Autowired
    HoneyRepository honeyRepository;

    @Override
    public Optional<Honey> detail(Long hId) {
        Optional<Honey> opt = honeyRepository.findById(hId);
        return opt;
    }

    @Override
    public List<Honey> list(int page) {
        Page<Honey> p = honeyRepository.findAll(PageRequest.of(page -1, 5, Sort.Direction.DESC, "createDate"));
        List<Honey> list = p.getContent();
        return list;
    }

    @Override
    public void delete(Long hId) {
        honeyRepository.deleteById(hId);
    }

    

    @Override
    public Optional<Honey> update(Long hId) {
        Optional<Honey> opt = honeyRepository.findById(hId);
        
        return opt;
    }

    @Override
    public void updatePost(Long hId, Honey honey) {
        Optional<Honey> opt = honeyRepository.findById(hId);
        Honey dbHoney = opt.get();
        dbHoney.setHSubject(honey.getHSubject());
		dbHoney.setHContent(honey.getHContent());
		honeyRepository.save(dbHoney);
        
    }

    @Override
    public void hit(Long hId) {
         honeyRepository.count(hId);
        
    }

    @Override
    public List<Honey> list() {
  
        Page<Honey> p = honeyRepository.findAll(PageRequest.of(0, 3, Sort.Direction.DESC, "createDate"));
        List<Honey> hlist = p.getContent();

        return hlist;
    }

  

    
}
