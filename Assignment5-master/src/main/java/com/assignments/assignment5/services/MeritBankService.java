package com.assignments.assignment5.services;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.assignments.assignment5.models.AccountHolder;
import com.assignments.assignment5.models.AccountHoldersContactDetails;
import com.assignments.assignment5.models.CDAccount;
import com.assignments.assignment5.models.CDOffering;
import com.assignments.assignment5.models.CheckingAccount;
import com.assignments.assignment5.models.SavingsAccount;
import com.assignments.assignment5.repository.AccountHolderRepository;
import com.assignments.assignment5.repository.AccountHoldersContactDetailsRepository;
import com.assignments.assignment5.repository.CDAccountRepository;
import com.assignments.assignment5.repository.CDOfferingRepository;
import com.assignments.assignment5.repository.CheckingAccountRepository;
import com.assignments.assignment5.repository.SavingsAccountRepository;

import Exceptions.AccountNotFoundException;
import Exceptions.ExceedsCombinedBalanceLimitException;

@Service
public class MeritBankService {
	@Autowired
	private AccountHoldersContactDetailsRepository ahContactDetailsrepository;
	@Autowired
	private AccountHolderRepository accountHolderRepository;
	@Autowired
	private SavingsAccountRepository savingsAccountRepository;
	@Autowired
	private CheckingAccountRepository checkingAccountRepository;
	@Autowired
	private CDAccountRepository cdAccountRepository;
	@Autowired
	private CDOfferingRepository cdOfferingRepository;
	
	public AccountHolder addAccountHolder(AccountHolder accountHolder) {
		return accountHolderRepository.save(accountHolder);
	}
	public List<AccountHolder> getAccountHolders(){
		return accountHolderRepository.findAll();
	}
	public AccountHolder getAccountHolderById(Integer id) throws AccountNotFoundException {
		return getById(id);
	}
	public AccountHolder getById(Integer id) {
		return accountHolderRepository.findById(id).orElse(null);
	}
	public AccountHoldersContactDetails postContactDetails(@Valid @RequestBody AccountHoldersContactDetails ahContactDetails,
			@PathVariable Integer id){
		AccountHolder ah = getById(id);
		ahContactDetails.setAccountHolder(ah);
		//accountHolderRepository.save(ah);
		ahContactDetailsrepository.save(ahContactDetails);
		return ahContactDetails;
	}
	public List<AccountHoldersContactDetails> getAccountHoldersContactDetails(){
		return ahContactDetailsrepository.findAll();
	}
	public CheckingAccount postCheckingAccount(CheckingAccount checkingAccount, Integer id) throws ExceedsCombinedBalanceLimitException{
		AccountHolder ah = getById(id);
		if (ah.getCombinedBalance() + checkingAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("Balance exceeds limit");
		}
		ah.setCheckingAccounts((Arrays.asList(checkingAccount)));
		checkingAccount.setAccountHolder(ah);
		checkingAccountRepository.save(checkingAccount);
		return checkingAccount;
	}
	public List<CheckingAccount> getCheckingAccountsById(@PathVariable Integer id){
		return getById(id).getCheckingAccounts();
	}
	
	public SavingsAccount postSavingsAccount(SavingsAccount savingsAccount, int id) throws ExceedsCombinedBalanceLimitException{
		AccountHolder ah = getById(id);
		if (ah.getCombinedBalance() + savingsAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("Balance exceeds limit");
		}
		ah.setSavingsAccounts((Arrays.asList(savingsAccount)));
		savingsAccount.setAccountHolder(ah);
		
		savingsAccountRepository.save(savingsAccount);
		return savingsAccount;
	}
	public List<SavingsAccount> getSavingsAccountsById(int id) throws AccountNotFoundException {
		return getById(id).getSavingsAccounts();
	}
	public CDAccount postCDAccount(CDAccount cdAccount, int id)
			throws AccountNotFoundException, ExceedsCombinedBalanceLimitException {
		AccountHolder ah = getById(id);
		if (ah.getCombinedBalance() + cdAccount.getBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("Balance exceeds limit");
		}
		ah.setcDAccounts(Arrays.asList(cdAccount));
		cdAccount.setAccountHolder(ah);
		cdAccountRepository.save(cdAccount);
		return cdAccount;
	}
	public List<CDAccount> getCDAccountsbyId(int id) {
		return getById(id).getcDAccounts();
	}
	public CDOffering postCDOffering(CDOffering cdOffering) {
		return cdOfferingRepository.save(cdOffering);
	}
	public List<CDOffering> getCDOfferings() {
		return cdOfferingRepository.findAll();
	}
}
