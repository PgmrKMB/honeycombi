package com.project.honeycombi.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import com.project.honeycombi.model.Mania;


public interface ManiaService {

    List<Mania> maniaList(int page);

    void ManiaWrite(Mania mania, HttpSession session);

    Optional<Mania> maniaDetail(Long mId);

}
