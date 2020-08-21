package com.hangga.challenge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "parent_id", nullable = false)
    Long parent_id;
    @Column(name = "type", nullable = false)
    String type;
    @Column(name = "amount", nullable = false)
    Double amount;

    public void setId(Long id) {
        this.id = id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
