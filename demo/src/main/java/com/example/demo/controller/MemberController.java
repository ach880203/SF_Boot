package com.example.demo.controller;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/list")
    public void list(Model model){
        log.info("List........");
        List<MemberDTO>getList = memberService.getList();
        model.addAttribute("list", getList);
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable int id, Model model){
        log.info("id : " + id);

        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "member/updateForm";
    }

    @PostMapping("/update")
    public String updatePost(MemberDTO memberDTO){

        memberService.update(memberDTO);

        return "redirect:/member/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
            memberService.deleteById(id);
        return "redirect:/member/list";
    }

    @GetMapping("/insert")
    public String insert(Model model){

        model.addAttribute("member", new MemberDTO());
        return "member/insert";
    }

    @PostMapping("/insert")
    public String insertPost(MemberDTO memberDTO){

        memberService.insert(memberDTO);

        return "redirect:/member/list";
    }



}
