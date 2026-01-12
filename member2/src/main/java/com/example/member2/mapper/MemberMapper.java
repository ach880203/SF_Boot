package com.example.member2.mapper;

import com.example.member2.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {

    int insert(MemberDTO memberDTO);

    MemberDTO findById(int memberId);

    List<MemberDTO>findAll();

}
