package com.example.shop.repository;

import com.example.shop.constant.ItemSellStatus;
import com.example.shop.entity.Item;
import com.example.shop.entity.Member;
import com.example.shop.entity.Order;
import com.example.shop.entity.OrderItem;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Log4j2
@Transactional
class OrderItemRepositoryTest {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PersistenceContext
    private EntityManager em;

    public Item createItem(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());

        item.setUpdateTime(LocalDateTime.now());
        return item;
    }

    public Order createOrder(){
        Order order = new Order();
        for(int i=0;i<3;i++){
            Item item = createItem();
            itemRepository.save(item);
            OrderItem orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        }
        Member member = new Member();
        memberRepository.save(member);
        order.setMember(member);
        orderRepository.save(order);
        return order;
    }

    @Test
    @DisplayName("지연 로딩 테스트")
    public void lazyLoadingTest(){

        Order order = createOrder();
        Long orderItemId = order.getOrderItems().get(0).getId();
        log.info("orderItemId1 : " + orderItemId);
        em.flush();
        em.clear();

        OrderItem orderItem = orderItemRepository.findById(orderItemId)
//                .orElseThrow(()-> new EntityNotFoundException())
                .orElseThrow( EntityNotFoundException::new);

        log.info("Order Class : " + orderItem.getOrder().getMember());



    }
}