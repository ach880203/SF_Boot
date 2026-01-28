package com.example.shop.repository;

import com.example.shop.dto.MemberFormDto;
import com.example.shop.entity.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
//@Transactional
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("Auditing 테스트")
    @WithMockUser(username = "gildong", roles = "USER")
    public void auditingTest(){

        Member member = new Member();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("gildong 수정")
    @WithMockUser(username = "hong", roles = "USER")
    public void updateTest(){
        Member member = memberRepository.findById(2L)
                .orElseThrow(EntityNotFoundException::new);

        member.setName("까미");

        memberRepository.save(member);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void createMember(){
        MemberFormDto memberFormDto  = MemberFormDto.builder()
                //.email("user" +System.nanoTime()+ "+@user.com")
                .email("user2@user.com")
                .name("강산")
                .address("서울시 천호동")
                .password("1234")
                .build();

        Member member = Member.createMember(memberFormDto, passwordEncoder);
        memberRepository.save(member);
    }
}



