package com.example.member2.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private int memberId;
    private String name;
    private int age;
    private String address;
    private String phone;
}
