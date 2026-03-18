package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    @Autowired 
    private WalletService walletService;
 
    @GetMapping("/{id}")
    public Wallet getWallet(@PathVariable Long id) {
        return walletService.getWalletById(id);
    }

    @PostMapping("/{id}/deposit")
    public String deposit(@PathVariable Long id, @RequestBody Double amount) {
        walletService.deposit(id, amount);
        return "Deposit successful!";
    }

    @PostMapping("/transfer")
    public TransferResponse transfer(@RequestBody TransferRequest request) {
        return walletService.transfer(
            request.getFromId(), 
            request.getToId(), 
            request.getAmount()
        );
    }
        
    @GetMapping("/user/{userId}")
    public List<Wallet> getWalletsByUserId(@PathVariable Long userId) {
        return walletService.getWalletsByUserId(userId);
    }

    @PostMapping("/user/{userId}")
    public Wallet addWallet(@PathVariable Long userId, @RequestBody Map<String, Double> body) {
        Double balance = body.getOrDefault("balance", 0.0);
        return walletService.createWallet(userId, balance);
    }
    
    @PutMapping("update/{id}")
    public Wallet updateWallet(@PathVariable Long id, @RequestBody Wallet walletDetails) {
        return walletService.updateWallet(id, walletDetails);
    }

    @DeleteMapping("delete/{id}")
    public String deleteWallet(@PathVariable Long id) {
        walletService.deleteWallet(id);
        return "Delete wallet with id: " + id + " successful!";
    }
}
