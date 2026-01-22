package com.example.demo.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class HelloController {

    @GetMapping("/hello")
    public void hello(){
        log.info("---------hello---------");
    }

    //@GetMapping("/hello")
    public void hello(@RequestParam("param1") String param1, String param2){
        log.info("param1 : " + param1);
        log.info("param2 : " + param2);
    }

    @GetMapping("/hello/{param1}/{param2}")
    public void hello2(@PathVariable("param1") String param1, @PathVariable("param2") String param2){
        log.info("param1-1 : " + param1);
        log.info("param2-2 : " + param2);
    }

    @GetMapping("/hello/{param1}")
    public void hello3(@PathVariable("param1") String param1, @RequestParam("param2") String param2){
        log.info("param1-2 : " + param1);
        log.info("param2-2 : " + param2);
    }


}
