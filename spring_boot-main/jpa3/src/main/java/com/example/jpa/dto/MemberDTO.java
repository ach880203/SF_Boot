package com.example.jpa.dto;

import com.example.jpa.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDTO {

    private Integer memberId;
    private String name;
    private int age;
    private String address;
    private String phone;

    //MemberDTO -> Member 변환 메소드
    public Member toEntity(){
        return Member.builder()
//                .memberId(this.memberId)
                .name(this.name)
                .age(this.age)
                .address(this.address)
                .phone(this.phone)
                .build();
    }

    //Member -> MemberDTO 변환 메소드
    public static MemberDTO from(Member member) {
        return MemberDTO.builder()
                .memberId(member.getMemberId())
                .name(member.getName())
                .age(member.getAge())
                .address(member.getAddress())
                .phone(member.getPhone())
                .build();
    }
}
