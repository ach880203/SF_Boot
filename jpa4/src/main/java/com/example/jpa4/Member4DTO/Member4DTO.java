package com.example.jpa4.Member4DTO;

import com.example.jpa4.domain.Member4;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Member4DTO {

    /*member_id int auto_increment primary key,
    name varchar(50) not null,
    age int,
    address varchar(100),
    phone varchar(20)*/

    private Integer memberId;
    private String name;
    private int age;
    private String address;
    private String phone;

    //MemberDTO -> Member 변환 메소드
    public Member4 toEntity(){
        return Member4.builder()
                //.memberId(this.memberId)
                .name(this.name)
                .age(this.age)
                .address(this.address)
                .phone(this.phone)
                .build();
    }

    //Member -> MemberDTO 변환 메서드
    public static Member4DTO from(Member4 member4){
        return Member4DTO.builder()
                .memberId(member4.getMemberId())
                .name(member4.getName())
                .age(member4.getAge())
                .address(member4.getAddress())
                .phone(member4.getPhone())
                .build();
    }
}
