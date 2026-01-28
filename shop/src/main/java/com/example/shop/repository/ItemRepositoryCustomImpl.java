package com.example.shop.repository;

import com.example.shop.constant.ItemSellStatus;
import com.example.shop.dto.ItemSearchDto;
import com.example.shop.dto.MainItemDto;
import com.example.shop.dto.QMainItemDto;
import com.example.shop.entity.Item;
import com.example.shop.entity.QItem;
import com.example.shop.entity.QItemImg;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private BooleanExpression searchSellStatusEq(ItemSellStatus searchSellStatus) {

        // ItemSellStatus(SELL, SOLD_OUT 조건이 있으면 그 조건으로 검색, 없으면 null
        return searchSellStatus == null ? null : QItem.item.itemSellStatus.eq(searchSellStatus);
    }

    private BooleanExpression regDtsAfter(String searchDateType) {
        LocalDateTime dateTime = LocalDateTime.now();

        if (StringUtils.equals("all", searchDateType) || searchDateType == null) {
            return null;
        } else if (StringUtils.equals("1d", searchDateType)) {
            dateTime = dateTime.minusDays(1);
        } else if (StringUtils.equals("1w", searchDateType)) {
            dateTime = dateTime.minusWeeks(1);
        } else if (StringUtils.equals("1m", searchDateType)) {
            dateTime = dateTime.minusMonths(1);
        } else if (StringUtils.equals("6m", searchDateType)) {
            dateTime = dateTime.minusMonths(6);
        }

        // 등록일(regTime)이 dateTime이후 날짜만 조회해라
        return QItem.item.regTime.after(dateTime);
    }


    // searchBy 조회 조건이 상품명 or 작성자
    private BooleanExpression searchByLike(String searchBy, String searchQuery) {
        if (StringUtils.equals("itemNm", searchBy)) {
            return QItem.item.itemNm.like("%" + searchQuery + "%");
        } else if (StringUtils.equals("createBy", searchBy)) {
            return QItem.item.createBy.like("%" + searchQuery + "%");
        }
        return null;
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

       /*
        QueryResults<Item> results = queryFactory.selectFrom(QItem.item)
                .where(
                        regDtsAfter(itemSearchDto.getSearchDateType()),
                        searchSellStatusEq(itemSearchDto.getSearchSellStatus()),
                        searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery())
                )
                .orderBy(QItem.item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();
         */

        BooleanExpression cond1 = regDtsAfter(itemSearchDto.getSearchDateType());
        BooleanExpression cond2 = searchSellStatusEq(itemSearchDto.getSearchSellStatus());
        BooleanExpression cond3 = searchByLike(itemSearchDto.getSearchBy(), itemSearchDto.getSearchQuery());

        QItem item = QItem.item;

        // 페이징된 내용 조회
        List<Item> list = queryFactory.selectFrom(QItem.item).where(cond1, cond2, cond3).orderBy(item.id.desc()).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

        // 전체 조건 맞는 건수 조회
        Long total = queryFactory.select(item.count()).from(item).where(cond1, cond2, cond3).fetchOne();

        return new PageImpl<>(list, pageable, total == null ? 0 : total);
    }

    private BooleanExpression itemNmLike(String searchQuery) {
        return StringUtils.isEmpty(searchQuery) ? null : QItem.item.itemNm.like("%" + searchQuery + "%");
    }

    @Override
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;

        /*
        QueryResults<MainItemDto> results = queryFactory
                .select(
                        new QMainItemDto(
                                item.id,
                                item.itemNm,
                                item.itemDetail,
                                itemImg.imgUrl,
                                item.price
                        )
                )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.repimgYn.eq("Y"))
                .where(itemNmLike(itemSearchDto.getSearchQuery()))
                .orderBy(item.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        results.getResults();
        results.getTotal();
        */

        // 페이징된 내용 조회
        List<MainItemDto> list = queryFactory.select(new QMainItemDto(item.id, item.itemNm, item.itemDetail, itemImg.imgUrl, item.price)).from(itemImg).join(itemImg.item, item).where(itemImg.repimgYn.eq("Y")).where(itemNmLike(itemSearchDto.getSearchQuery())).offset(pageable.getOffset()).limit(pageable.getPageSize()).fetch();

        // 전체 조건 맞는 건수 조회
        Long total = queryFactory.select(item.count()).from(itemImg).join(itemImg.item, item).where(itemImg.repimgYn.eq("Y")).where(itemNmLike(itemSearchDto.getSearchQuery())).fetchOne();


        return new PageImpl<>(list, pageable, total);
    }
}
