package com.n2015942034.main.controller;

import com.n2015942034.main.domain.Basic;
import com.n2015942034.main.domain.Profile;
import com.n2015942034.main.service.BasicService;
import com.n2015942034.main.service.MainService;
import com.n2015942034.main.service.ProfileService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {
    private BasicService basicService;
    private ProfileService profileService;
    private MainService mainService;

    public MainController(BasicService basicService, ProfileService profileService, MainService mainService) {
        this.basicService = basicService;
        this.profileService = profileService;
        this.mainService = mainService;
    }

    @GetMapping({"/", "basic/list", "profile/list"})
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("basicList", mainService.findBasicList(pageable));
        model.addAttribute("profileList", mainService.findProfileList(pageable));
        return "main/index";
    }

    @PostMapping("/basic/add")
    public String addBasic(Basic basic, Model model) {
        Basic saveBasic = basicService.saveBasic(basic);
        model.addAttribute("basic", basicService.findBasicByIdx(saveBasic.getIdx()));
        return "/main/basic/basic";
    }

    @PostMapping("/profile/add")
    public String addProfile(Profile profile, Model model) {
        profile.setCreateDatNow();
        Profile saveProfile = profileService.saveProfile(profile);
        model.addAttribute("profile", profileService.findProfileByIdx(saveProfile.getIdx()));
        return "/main/profile/profile";
    }

    @GetMapping("/basic/new")
    public String formBasic(Basic basic) {
        return "/main/basic/new";
    }

    @GetMapping("/profile/new")
    public String formProfile(Profile profile) {
        return "/main/profile/new";
    }

    @GetMapping("/basic/{idx}")
    public String readBasic(@PathVariable Long idx, Model model) {
        Basic foundBasic = basicService.findBasicByIdx(idx);
        if(foundBasic.getIdx() == 0) {
            return "main/error";
        }
        model.addAttribute("basic", foundBasic);
        return "/main/basic/item";
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

    @DeleteMapping("/basic/{idx}")
    public ResponseEntity<?> deleteBasic(@PathVariable("idx") Long idx) {
        basicService.deleteBasic(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/profile/{idx}")
    public ResponseEntity<?> deleteProfile(@PathVariable("idx") Long idx) {
        profileService.deleteProfile(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PutMapping("/basic/{idx}")
    public ResponseEntity<?> updateBasic(@PathVariable("idx") Long idx, @RequestBody Basic basic) {
        Basic persistBasic = basicService.getOne(idx);
        persistBasic.update(basic);
        basicService.saveBasic(persistBasic);
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
