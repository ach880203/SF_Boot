package com.example.jpa2.repository;

import com.example.jpa2.domain.Member2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member2, Integer> {
}
