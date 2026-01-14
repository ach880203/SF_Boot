package com.example.jpa.service;

import com.example.jpa.domain.Member;
import com.example.jpa.dto.MemberDTO;
import com.example.jpa.repository.MemberRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Log4j2
public class MemberService {

    private MemberRepository memberRepository;

    //생성자 주입
    public MemberService(MemberRepository memberRepository){
        this.memberRepository  = memberRepository;
    }

    //수정
    @Transactional // 중요: 이 어노테이션이 있어야 변경 감지가 작동합니다.
    public void update(int memberId,  MemberDTO memberDTO){
        // 2. 영속성 컨텍스트에서 엔티티를 조회
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다. id=" + memberId));

        // 3. 비즈니스 로직을 엔티티 내부 메서드로 처리 (Setter 대신 권장)
        member.updateInfo(memberDTO.getName(), memberDTO.getAge(), memberDTO.getAddress(), memberDTO.getPhone());
    }

    //추가
    public void  insert(MemberDTO memberDTO){
        Member member = memberDTO.toEntity();
        memberRepository.save(member);
    }

    //삭제
    public void delete(int memberId){
        memberRepository.deleteById(memberId);
    }

    //조회
    public MemberDTO findById(int memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("해당 회원이 없습니다."));

        return  MemberDTO.from(member);
    }

    //전제 데이타 조회
    public List<Member> findByALL(){
        return memberRepository.findAll();
    }

    //전체 데이타 조회(페이징 처리 포함)
    public Page<Member> findByAll(Pageable pageable){

        Page<Member> memberPage = memberRepository.findAll(pageable);
        return memberPage;
    }


}
