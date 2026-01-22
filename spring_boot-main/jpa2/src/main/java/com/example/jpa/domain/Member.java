package com.example.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private int memberId;

    @Column(nullable = false, length = 50)
    private String name;
    private int age;

    @Column(nullable = false, length = 200)
    private String address;
    private String phone;

    public void updateInfo(String name, int age, String address, String phone) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }
}
