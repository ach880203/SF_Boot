package com.example.jpa4.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member4")
public class Member4 {

    /*member_id int auto_increment primary key,
    name varchar(50) not null,
    age int,
    address varchar(100),
    phone varchar(20)*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

    @Column(nullable = false, length = 50)
    private String name;
    private int age;
    private String address;

    @Column(nullable = false, length = 13)
    private String phone;
}
