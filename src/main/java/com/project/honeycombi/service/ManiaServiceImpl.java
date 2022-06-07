package com.project.honeycombi.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.project.honeycombi.model.Mania;
import com.project.honeycombi.model.ManiaFile;
import com.project.honeycombi.model.ManiaRecommend;
import com.project.honeycombi.model.User;
import com.project.honeycombi.repository.ManiaFileRepository;
import com.project.honeycombi.repository.ManiaRecommendRepository;
import com.project.honeycombi.repository.ManiaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class ManiaServiceImpl implements ManiaService {

  @Autowired
  ManiaRepository maniaRepository;

  @Autowired
  ManiaFileRepository maniaFileRepository;

  @Autowired
  ManiaRecommendRepository maniaRecommendRepository;

  @Override
  public List<Mania> maniaList(int page) {
    Page<Mania> p = maniaRepository.findAll(PageRequest.of(page - 1, 5, Sort.Direction.DESC, "createDate"));

    List<Mania> list = p.getContent();

    return list;

  }

  @Override
  public void ManiaWrite(Mania mania, HttpSession session, MultipartHttpServletRequest mRequest) {

    User user = (User) session.getAttribute("user");
    mania.setUser(user);
    mania.setCreateDate(new Date());
    maniaRepository.save(mania);

    /* 첨부파일 저장 및 DB 저장 */
    Iterator<String> iter = mRequest.getFileNames();
    while (iter.hasNext()) {
      String inputName = iter.next();
      List<MultipartFile> mFiles = mRequest.getFiles(inputName);
      for (MultipartFile mFile : mFiles) {
        String oName = mFile.getOriginalFilename();
        if (oName == null || oName.equals("")) {
          break;
        }

        /* 중복파일 검사 - 파일명 변경 */
        File f = new File("c:/study/" + oName);
        String sName = "";

        if (f.isFile()) { // 파일이 존재하는가?
          String fileName = oName.substring(0, oName.lastIndexOf("."));
          String fileExt = oName.substring(oName.lastIndexOf("."));
          sName = fileName + System.currentTimeMillis() + fileExt;
        } else {
          sName = oName;
        }

        try {
          ManiaFile mf = new ManiaFile();
          mf.setOriginalFileName(oName);
          mf.setSaveFileName(sName);
          mf.setMania(mania);
          maniaFileRepository.save(mf);

          mFile.transferTo(new File("c:/study/" + sName));
        } catch (IllegalStateException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }

  }

  @Override
  public Optional<Mania> maniaDetail(Long mId) {

    Optional<Mania> opt = maniaRepository.findById(mId);

    return opt;
  }

  @Override
  public List<ManiaFile> download(Mania mania) {

    List<ManiaFile> fList = maniaFileRepository.findByMania(mania);
    return fList;
  }

  @Override
  public void delete(Long mId) {
    maniaRepository.deleteById(mId);
  }

  @Override
  public Optional<Mania> update(Long mId) {
    Optional<Mania> opt = maniaRepository.findById(mId);
    
    return opt;
  }

  @Override
  public void updatePost(Long mId, Mania mania) {
    Optional<Mania> opt = maniaRepository.findById(mId);

    if(opt.isPresent()){
      Mania dbmania = opt.get();
      dbmania.setMTitle(mania.getMTitle());
      dbmania.setMContent(mania.getMContent());
      
      maniaRepository.save(dbmania);
    }

  }

  @Override
  public void count(Long mId) {
    
    maniaRepository.count(mId);

  }

  @Override
  public void mrcmd(Long mId, Long uId) {
    
    Mania maniarc = new Mania();
    maniarc.setMId(mId);
    
    User userrc = new User();
    userrc.setUId(uId);

    ManiaRecommend dbmrcmd = new ManiaRecommend();
    dbmrcmd.setMania(maniarc);
    dbmrcmd.setUser(userrc);

    maniaRecommendRepository.save(dbmrcmd);

  }

  @Override
  public List<Mania> list() {
    Page<Mania> p = maniaRepository.findAll(PageRequest.of(0, 3, Sort.Direction.DESC, "createDate"));
        List<Mania> mlist = p.getContent();
    return mlist;
  }

}