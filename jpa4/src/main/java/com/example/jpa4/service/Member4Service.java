package com.example.jpa4.service;

import com.example.jpa4.Member4DTO.Member4DTO;
import com.example.jpa4.domain.Member4;
import com.example.jpa4.repository.Member4Repository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j2
public class Member4Service {

    private Member4Repository member4Repository;

    //생성자
    public Member4Service(Member4Repository member4Repository){
        this.member4Repository = member4Repository;}


    //추가
    public void insert(Member4DTO member4DTO){
        Member4 member4 = member4DTO.toEntity();

    }

    //수정
    @Transactional
    public void update(int memberId, Member4DTO member4DTO){
        //2. 영속성 컨텍스트에서 엔티티를 조회
        Member4 member4 = member4Repository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + memberId));

        //3. 비지니스 로직을 엔티티 내부 메서드로 처리(Setter 대신 권장)
        member4.updateInfo(member4DTO.getName(), member4DTO.getAge(), member4DTO.getAddress(), member4DTO.getPhone());
    }

    interface Member4Repository extends Repository<com.example.jpa4.domain.Member4, java.lang.Integer> {

    }