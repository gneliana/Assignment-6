package com.assignments.assignment5.models;

public class CDOffering {
	int term; 
	int interestRate; 
	int balance; 
	String dateOpened; 
	
	public CDOffering() {
		this.term = 0; 
		this.interestRate = 0;
		this.balance = 0;
		this.dateOpened = "";
	}

	public int getTerm() {
		return term;
	}

	public void setTerm(int term) {
		this.term = term;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(int interestRate) {
		this.interestRate = interestRate;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getDateOpened() {
		return dateOpened;
	}

	public void setDateOpened(String dateOpened) {
		this.dateOpened = dateOpened;
	}
}