package com.seyrek.exchangeapp.repositories;

import com.seyrek.exchangeapp.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepository extends JpaRepository<Share, Integer> {
    Share findByCode(String code);
}
