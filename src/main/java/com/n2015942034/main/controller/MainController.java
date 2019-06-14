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
    private MainService mainService;

    public MainController(MainService mainService) {
        this.mainService = mainService;
    }

    @GetMapping({"/", "basic/list", "profile/list"})
    public String list(@PageableDefault Pageable pageable, Model model) {
        model.addAttribute("basicList", mainService.findBasicList(pageable));
        model.addAttribute("profileList", mainService.findProfileList(pageable));
        return "main/index";
    }
}
