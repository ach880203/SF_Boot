package com.example.shop.entity;

import com.example.shop.constant.Role;
import com.example.shop.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;  //회원 가입시 아이디로 사용~
    private String password;
    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    //MemberDTO -> Member 변경(Service계층까지 전달받은 Dto를 Repository계층 Entity로 변경해서 전달)
    public static Member createMember(MemberFormDto memberFormDto,
                                      PasswordEncoder passwordEncoder){

            return Member.builder()
                    .name(memberFormDto.getName())
                    .email(memberFormDto.getEmail())
                    .password(passwordEncoder.encode(memberFormDto.getPassword())) //비밀 번호 암호화
                    .address(memberFormDto.getAddress())
                    .role(Role.USER)
                    .build();
    }
}
