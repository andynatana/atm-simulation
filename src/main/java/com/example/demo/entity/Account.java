package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

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

    @Column(name = "ACCOUNT_NUMBER", nullable = false, unique = true)
    private String accountNumber;
    private BigDecimal balance;

    @ManyToOne
    private AccountCategory accountCategory;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "account")
    @JsonIgnore
    private List<Operation> operationList;
}
