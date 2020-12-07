package com.assignments.assignment5.models;

public class SavingsAccount {

	long savingsAccountNumber;
	double balance;
	String dateOpened;
	double interestRate = 0.01;
	int id;
	static int nextSavingsAccountNumber = 1;
	
	public SavingsAccount() {
		this.balance = 0;
		this.dateOpened = "12/6/2020";
		this.interestRate = 0.01;
		this.savingsAccountNumber = nextSavingsAccountNumber++;
	}

	public long getAccountNumber() {
		return savingsAccountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.savingsAccountNumber = accountNumber;
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
}