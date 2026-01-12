package com.example.jpa.repository;

import com.example.jpa.domain.Member;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findMemberByName(String name);
    Member findMemberByPhone(String phone);
    Member findByNameAndAddress(String name, String address);

    List<Member> findMemberByAddress(String address);

    List<Member> findByAgeGreaterThanEqual(int ageIsGreaterThan, Pageable pageable);

    List<Member> findByAgeGreaterThanEqual(int ageIsGreaterThan);
}
