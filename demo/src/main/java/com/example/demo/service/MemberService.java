package com.example.demo.service;

import com.example.demo.domain.MemberDTO;
import com.example.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public List<MemberDTO> getList(){
        return memberMapper.findAll();
    }

    public MemberDTO findById(int memberId){
        return memberMapper.findById(memberId);
    }

    public void update(MemberDTO memberDTO) {
        memberMapper.update(memberDTO);
    }

    public void deleteById(int id) {
        memberMapper.delete(id);
    }
}
