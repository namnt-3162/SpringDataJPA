package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // Đánh dấu đây là một Bean
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;  

    @Transactional
    public void withdraw(long walletId, Double amount) {
        Wallet wallet = walletRepository.findById(walletId)
            .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + walletId));

        if (wallet.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds to withdraw!");
        }

        wallet.setBalance(wallet.getBalance() - amount);
    }

    @Transactional
    public void deposit(long walletId, Double amount) {
        Wallet wallet = walletRepository.findById(walletId)
            .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + walletId));
        
        wallet.setBalance(wallet.getBalance() + amount);
    }

    public Wallet getWalletById(long walletId) {
        return walletRepository.findById(walletId)
            .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + walletId));
    }   

    @Transactional
    public TransferResponse transfer(long fromId, long toId, Double amount) {

        Wallet fromWallet = walletRepository.findById(fromId)
            .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + fromId));
            
        Wallet toWallet = walletRepository.findById(toId)
            .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + toId));

        if (fromWallet.getBalance() < amount) {
            throw new RuntimeException("transfer failed: insufficient funds in wallet " + fromId);
        }

        fromWallet.setBalance(fromWallet.getBalance() - amount);
        toWallet.setBalance(toWallet.getBalance() + amount);

        return new TransferResponse(
            "transfer successful!",
            fromWallet.getBalance(), 
            toWallet.getBalance()   
        );
    }

    public List<Wallet> getWalletsByUserId(long userId) {
        return walletRepository.findByUserId(userId); 
    }

    @Transactional
    public Wallet createWallet(long userId, Double initialBalance) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User doesn't exist with id: " + userId));

        Wallet wallet = new Wallet();
        wallet.setBalance(initialBalance);
        wallet.setUser(user); 

        return walletRepository.save(wallet);
    }

    @Transactional
    public Wallet updateWallet(long id, Wallet walletDetails) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + id));
   
        if (walletDetails.getBalance() != null) {
            wallet.setBalance(walletDetails.getBalance());
        }
        
        return walletRepository.save(wallet);
    }

    @Transactional
    public void deleteWallet(long id) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Wallet not found with id: " + id));
        
        if (wallet.getBalance() > 0) {
            throw new RuntimeException("Cannot delete wallet with remaining balance (Balance: " + wallet.getBalance() + ")");
        }

        walletRepository.delete(wallet);
    }
}