package com.example.shop.controller;

import com.example.shop.dto.ItemDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExController {

    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model model){
        ItemDto item = ItemDto.builder()
                .itemNm("테스트 상품1")
                .itemDetail("상품 상세 설명")
                .price(10000)
                .regTime(LocalDateTime.now())
                .build();
        model.addAttribute("itemDto", item);

        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model model){

        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10; i++) {
            ItemDto item = ItemDto.builder()
                    .itemNm("테스트 상품" + i)
                    .itemDetail("상품 상세 설명" + i)
                    .price(1000*i)
                    .regTime(LocalDateTime.now())
                    .build();

            itemDtoList.add(item);
        }
        model.addAttribute("itemDtoList", itemDtoList);

        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model model){
        List<ItemDto> itemDtoList = new ArrayList<>();

        for(int i=1; i<=10; i++) {
            ItemDto item = ItemDto.builder()
                    .itemNm("테스트 상품" + i)
                    .itemDetail("상품 상세 설명" + i)
                    .price(1000*i)
                    .regTime(LocalDateTime.now())
                    .build();

            itemDtoList.add(item);
        }
        model.addAttribute("itemDtoList", itemDtoList);

        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping(value = "/ex05")
    public String thymeleafExample05(Model model){
        return "thymeleafEx/thymeleafEx05";
    }

    @GetMapping("/ex07")
    public String thymeleafExample07(Model model){
        return "thymeleafEx/thymeleafEx07";
    }
}