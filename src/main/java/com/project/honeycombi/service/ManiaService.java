package com.project.honeycombi.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import com.project.honeycombi.model.Mania;
import com.project.honeycombi.model.ManiaFile;

import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface ManiaService {

    List<Mania> maniaList(int page);

    void ManiaWrite(Mania mania, HttpSession session, MultipartHttpServletRequest mRequest);

    Optional<Mania> maniaDetail(Long mId);

    List<ManiaFile> download(Mania mania);

}
