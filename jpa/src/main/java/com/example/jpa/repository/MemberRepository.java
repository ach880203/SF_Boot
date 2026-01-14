package com.example.jpa.repository;

import com.example.jpa.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    Member findMemberByName(String name);
    Member findByPhone(String phone);
    List<Member> findByAge(int age);
    Member findByNameAndAddress(String name, String address);
    List<Member> findByAddressLike(String address);
    List<Member> findByAddressOrderByAgeAsc(String address);

    List<Member> findByAgeGreaterThanEqualOrderByAgeDesc(int age);

    /*
     JPQL 문법
    select *
    from member
    where address like "안산시"
    order by age asc;

     */
    @Query(
        "select m from Member m where m.address like %:address% order by m.age asc "
    )
    List<Member> aaa(String address);

    /*
    select *
    from member
    where age>=13
    order by age desc;
     */
    @Query(
            "select m from Member m where m.age>= :age " +
                    " order by m.age desc"
    )
    List<Member> findByAgeOrederByDesc(int age);

    /*  nativeQuery ->
        select * from member where age>=13 order by age desc;
     */

    @Query(value = "select * from member where age>=:age order by age desc", nativeQuery = true)
    List<Member> findByAgeOrederByDesc2(int age);

}
