package com.seyrek.exchangeapp.controllers;

import com.seyrek.exchangeapp.entities.Portfolio;
import com.seyrek.exchangeapp.services.PortfolioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {
    private final PortfolioService portfolioService;

    @GetMapping
    public ResponseEntity<List<Portfolio>> getAllPortfolios() {
        return new ResponseEntity<>(portfolioService.getAllPortfolios(), OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getPortfolioById(@PathVariable int id) {
        return new ResponseEntity<>(portfolioService.getPortfolioById(id), OK);
    }

    @PostMapping
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody Portfolio portfolio) {
        return new ResponseEntity<>(portfolioService.createPortfolio(portfolio), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updatePortfolioById(@PathVariable int id, @RequestBody Portfolio newPortfolio) {
        Portfolio portfolio = portfolioService.updatePortfolioById(id, newPortfolio);
        if (portfolio != null)
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortfolioById(@PathVariable int id) {
        portfolioService.deletePortfolioById(id);
        return new ResponseEntity<>(OK);
    }

}
