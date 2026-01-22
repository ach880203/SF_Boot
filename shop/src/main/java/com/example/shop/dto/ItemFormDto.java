package com.example.shop.dto;

import com.example.shop.constant.ItemSellStatus;
import com.example.shop.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "성품 명은 필수입니다.")
    private  String itemNm;

    @NotNull(message = "가격은 필수 입력입니다.")
    private  Integer price;

    @NotBlank(message = "상품상세는 필수입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력입니다.")
    private int stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    public Item createItem(){
        return  modelMapper.map(this, Item.class);
    }
    public static ItemFormDto of(Item item){
        return modelMapper.map(item, ItemFormDto.class);
    }

}
