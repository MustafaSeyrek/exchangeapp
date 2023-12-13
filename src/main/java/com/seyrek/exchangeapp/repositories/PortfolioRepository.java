package com.seyrek.exchangeapp.repositories;

import com.seyrek.exchangeapp.entities.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    Portfolio findByUserIdAndShareId(int userId, int shareId);
}
