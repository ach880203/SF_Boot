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
                .name("조이")
                .age(4)
                .phone("010-4412-3563")
                .address("경상도")
                .build();

        memberRepository.save(member);
    }

    //수정
    @Test
    public void updateTest(){
        Optional<Member> optMember = memberRepository.findById(1);
        Member member = optMember.get();

        member.setName("강산");
        member.setAge(7);
        member.setAddress("서울시");

        memberRepository.save(member);
    }

    //삭제
    @Test
    public void deleteTest(){

        memberRepository.deleteById(1);
    }

    //전체 데이터 조회
    @Test
    public void selectAll(){
        List<Member> memberList = memberRepository.findAll();
        memberList.forEach(member -> log.info(member));

    }

    //조회
    @Test
    public void selectTest(){

        //Member member = memberRepository.findMemberByName("로미");
        //Member member = memberRepository.findMemberByPhone("010-2222-3333");
        //Member member = memberRepository.findByNameAndAddress("발칸", "경상도");
        //List<Member> member = memberRepository.findMemberByAddress("경상도");
        List<Member> member = memberRepository.findByAgeGreaterThanEqual(6);

        member.forEach(member1 -> log.info(member));
        log.info(member);
    }


}