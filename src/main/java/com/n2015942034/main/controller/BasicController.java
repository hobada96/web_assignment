package com.n2015942034.main.controller;

import com.n2015942034.main.domain.Basic;
import com.n2015942034.main.service.BasicService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BasicController {
    BasicService basicService;

    public BasicController(BasicService basicService) {
        this.basicService = basicService;
    }

    @PostMapping("/basic/add")
    public String addBasic(Basic basic, Model model) {
        Basic saveBasic = basicService.saveBasic(basic);
        model.addAttribute("basic", basicService.findBasicByIdx(saveBasic.getIdx()));
        return "/main/basic/basic";
    }

    @GetMapping("/basic/new")
    public String formBasic(Basic basic) {
        return "/main/basic/new";
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

    @DeleteMapping("/basic/{idx}")
    public ResponseEntity<?> deleteBasic(@PathVariable("idx") Long idx) {
        basicService.deleteBasic(idx);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @PutMapping("/basic/{idx}")
    public ResponseEntity<?> updateBasic(@PathVariable("idx") Long idx, @RequestBody Basic basic) {
        Basic persistBasic = basicService.getOne(idx);
        persistBasic.update(basic);
        basicService.saveBasic(persistBasic);
        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
