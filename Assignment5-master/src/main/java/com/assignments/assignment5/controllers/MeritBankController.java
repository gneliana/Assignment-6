package com.assignments.assignment5.controllers;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.assignments.assignment5.models.AccountHolder;
import com.assignments.assignment5.models.AccountHoldersContactDetails;
import com.assignments.assignment5.models.CDAccount;
import com.assignments.assignment5.models.CDOffering;
import com.assignments.assignment5.models.CheckingAccount;
import com.assignments.assignment5.models.SavingsAccount;
import com.assignments.assignment5.services.MeritBankService;

import Exceptions.AccountNotFoundException;
import Exceptions.ExceedsCombinedBalanceLimitException;

@RestController
public class MeritBankController {
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MeritBankService meritBankService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/AccountHolders")
	public AccountHolder addAccountHolder(@Valid @RequestBody AccountHolder accountHolder) {
		return meritBankService.addAccountHolder(accountHolder);
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders")
	public List<AccountHolder> getAccountHolders() {
		return meritBankService.getAccountHolders();
	}
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}")
	public AccountHolder getAccountHolderById(@PathVariable Integer id) throws AccountNotFoundException {
		AccountHolder ah; 
		//debug log - entering 
		try {
			//use this only when someone logs in - to have record on log of login 
			log.info("Entered /AccountHolders/{1} End Point");
			ah = meritBankService.getAccountHolderById(id);
		} catch (Exception e) {
			//error log - there's been an error + exception
			log.debug("getAccountById Started" + e);
			throw new AccountNotFoundException("Account id not found");
		}
		log.info("Entered /AccountHolders/{1} End Point");
		//debug log - returning
		return ah;
	}

	@GetMapping(value = "/ContactDetails")
	public List<AccountHoldersContactDetails> getAccountHoldersContactDetails(){
		return meritBankService.getAccountHoldersContactDetails();
	}

	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/ContactDetails/{id}")
	public AccountHoldersContactDetails postContactDetails(@Valid @RequestBody AccountHoldersContactDetails ahContactDetails,
			@PathVariable Integer id){
		return meritBankService.postContactDetails(ahContactDetails, id);
	}
	
	@ResponseStatus(HttpStatus.OK)
	@PostMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public CheckingAccount postCheckingAccount(@Valid @RequestBody CheckingAccount checkingAccount, @PathVariable Integer id) 
			throws ExceedsCombinedBalanceLimitException {
		return meritBankService.postCheckingAccount(checkingAccount, id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	public List<CheckingAccount> getCheckingAccountsById(@PathVariable Integer id) throws AccountNotFoundException {
		try {
			return meritBankService.getCheckingAccountsById(id);
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public SavingsAccount postSavingsAccount(@Valid @RequestBody SavingsAccount savingsAccount, @PathVariable int id) 
			throws ExceedsCombinedBalanceLimitException{
		return meritBankService.postSavingsAccount(savingsAccount, id);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	public List<SavingsAccount> getSavingsAccountsById(@PathVariable int id) throws AccountNotFoundException {
		return meritBankService.getSavingsAccountsById(id);
	}

	@PostMapping(value = "/AccountHolders/{id}/CDAccounts")
	public CDAccount postCDAccount(@Valid @RequestBody CDAccount cdAccount, @PathVariable int id) 
			throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
		return meritBankService.postCDAccount(cdAccount, id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	public List<CDAccount> getCDAccountsbyId(@PathVariable int id) {
		return meritBankService.getCDAccountsbyId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/CDOfferings")
	public CDOffering postCDOffering(@Valid @RequestBody CDOffering cdOffering) {
		return meritBankService.postCDOffering(cdOffering);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/CDOfferings")
	public List<CDOffering> getCDOfferings() {
		return meritBankService.getCDOfferings();
	}
	
	
}
