package com.n2015942034.main.service;

import com.n2015942034.main.domain.Basic;
import com.n2015942034.main.domain.Profile;
import com.n2015942034.main.repository.BasicRepository;
import com.n2015942034.main.repository.ProfileRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    private final BasicRepository basicRepository;
    private final ProfileRepository profileRepository;

    public MainService(BasicRepository basicRepository, ProfileRepository profileRepository) {
        this.basicRepository = basicRepository;
        this.profileRepository = profileRepository;
    }

    public Page<Basic> findBasicList(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1, pageable.getPageSize());
        return basicRepository.findAll(pageable);
    }
    public Page<Profile> findProfileList(Pageable pageable){
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() -1, pageable.getPageSize());
        return profileRepository.findAll(pageable);
    }
}
