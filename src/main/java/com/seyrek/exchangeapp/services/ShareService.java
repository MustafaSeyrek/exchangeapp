package com.seyrek.exchangeapp.services;

import com.seyrek.exchangeapp.entities.Share;
import com.seyrek.exchangeapp.repositories.ShareRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShareService {
    private final ShareRepository shareRepository;

    public List<Share> getAllShares() {
        return shareRepository.findAll();
    }

    public Share getShareByCode(String code) {
        return shareRepository.findByCode(code);
    }

    public Share createShare(Share share) {
        share.setCreatedDate(new Date());
        return shareRepository.save(share);
    }

    public Share updateShareByCode(String code, Share newShare) {
        Optional<Share> share = Optional.ofNullable(shareRepository.findByCode(code));
        if (share.isPresent()) {
            Share foundShare = share.get();
            foundShare.setRate(newShare.getRate());
            foundShare.setUpdatedDate(new Date());
            foundShare.setUpdatedId(newShare.getUpdatedId());
            shareRepository.save(foundShare);
            return foundShare;
        } else {
            return null;
        }
    }
}
