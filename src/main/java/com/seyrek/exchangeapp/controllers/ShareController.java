package com.seyrek.exchangeapp.controllers;

import com.seyrek.exchangeapp.entities.Share;
import com.seyrek.exchangeapp.services.ShareService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/shares")
public class ShareController {
    private final ShareService shareService;

    @GetMapping
    public ResponseEntity<List<Share>> getAllShares() {
        return new ResponseEntity<>(shareService.getAllShares(), OK);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Share> getShareByCode(@PathVariable String code) {
        return new ResponseEntity<>(shareService.getShareByCode(code), OK);
    }

    @PostMapping
    public ResponseEntity<Share> createShare(@RequestBody Share share) {
        return new ResponseEntity<>(shareService.createShare(share), CREATED);
    }

    @PutMapping("/{code}")
    public ResponseEntity<Void> updateShareByCode(@PathVariable String code, @RequestBody Share newShare) {
        Share share = shareService.updateShareByCode(code, newShare);
        if (share != null) {
            return new ResponseEntity<>(OK);
        } else return new ResponseEntity<>(INTERNAL_SERVER_ERROR);
    }
}
