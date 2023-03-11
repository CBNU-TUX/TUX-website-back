package com.example.tuxBack.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "user")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long uid;

    @Column(length=20, nullable = false, unique = true)
    private String id;

    @Column(length=20, nullable = false)
    private String name;

    @Column(length=20, nullable = false, unique = true)
    private String studentNum;

    @Column(length = 100, nullable = false)
    private String password;

    @Email
    @Column(length = 20, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String phoneNum;

    @Builder
    public UserEntity(Long uid, String id, String name, String studentNum,
                      String password, String email, String phoneNum) {
        this.uid = uid;
        this.id = id;
        this.name = name;
        this.studentNum = studentNum;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
    }

}
