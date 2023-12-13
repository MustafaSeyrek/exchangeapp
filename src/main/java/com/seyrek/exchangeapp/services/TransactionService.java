package com.seyrek.exchangeapp.services;

import com.seyrek.exchangeapp.entities.Transaction;
import com.seyrek.exchangeapp.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> getAllTransactions(String type){
        if(type == null)
            return transactionRepository.findAll();
        else
            return transactionRepository.findAllByType(type);
    }

    public Transaction createTransaction(Transaction transaction){
        return transactionRepository.save(transaction);
    }
}

