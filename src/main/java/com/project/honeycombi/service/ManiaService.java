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

    void delete(Long mId);

    Optional<Mania> update(Long mId);

    void updatePost(Long mId, Mania mania);

    void count(Long mId);

    void mrcmd(Long mId, Long uId);

    List<Mania> list();

}
