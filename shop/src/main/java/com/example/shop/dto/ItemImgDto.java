package com.example.shop.dto;

import com.example.shop.entity.ItemImg;
import lombok.*;
import org.modelmapper.ModelMapper;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemImgDto {
    private Long id;
    private String imgName; //이미지 파일명
    private String oriImgName; // 원본 파일명
    private String imgUrl; //이미지 조회 경로
    private String repimgYn; //대표이미지 여부

    /*public static itemImgDto toItemImg(ItemImg itemImg){
        itemImgDto itemImgDto = new itemImgdTo();
        itemImgDto.setId(itmeImg.getId());
        itemImgDto.setId(itmeImg.getImgName());
        itemImgDto.setId(itmeImg.getId());
    }*/

    private static ModelMapper modelMapper = new ModelMapper();
/*
* ItemImg 객체를 전달 받아서, itemImgDto로 변환
* */
    public static ItemImgDto of(ItemImg itemimg){
        return modelMapper.map(itemimg, ItemImgDto.class);
    }
}

