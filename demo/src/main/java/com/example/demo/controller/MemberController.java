package com.example.demo.controller;

import com.example.demo.domain.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/list")
    public void list(Model model){
        log.info("List...........");
        List<MemberDTO> getList = memberService.getList();
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
    public String updatePost(@ModelAttribute MemberDTO memberDTO){

        memberService.update(memberDTO);

        return "redirect:/member/list";
    }

    //http://localhost:8080/member/delete/7
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        memberService.deleteById(id);
        return "redirect:/member/list";
    }

}


