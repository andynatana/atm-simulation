package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ACCOUNT_NUMBER", nullable = false)
    private String accountNumber;
    private BigDecimal balance;

    @ManyToOne
    private AccountCategory accountCategory;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private User owner;
}
