package com.example.jpa2.domain;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "member2")
@ToString
public class Member2 {

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
