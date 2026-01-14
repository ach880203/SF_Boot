package com.example.jpa.repository;

import com.example.jpa.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;


    //추가
    @Test
    public void insertTest(){

        Member member = Member.builder()
                .name("길동")
                .age(2)
                .phone("111")
                .address("강동구")
                .build();

        memberRepository.save(member);
    }

    //수정
    @Test
    public void updateTest(){
        Optional<Member> optMember = memberRepository.findById(1);
        Member member = optMember.get();

        member.setName("뽀양");
        member.setAge(7);
        member.setAddress("천호동");
        log.info("-------------------------------------------------");
        memberRepository.save(member);
    }

    //삭제
    @Test
    public void deleteTest(){

        memberRepository.deleteById(1);
    }

    //전체데이타 조회
    @Test
    public void selectAll(){
        List<Member> memberList = memberRepository.findAll();

        memberList.forEach(member->log.info(member));
    }


    //조회
    @Test
    public void selectTest(){

//        Member member = memberRepository.findMemberByName("까미");
        List<Member> memberList =
                memberRepository.findByAgeGreaterThanEqualOrderByAgeDesc(13);

        memberList.forEach(member->log.info(member));
        log.info("--------------------------------------------");
        log.info(memberList);
    }

    @Test
    public void likeTest(){
        List<Member> memberList = memberRepository.findByAddressLike("%안산%");
        memberList.forEach(member -> log.info(member));
    }

    @Test
    public void orderAge(){
        //List<Member> memberList = memberRepository.findByAddressOrderByAgeAsc("안산시");
        List<Member> memberList = memberRepository.findByAgeOrederByDesc2(13);
        memberList.forEach(member->log.info(member));

    }

}