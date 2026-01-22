package com.example.shop.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberFormDto {

    private String name;
    private String email;  //회원 가입시 아이디로 사용~
    private String password;
    private String address;
}
