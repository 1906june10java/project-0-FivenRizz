package com.revature;

public class User {
	
	//login, logout
	private String username;
	private String password;
	private double balance;
	
	User(String username1, String password1, double balance1){
		username = username1;
		password = password1;
		balance = balance1;
	}
	
	void viewBalance() {
		System.out.println(balance);
	}
	
	public double withdraw(double take) {
		
		if(balance<take) {
			throw new IllegalArgumentException("You don't have that much money");
		}
		
		balance-=take;
		
		return balance;
	}
	
	public double deposit(double give) {
		
		balance+=give;
		
		return balance;
	}
	
}
