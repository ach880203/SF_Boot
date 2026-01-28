package com.example.shop.service;

import com.example.shop.constant.ItemSellStatus;
import com.example.shop.dto.ItemFormDto;
import com.example.shop.entity.Item;
import com.example.shop.entity.ItemImg;
import com.example.shop.repository.ItemImgRepository;
import com.example.shop.repository.ItemRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemImgRepository itemImgRepository;

    //가짜 이미지 파일 생성
    List<MultipartFile> createMultipartFiles() throws  Exception{

        List<MultipartFile> multipartFileList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            String path = "c:/shop/item/";
            String imageName = "image" + i + ".jpg";

            MockMultipartFile mockMultipartFile = new MockMultipartFile(
                    path, imageName, "image/jpg", new byte[]{1,2,3,4,5}
            );
            multipartFileList.add(mockMultipartFile);
        }

        return multipartFileList;
    }

    @Test
    @WithMockUser(username = "amdin", roles = "ADMIN")
    public void saveItem() throws Exception{

        ItemFormDto itemFormDto = ItemFormDto.builder()
                .itemNm("테스트 상품")
                .itemSellStatus(ItemSellStatus.SELL)
                .itemDetail("테스트 상품 입니다.")
                .price(1000)
                .stockNumber(100)
                .build();

        List<MultipartFile> multipartFileList = createMultipartFiles();

        Long itemId = itemService.savedItem(itemFormDto, multipartFileList);


        List<ItemImg> itemImgList = itemImgRepository.findItemImgByItemIdOrderByIdAsc(itemId);

        log.info(itemImgList);

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException());

        assertEquals(itemImgList.get(0).getItem().getId(), item.getId());
       // assertEquals(6, item.getId());
    }

}