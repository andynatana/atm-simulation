package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "CREATION_DATE")
    @CreationTimestamp
    private Timestamp creationDate;

    @Column(name = "UPDATE_DATE")
    @UpdateTimestamp
    private Timestamp updateDate;

    private String mail;

    @Column(name = "USER_PASSWORD")
    private String password;

    @OneToMany(mappedBy = "owner")
    private List<Account> accountList;
}
