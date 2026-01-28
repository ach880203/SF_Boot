package com.example.shop.service;

import com.example.shop.entity.Member;
import com.example.shop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepoistory;

    public Member saveMember(Member member){
        Optional<Member> findMember = memberRepoistory.findByEmail(member.getEmail());

        if(findMember.isPresent())
            throw new IllegalStateException("이미 가입된 회원입니다.");

        return memberRepoistory.save(member);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("------------email------------------ : " + email);

        Member member = memberRepoistory.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email + "해당하는 회원이 없습니다."));

        return User.builder()
                .username(member.getName())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
