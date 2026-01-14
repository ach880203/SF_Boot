package com.example.jpa2.service;

import com.example.jpa2.domain.Member2;
import com.example.jpa2.repository.Member2Repository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Log4j2
public class Member2service {

    private Member2Repository member2Repository;

    public Member2service(Member2Repository member2Repository) {
                this.member2Repository = member2Repository;
    }

        //추가
        public void insert(Member2 member2) {
        member2Repository.save(member2);
        }

        //수정
        public void update(Member2 member2) {
        member2Repository.save(member2);
        }

        //삭제
        public void delete(int memberId) {
            member2Repository.deleteById(memberId);
        }

        //조회
        public Member2 findById(int memberId){
            Member2 member2 = member2Repository.findById(memberId)
                    .orElseThrow(() -> new NoSuchElementException("해당 회원이 없습니다."));

            return member2;
        }

        //전체 데이터 조회
        public List<Member2> findByAll(){
            return member2Repository.findAll();
        }

        //페이징 처리
        public Page<Member2> findByAll(Pageable pageable){

        Page<Member2> member2Page = member2Repository.findAll(pageable);

        return member2Page;

        }


}