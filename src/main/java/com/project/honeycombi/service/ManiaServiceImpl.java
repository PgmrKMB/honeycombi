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
import com.project.honeycombi.model.User;
import com.project.honeycombi.repository.ManiaFileRepository;
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

    @Override
    public List<Mania> maniaList(int page) {
        Page<Mania> p = maniaRepository.findAll(PageRequest.of(page - 1, 10, Sort.Direction.DESC, "createDate"));

        List<Mania> list = p.getContent();

        return list;

    }

    @Override
    public void ManiaWrite(Mania mania, HttpSession session, MultipartHttpServletRequest mRequest) {

        User user = (User) session.getAttribute("user");
        mania.setUser(user);
        mania.setCreateDate(new Date());
        maniaRepository.save(mania);

        Iterator<String> iter = mRequest.getFileNames();
        while (iter.hasNext()) {
            String inputName = iter.next();
            List<MultipartFile> mFiles = mRequest.getFiles(inputName);
            for (MultipartFile mFile : mFiles) {
                String oName = mFile.getOriginalFilename();
                if (oName == null || oName.equals("")) {
                    break;
                }

                File f = new File("c:/hrdkmb/SMP/honeycombi/src/main/resources/static/uploade" + oName);
                String sName = "";

                if (f.isFile()) {
                    String fileName = oName.substring(0, oName.lastIndexOf("."));
                    String fileExt = oName.substring(oName.lastIndexOf("."));
                    sName = fileName + (System.currentTimeMillis() / 1000) + fileExt;
                } else {
                    sName = oName;
                }
                try {
                    ManiaFile mf = new ManiaFile();
                    mf.setOriginalFileName(oName);
                    mf.setSaveFileName(sName);
                    mf.setMania(mania);
                    maniaFileRepository.save(mf);

                    mFile.transferTo(new File("c:/hrdkmb/SMP/honeycombi/src/main/resources/static/uploade" + oName));
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

}
