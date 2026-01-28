package com.example.shop.repository;

import com.example.shop.dto.MemberFormDto;
import com.example.shop.entity.Cart;
import com.example.shop.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
@Transactional
class CartRepositoryTest {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepoistory;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;

    public Member createMember(){
        MemberFormDto memberFormDto  = MemberFormDto.builder()
                .email("user2@user.com")
                .name("강산")
                .address("서울시 천호동")
                .password("1234")
                .build();
        return  Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCartAndMemberTest(){
        Member member = createMember();
        memberRepoistory.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);
        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        log.info("-------- : " + savedCart.getMember().getId());
        assertEquals(savedCart.getMember().getId(), member.getId());
        assertEquals(savedCart.getMember().getId(),member.getId());
    }
}













