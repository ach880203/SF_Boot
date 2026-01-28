package com.example.shop.repository;

import com.example.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {

    List<ItemImg> findItemImgByItemIdOrderByIdAsc(Long itemId);
}
