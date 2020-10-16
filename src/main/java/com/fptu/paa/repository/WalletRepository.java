package com.fptu.paa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fptu.paa.entity.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long>{

}
