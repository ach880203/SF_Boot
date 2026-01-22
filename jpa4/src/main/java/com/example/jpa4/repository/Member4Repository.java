package com.example.jpa4.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;

@Repository
public interface Member4Repository extends JpaRepository<Member, Integer> {

}
