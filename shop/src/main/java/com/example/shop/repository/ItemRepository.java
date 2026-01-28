package com.example.shop.repository;

import com.example.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {

    Optional<Item> findItemByItemNm(String itemNm);

    List<Item> findByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String 테스트_상품1, String 테스트_상품_상세_설명5);

    List<Item> findByPriceLessThan(int i);

    List<Item> findByPriceLessThanOrderByPriceDesc(int i);

    List<Item> findByItemDetail(String 테스트_상품_상세_설명);
}
