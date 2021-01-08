package com.assignments.assignment5.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assignments.assignment5.models.SavingsAccount;

public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Integer> {

}
