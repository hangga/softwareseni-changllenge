package com.hangga.challenge.model;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "parent_id", nullable = false)
    Long parent_id;

    @Column(name = "type", nullable = false)
    String type;

    @Column(name = "amount", nullable = false)
    Double amount;
}
