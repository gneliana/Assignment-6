package com.assignments.assignment5.controllers;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.assignment5.models.AccountHolder;
import com.assignments.assignment5.models.CDAccount;
import com.assignments.assignment5.models.CDOffering;
import com.assignments.assignment5.models.CheckingAccount;
import com.assignments.assignment5.models.SavingsAccount;

import Exceptions.AccountNotFoundException;
import Exceptions.NegativeBalanceException;
import Exceptions.ExceedsCombinedBalanceLimitException;
import Exceptions.InterestRateOutOfBoundsException;

@RestController
public class MeritBankController {
	List <AccountHolder> accountHolders = new ArrayList<AccountHolder>();
	List <CDOffering> cdOfferings = new ArrayList<CDOffering>();
	
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/AccountHolders")
	public AccountHolder postAccountHolder(@Valid @RequestBody AccountHolder accountHolder) {
		accountHolders.add(accountHolder);
		return accountHolder;
		
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders")
	public List<AccountHolder> getAccountHolders(){
		return accountHolders;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}")
	public AccountHolder getAccountHolderById(@PathVariable int id) throws AccountNotFoundException {
		if (id > accountHolders.size() ) {
			throw new AccountNotFoundException("Account id not found");
		}
		return accountHolders.get(id-1);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public CheckingAccount postCheckingAccount(@Valid @RequestBody CheckingAccount checkingAccount, @PathVariable int id) throws NegativeBalanceException, ExceedsCombinedBalanceLimitException, AccountNotFoundException {
		if (id > accountHolders.size() ) {
			throw new AccountNotFoundException("Account id not found");
		}
		if(checkingAccount.getBalance()< 0) {
			throw new NegativeBalanceException("Balance can not be less 0");
		}
		if(accountHolders.get(id-1).getCombinedbalance() + checkingAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("Balance exceeds limit");
		}
		accountHolders.get(id-1).addCheckingAccount(checkingAccount);
		accountHolders.get(id-1).setCombinedbalance(checkingAccount.getBalance() + accountHolders.get(id-1).getCombinedbalance());
		return checkingAccount;
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public List<CheckingAccount> getCheckingAccountsById(@PathVariable int id) throws AccountNotFoundException {
		if (id > accountHolders.size() ) {
			throw new AccountNotFoundException("Account id not found");
		}
		return accountHolders.get(id-1).getCheckingAccounts();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public SavingsAccount postSavingsAccount(@Valid @RequestBody SavingsAccount savingsAccount, @PathVariable int id) throws NegativeBalanceException, ExceedsCombinedBalanceLimitException, AccountNotFoundException {
		if (id > accountHolders.size() ) {
			throw new AccountNotFoundException("Account id not found");
		}
		if(savingsAccount.getBalance()< 0) {
			throw new NegativeBalanceException("Balance can not be less 0");
		}
		if(accountHolders.get(id-1).getCombinedbalance() + savingsAccount.getBalance()> 250000) {
			throw new ExceedsCombinedBalanceLimitException("Balance exceeds limit");
		}
		accountHolders.get(id-1).addSavingsAccount(savingsAccount);
		accountHolders.get(id-1).setCombinedbalance(savingsAccount.getBalance() + accountHolders.get(id-1).getCombinedbalance());
		return savingsAccount;
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public List<SavingsAccount> getSavingsAccountsById(@PathVariable int id) throws AccountNotFoundException{
		if (id > accountHolders.size() ) {
			throw new AccountNotFoundException("Account id not found");
		}
		return accountHolders.get(id-1).getSavingsAccounts();
				
	}
	
	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	public CDAccount postCDAccoutnt(@Valid @RequestBody CDAccount cdAccount, @PathVariable int id) throws NegativeBalanceException, ExceedsCombinedBalanceLimitException, InterestRateOutOfBoundsException, AccountNotFoundException {
		if (id > accountHolders.size() ) {
			throw new AccountNotFoundException("Account id not found");
		}
		if(cdAccount.getBalance()<0){
			throw new NegativeBalanceException("Balance can not be less than 0");
		}
		if(cdAccount.getInterestRate() <= 0 || cdAccount.getInterestRate() >=1) {
			throw new InterestRateOutOfBoundsException("Intrest rate must be greater t han 0 and less than 1");
		}
		
		accountHolders.get(id-1).addCDAccount(cdAccount);
		return cdAccount;
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public List<CDAccount> getCDAccountsbyId(@PathVariable int id){
		return accountHolders.get(id-1).getCDAccounts();
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = " /CDOfferings")
	public CDOffering postCDOffering(@Valid @RequestBody CDOffering cdOffering) {
		return cdOffering;
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/CDOfferings")
	public List<CDOffering> getCDOfferings(){
		return cdOfferings;
	}
}
