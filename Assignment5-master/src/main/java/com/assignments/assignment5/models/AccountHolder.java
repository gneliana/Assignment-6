package com.assignments.assignment5.models;

import java.util.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AccountHolder {

	int id;
	@NotNull(message = "First Name can not be null")
	@NotBlank(message = "First Name can not be blank")
	String firstName;
	String middleName;
	@NotNull(message = "Last Name can not be null")
	@NotBlank(message = "Last Name can not be blank")
	String lastName;
	@NotNull(message = "SSN can not be null")
	@NotBlank(message = "SSN can not be blank")
	String SSN;
	List<CheckingAccount> checkingAccounts = new ArrayList<CheckingAccount>();
	List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
	List<CDAccount> cdAccounts = new ArrayList<CDAccount>();
	int numberOfCheckingAccounts;
	int checkingBalance;
	int numberOfSavingsAccounts;
	int savingsBalance;
	int numberOfCDAccounts;
	int cdbalance;
	double combinedbalance;
	
	
	static int nextId = 1;
	
	public AccountHolder() {
		this.id = nextId ++;
		this.firstName = "";
		this.middleName = "";
		this.lastName = "";
		this.SSN = "";
		this.numberOfCheckingAccounts = 0;
		this.checkingBalance = 0;
		this.numberOfSavingsAccounts = 0;
		this.savingsBalance = 0;
		this.numberOfCDAccounts = 0;
		this.cdbalance = 0;
		this.combinedbalance = 0;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSSN() {
		return SSN;
	}

	public void setSSN(String SSN) {
		this.SSN = SSN;
	}
	
	public int getNumberOfCheckingAccounts() {
		return numberOfCheckingAccounts;
	}

	public void setNumberOfCheckingAccounts(int numberOfCheckingAccounts) {
		this.numberOfCheckingAccounts = numberOfCheckingAccounts;
	}

	public int getCheckingBalance() {
		return checkingBalance;
	}

	public void setCheckingBalance(int checkingBalance) {
		this.checkingBalance = checkingBalance;
	}

	public int getNumberOfSavingsAccounts() {
		return numberOfSavingsAccounts;
	}

	public void setNumberOfSavingsAccounts(int numberOfSavingsAccounts) {
		this.numberOfSavingsAccounts = numberOfSavingsAccounts;
	}

	public int getSavingsBalance() {
		return savingsBalance;
	}

	public void setSavingsBalance(int savingsBalance) {
		this.savingsBalance = savingsBalance;
	}

	public int getNumberOfCDAccounts() {
		return numberOfCDAccounts;
	}

	public void setNumberOfCDAccounts(int numberOfCDAccounts) {
		this.numberOfCDAccounts = numberOfCDAccounts;
	}

	public int getCdbalance() {
		return cdbalance;
	}

	public void setCdbalance(int cdbalance) {
		this.cdbalance = cdbalance;
	}

	public double getCombinedbalance() {
		return combinedbalance;
	}

	public void setCombinedbalance(double combinedbalance) {
		this.combinedbalance = combinedbalance;
	}
	
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) {
		checkingAccounts.add(checkingAccount);
		numberOfCheckingAccounts++;
		return checkingAccount;
	}
	
	public List<CheckingAccount> getCheckingAccounts() {
		return checkingAccounts;		
	}
	
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount){
		savingsAccounts.add(savingsAccount);
		numberOfSavingsAccounts++;
		return savingsAccount;
	}
	
	public List<SavingsAccount> getSavingsAccounts(){
		return savingsAccounts;
	}
	
	public CDAccount addCDAccount(CDAccount cdAccount){
		cdAccounts.add(cdAccount);
		return cdAccount;
	}
	
	public List<CDAccount> getCDAccounts(){
		return cdAccounts;
	}
}