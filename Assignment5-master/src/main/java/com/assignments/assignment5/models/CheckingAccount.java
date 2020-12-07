package com.assignments.assignment5.models;

public class CheckingAccount {
	long checkingAccountNumber;
	double balance;
	double interestRate = .0001;
	String dateOpened;
	static int nextCheckingAccountNumber = 1;
	
	public CheckingAccount() {
		this.checkingAccountNumber = nextCheckingAccountNumber++;
		this.balance = 0;
		this.interestRate = .0001;
		this.dateOpened = "12/6/2020";
	}
	
	
	public long getAccountNumber() {
		return checkingAccountNumber;
	}

	public void setAccountNumber(long checkingAccountNumber) {
		this.checkingAccountNumber = checkingAccountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public double getInterestRate() {
		return interestRate;
	}

	public void setInterest(double interestRate) {
		this.interestRate = interestRate;
	}

	public String getDate() {
		return dateOpened;
	}

	public void setDate(String date) {
		this.dateOpened = date;
	}



}