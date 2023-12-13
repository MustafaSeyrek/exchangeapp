package com.seyrek.exchangeapp.services;

import com.seyrek.exchangeapp.entities.Portfolio;
import com.seyrek.exchangeapp.repositories.PortfolioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public List<Portfolio> getAllPortfolios() {
        return portfolioRepository.findAll();
    }

    public Portfolio createPortfolio(Portfolio portfolio) {
        return portfolioRepository.save(portfolio);
    }

    public Portfolio getPortfolioById(int id) {
        return portfolioRepository.findById(id).orElse(null);
    }

    public Portfolio updatePortfolioById(int id, Portfolio newPortfolio) {
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);
        if (portfolio.isPresent()) {
            Portfolio foundPortfolio = portfolio.get();
            foundPortfolio.setCount(newPortfolio.getCount());
            portfolioRepository.save(foundPortfolio);
            return foundPortfolio;
        } else {
            return null;
        }
    }

    public void deletePortfolioById(int id) {
        portfolioRepository.deleteById(id);
    }

    public Portfolio getByUserIdAndShareId(int userId, int shareId) {
        return portfolioRepository.findByUserIdAndShareId(userId, shareId);
    }
}
