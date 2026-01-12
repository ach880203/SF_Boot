package com.example.demo.mapper;

import com.example.demo.domain.MemberDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@Transactional //테스트 종료후 롤백 시키는 주문
@SpringBootTest
@Log4j2
class MemberMapperTest {

    @Autowired
    private MemberMapper memberMapper;

    @Test
    @DisplayName("All READ: 전체 데이타 조회")
    void findAll() {
        //이 상황에서  -> 이런 행동을 하면 -> 이런결과가 나와야한다.

        //given -> 테스트에 필요한 모든 조건을 만드는 단계

        //when -> 테스트 대상 동작 실행(실제로 검증하고 싶은 행동르 수행하는 단계)
        List<MemberDTO> list = memberMapper.findAll();

        //then -> 검증 결과 단계
        Assertions.assertNotNull(list);

        list.forEach (m -> log.info(m));
    }

    @Test
    @DisplayName("create: member insert 테스트")
    public void insertTest(){
        //given
        MemberDTO memberDTO = MemberDTO.builder()
                .name("모모")
                .age(4)
                .address("서초구")
                .phone("010-5555-4444")
                .build();
        //when
        int result = memberMapper.insert(memberDTO);

        //then

        assertEquals(1, result);
        assertNotNull(memberDTO.getMemberId());
    }

    @Test
    @DisplayName("READ: member findById 테스트")
    public  void  findByIdTest(){
        //given
        int memberId = 2;

        //when
        MemberDTO memberDTO = memberMapper.findById(memberId);

        //then
        assertNotNull(memberDTO);
        log.info(memberDTO);
    }

    @Test
    @DisplayName("UPDATE: member update 테스트")
    public void updateTest(){
        //give
        MemberDTO memberDTO = memberMapper.findById(1);

        //when
        memberDTO.setName("수정");
        memberDTO.setAge(7);

        int updated = memberMapper.update(memberDTO);

        //then
        assertEquals(1, updated);
        MemberDTO found = memberMapper.findById(1);

        assertEquals("수정", found.getName());
        assertEquals(7, found.getAge());

    }

    @Test
    @DisplayName("DELETE : member delete test")
    public void deleteTest(){
        //give
        MemberDTO memberDTO = memberMapper.findById(1);

        //when
        int result = memberMapper.delete(memberDTO.getMemberId());

        //then
        assertEquals(1, result);
        MemberDTO found = memberMapper.findById(memberDTO.getMemberId());

        assertNull(found);
    }
}