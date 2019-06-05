package com.n2015942034.main.service;

import com.n2015942034.main.domain.Profile;
import com.n2015942034.main.repository.ProfileRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {
    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> findProfileList() {
        return profileRepository.findAll();
    }

    public Profile findProfileByIdx(Long idx) {
        return profileRepository.findById(idx).orElse(new Profile());
    }

    public Profile saveProfile(Profile saveProfile) {
        profileRepository.save(saveProfile);
        return saveProfile;
    }

    public void deleteProfile(long idx) {
        profileRepository.deleteById(idx);
    }

    public Profile getOne(Long idx) {
        return profileRepository.getOne(idx);
    }

    public Profile findBasicByIdx(Long idx) {
        return profileRepository.findById(idx).orElse(new Profile());
    }
}
