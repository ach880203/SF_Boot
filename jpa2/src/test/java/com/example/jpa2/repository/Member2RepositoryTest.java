package com.example.jpa2.repository;

import com.example.jpa2.domain.Member2;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Log4j2
class Member2RepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest(){

        Member2 member2 = Member2.builder()
                .name("김일성")
                .age(130)
                .address("개성")
                .phone("010-4444-5555")
                .build();

        memberRepository.save(member2);
    }

    @Test
    public void updateTest(){
        Optional<Member2> optMember2 = memberRepository.findById(1);
        Member2 member2 = optMember2.get();

        member2.setName("김유신");
        member2.setAge(260);
        member2.setPhone("010-1212-2323");

        memberRepository.save(member2);
    }

    @Test
    public void deleteTest(){
        memberRepository.deleteById(3);
    }

    @Test
    public void selectAll(){
        List<Member2> memberList2 = memberRepository.findAll();
        memberList2.forEach(member2 -> log.info(member2));

    }





}