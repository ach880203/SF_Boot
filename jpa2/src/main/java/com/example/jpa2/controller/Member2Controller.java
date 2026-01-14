package com.example.jpa2.controller;

import com.example.jpa2.domain.Member2;
import com.example.jpa2.service.Member2service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/members2")
public class Member2Controller {

    private final Member2service member2service;

    @GetMapping("/list")
    public void getList(@PageableDefault(size=4, sort = "memberId",
        direction = Sort.Direction.DESC)Pageable pageable, Model model){

        log.info("controller pageable : ");
        log.info("pageable");
        Page<Member2> member2Page = member2service.findByAll(pageable);

        model.addAttribute("member2List", member2Page.getContent());
        model.addAttribute("page", member2Page);

    }

    //@GetMapping("/list")
    public void getlist(Model model){
        List<Member2> member2List = member2service.findByAll();
        model.addAttribute("member2List", member2List);
    }

    @GetMapping("/new")
    public void getnew(){

    }

    @PostMapping("/new")
    public String Postnew(Member2 member2){
        member2service.insert(member2);
        return "redirect:/members2/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int memberId){
        member2service.delete(memberId);
        return "redirect:/members2/list";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int memberId, Model model){

        log.info("------------------edit-----------------");
        Member2 member2 = member2service.findById(memberId);
        model.addAttribute("member2", member2);
        return "/members2/edit";
    }

    @PostMapping("/edit/{id}")
    public String editPost(@PathVariable("id") int memberId, Member2 member2){

        Member2 oldMember = member2service.findById(memberId);

        oldMember.setName(member2.getName());
        oldMember.setAge(member2.getAge());
        oldMember.setAddress(member2.getAddress());
        oldMember.setPhone(member2.getPhone());

        member2service.update(oldMember);

        return "redirect:/members2/list";

    }

}
