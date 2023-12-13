package com.seyrek.exchangeapp.controllers;

import com.seyrek.exchangeapp.entities.Portfolio;
import com.seyrek.exchangeapp.entities.Share;
import com.seyrek.exchangeapp.entities.Transaction;
import com.seyrek.exchangeapp.services.PortfolioService;
import com.seyrek.exchangeapp.services.ShareService;
import com.seyrek.exchangeapp.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@AllArgsConstructor
@RestController
@RequestMapping("/api/trade")
public class TradeController {
    private final PortfolioService portfolioService;
    private final TransactionService transactionService;
    private final ShareService shareService;

    @PostMapping("/sell/{userId}/{code}/{count}")
    public ResponseEntity<Void> sellOperation(@PathVariable int userId, @PathVariable String code, @PathVariable int count) {
        Share share = shareService.getShareByCode(code);
        if (share != null) {
            Portfolio portfolio = portfolioService.getByUserIdAndShareId(userId, share.getId());
            if (portfolio == null || (portfolio.getCount() <= 0 || portfolio.getCount() < count)) {
                return new ResponseEntity<>(BAD_REQUEST);
            } else {
                Transaction transaction = new Transaction();
                transaction.setType("SELL");
                transaction.setUserId(userId);
                transaction.setShareId(share.getId());
                transaction.setCount(count);
                transaction.setPrice(count * share.getRate());
                transactionService.createTransaction(transaction);
                portfolio.setCount(portfolio.getCount() - count);
                portfolioService.updatePortfolioById(portfolio.getId(), portfolio);
                return new ResponseEntity<>(OK);
            }
        } else {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }

    @PostMapping("/buy/{userId}/{code}/{count}")
    public ResponseEntity<Void> buyOperation(@PathVariable int userId, @PathVariable String code, @PathVariable int count) {
        Share share = shareService.getShareByCode(code);
        if (share != null) {
            Portfolio portfolio = portfolioService.getByUserIdAndShareId(userId, share.getId());
            if (portfolio == null) {
                Portfolio temp = new Portfolio();
                temp.setShareId(share.getId());
                temp.setUserId(userId);
                temp.setCount(count);
                portfolioService.createPortfolio(temp);
            } else {
                portfolio.setCount(portfolio.getCount() + count);
                portfolioService.updatePortfolioById(portfolio.getId(), portfolio);
            }
            Transaction transaction = new Transaction();
            transaction.setType(("BUY"));
            transaction.setUserId(userId);
            transaction.setShareId(share.getId());
            transaction.setCount(count);
            transaction.setPrice(count * share.getRate());
            transactionService.createTransaction(transaction);
            return new ResponseEntity<>(OK);
        } else {
            return new ResponseEntity<>(BAD_REQUEST);
        }
    }
}
