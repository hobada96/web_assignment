package com.n2015942034.main.controller;

import com.n2015942034.main.domain.Profile;
import com.n2015942034.main.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {

    ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("/profile/add")
    public String addProfile(Profile profile, Model model) {
        profile.setCreateDatNow();
        Profile saveProfile = profileService.saveProfile(profile);
        model.addAttribute("profile", profileService.findProfileByIdx(saveProfile.getIdx()));
        return "/main/profile/profile";
    }

    @GetMapping("/profile/new")
    public String formProfile(Profile profile) {
        return "/main/profile/new";
    }

    @GetMapping("/profile/{idx}")
    public String readProfile(@PathVariable Long idx, Model model) {
        Profile foundProfile = profileService.findProfileByIdx(idx);
        if(foundProfile.getIdx() == 0){
            return "main/error";
        }
        model.addAttribute("profile", foundProfile);
        return "/main/profile/item";
    }

    @DeleteMapping("/profile/{idx}")
    public ResponseEntity<?> deleteProfile(@PathVariable("idx") Long idx) {
        profileService.deleteProfile(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PutMapping("/profile/{idx}")
    public ResponseEntity<?> updateProfile(@PathVariable("idx") Long idx, @RequestBody Profile profile) {
        Profile persistProfile = profileService.getOne(idx);
        persistProfile.update(profile);
        profileService.saveProfile(persistProfile);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
