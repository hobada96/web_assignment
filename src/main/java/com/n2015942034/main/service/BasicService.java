package com.n2015942034.main.service;

import com.n2015942034.main.domain.Basic;
import com.n2015942034.main.repository.BasicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BasicService {
    private BasicRepository basicRepository;

    public BasicService(BasicRepository basicRepository) {
        this.basicRepository = basicRepository;
    }

    public List<Basic> findBasicList() {
        return basicRepository.findAll();
    }

    public Basic saveBasic(Basic saveBasic) {
        basicRepository.save(saveBasic);
        return saveBasic;
    }

    public void deleteBasic(long idx) {
        basicRepository.deleteById(idx);
    }

    public Basic getOne(Long idx){
        return basicRepository.getOne(idx);
    }

    public Basic findBasicByIdx(Long idx) {
        return basicRepository.findById(idx).orElse(new Basic());
    }
}
