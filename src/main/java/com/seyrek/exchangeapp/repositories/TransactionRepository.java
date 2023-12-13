package com.seyrek.exchangeapp.repositories;

import com.seyrek.exchangeapp.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> findAllByType(String type);
}
