package com.project.honeycombi.service;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.Mania;
import com.project.honeycombi.model.User;
import com.project.honeycombi.repository.ManiaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

public class ManiaServiceImpl implements ManiaService {

    @Autowired
    ManiaRepository maniaRepository;

    @Override
    public List<Mania> maniaList(int page) {
        Page<Mania> p = maniaRepository.findAll(PageRequest.of(page - 1, 10, Sort.Direction.DESC, "createDate"));

        List<Mania> list = p.getContent();

        return list;

    }

    @Override
    public void ManiaWrite(Mania mania, HttpSession session) {

        User user = (User) session.getAttribute("user");
        mania.setUser(user);
        mania.setCreateDate(new Date());

        maniaRepository.save(mania);

    }

}
