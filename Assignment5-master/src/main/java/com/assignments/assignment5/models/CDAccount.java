package com.assignments.assignment5.models;

public class CDAccount {
	long accountNumber;
	double balance;
	String dateOpened;
	double interestRate = 0.025;
	int term;
	
	public CDAccount() {
		this.accountNumber = 0 ;
		this.balance = 0;
		this.dateOpened = "";
		this.interestRate = 0;
		this.term = 0;
	}
	
	
	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(String dateOpened) {
		this.dateOpened = dateOpened;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

}