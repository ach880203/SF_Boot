package com.example.shop.repository;

import com.example.shop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepoistory extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}











