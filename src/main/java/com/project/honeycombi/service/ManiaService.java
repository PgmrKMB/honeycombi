package com.project.honeycombi.service;

import java.util.List;

import com.project.honeycombi.model.Mania;

import org.springframework.beans.factory.annotation.Autowired;

public interface ManiaService {

    List<Mania> maniaList(int page);
    

}
