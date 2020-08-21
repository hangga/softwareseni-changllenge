package com.hangga.challenge.controller;

import com.hangga.challenge.model.Transaction;
import com.hangga.challenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("transactionservice")
public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/")
    public List<Transaction> all(){
        return transactionRepository.findAll();
    }

    @GetMapping("/transaction/{transaction_id}")
    public Optional<Transaction> findtransactionByid(@PathVariable("id") Long transaction_id) {
        return transactionRepository.findById(transaction_id);
    }

    /**
     * A json list of all transaction ids that share the same type $type.
     * @param em
     * @param type
     * @return
     */
    @GetMapping("/type/{type}")
    public List<Transaction> getTransactionByType(EntityManager em, String type) {
        TypedQuery<Transaction> query = em.createQuery(
                "SELECT id FROM transaction WHERE type = :name", Transaction.class);
        return query.setParameter("name", type).getResultList();
    }

}
