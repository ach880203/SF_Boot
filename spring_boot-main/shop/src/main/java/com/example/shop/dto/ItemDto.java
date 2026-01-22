package com.example.shop.dto;

import com.example.shop.constant.ItemSellStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long id;   //상품 코드

    private String itemNm;  //상품 명

    private int price;    //가격

    private String itemDetail; //상품 상세 설명

    private String sellStatCd;

    private LocalDateTime regTime;  //등록시간
    private LocalDateTime updateTime; //수정시간
}
