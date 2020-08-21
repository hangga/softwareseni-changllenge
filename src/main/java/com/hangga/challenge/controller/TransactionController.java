package com.hangga.challenge.controller;

import com.hangga.challenge.model.Transaction;
import com.hangga.challenge.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<Transaction> all() {
        return transactionRepository.findAll();
    }

    @PutMapping("/transaction/{transaction_id}")
    public Transaction add(@PathVariable("id") Long transaction_id,
                           @RequestParam("amount") Double amount,
                           @RequestParam("type") String type,
                           @RequestParam("parent_id") Long parent_id) {
        Transaction transaction = new Transaction();
        transaction.setId(transaction_id);
        transaction.setParent_id(parent_id);
        transaction.setType(type);
        transaction.setAmount(amount);
        return transactionRepository.save(transaction);
    }

    @GetMapping("/transaction/{transaction_id}")
    public Optional<Transaction> findtransactionByid(@PathVariable("id") Long transaction_id) {
        return transactionRepository.findById(transaction_id);
    }

    /**
     * A json list of all transaction ids that share the same type $type.
     *
     * @param em
     * @param type
     * @return
     */
    @GetMapping("/type/{type}")
    public List<Transaction> getTransactionByType(EntityManager em, String type) {
        TypedQuery<Transaction> query = em.createQuery(
                "SELECT id FROM transaction WHERE type = :type", Transaction.class);
        return query.setParameter("type", type).getResultList();
    }


    /**
     * A sum of all transactions that are transitively linked by their parent_id to
     * $transaction_id.
     *
     * @param em
     * @param transaction_id
     * @return
     */
    @GetMapping("/sum/{transaction_id}")
    public Long getAmountSum(EntityManager em, Long transaction_id) {
        return ((Number)em.createNativeQuery("SELECT sum(amount) FROM transaction WHERE parent_id = :transaction_id")
                .getSingleResult()).longValue();
    }

}
