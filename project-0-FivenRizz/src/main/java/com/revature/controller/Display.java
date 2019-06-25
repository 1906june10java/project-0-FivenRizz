package com.revature.controller;

import java.util.Scanner;

import com.revature.exception.InvalidAmountException;
import com.revature.exception.NegativeBalanceException;
import com.revature.exception.UserNotFoundException;
import com.revature.model.Bank;
import com.revature.repository.BankRepositoryJdbc;

public class Display {
	
	
	public static void main(String[] args) {
		
		BankRepositoryJdbc bank = new BankRepositoryJdbc();
		Scanner user = new Scanner(System.in);
		boolean run = true;
		boolean run2 = true;
		String username = "";
		boolean login = false;
		boolean correct = true;
		while (run) {
		while (!login){
			System.out.println("Welcome!");
			System.out.println("Press 1 to login");
			System.out.println("Press 2 to exit");
			System.out.println("Press 3 to register");
			String option = user.nextLine();
		
			if (option.equals("1")) {
				run2 = true;
				System.out.println("Please input username: ");
				username = user.nextLine();
			try {
				if(bank.loginUser(username)){
					System.out.println("Please input password for " + username);
					String password = user.nextLine();
					if(bank.loginPassword(username, password)) {
						System.out.println("");
						System.out.println("Login Successful!");
						System.out.println("");
						login = true;
					}
					else {
						System.out.println("");
						System.out.println("Wrong password. Back to login page");
					}
				}
				else {
					throw new UserNotFoundException("User: " + username + " not found");
					}
				}
				catch (UserNotFoundException  e) {
					
					System.out.println("Login failed, User: " + username + " not found");
					System.out.println("");
				}
			}
			else if (option.equals("2")) {
				System.out.println("");
				System.out.println("Thank you for your business");
				login = true;
				run = false;
				run2 = false;
			}
			else if (option.equals("3")) {
				boolean first = false;
				boolean second = false;
				
				while (!second){
					
					System.out.println("Please provide a username");
					String temp_username = user.nextLine();
					second = true;
					
					while(!first) {
						System.out.println("Please provide a password");
						String temp_password = user.nextLine();
						first = true;
						
						Long id = bank.count();
						Bank temp = new Bank(id, temp_username, temp_password, 0L);
						
						if(bank.loginUser(temp_username)) {
							System.out.println("Username Taken");
							second = false;
							first = false;
						}
						else {
							bank.create(temp);
							System.out.println(temp_username + " created. Please login.");
						}
						
					}
					
				}

				
				
			}
			if(Integer.parseInt(option)>3) {
				System.out.println("");
				System.out.println("Invalid Entry");
				System.out.println("");
			}
		
		}
		
		Bank curr = bank.findByName(username);
		
		while (run2) {
			
		System.out.println("");
		System.out.println("Press 1 to check balance");
		System.out.println("Press 2 to deposit");
		System.out.println("Press 3 to withdraw");
		System.out.println("Press 4 to logout");
		String option = user.nextLine();
		
		if(option.equals("1")) {
			System.out.println("");
			System.out.println(username + " balance is " + curr.getBalance());
			System.out.println("");
			
			System.out.println("Press 1 to do more");
			System.out.println("Press 2 to logout");
			String option1 = user.nextLine();
			if(option1.equals("2")) {
				System.out.println("");
				System.out.println("Logout Successful");
				System.out.println("");
				run2 = false;
				login = false;
			}
			else if(Integer.parseInt(option1)>2){
				System.out.println("Invald entry");
			}
			
		}
		
		if(option.equals("2")) {
			
			System.out.println("");
			System.out.println("Enter amount to deposit");
			String s_dep = user.nextLine();
			try {
				try {
					Double.parseDouble(s_dep);
				}
				catch(NumberFormatException e) {
					throw new InvalidAmountException("Please enter a proper amount");
				}
				if(Integer.parseInt(s_dep)<0) {
					throw new InvalidAmountException("Please enter a proper amount");
				}
			}
			catch(InvalidAmountException e){
				System.out.println("Please enter a proper amount");
				correct = false;
			}
			if (correct) {
			Long deposit = Long.parseLong(s_dep);
			curr.setBalance(curr.getBalance() + deposit);
			if(bank.deposit(curr.getUsername(), deposit)) {
				System.out.println("");
				System.out.println("Deposit successful");
				System.out.println("");
			}
			else {
				System.out.println("Deposit failed");
				System.out.println("");
			}
				System.out.println("Press 1 to do more");
				System.out.println("Press 2 to logout");
				String option1 = user.nextLine();
				if(option1.equals("2")) {
					System.out.println("");
					System.out.println("Logout Successful");
					System.out.println("");
					run2 = false;
					login = false;
				}
		}
		}
		
		if(option.equals("3")) {
			System.out.println("");
			System.out.println("Enter amount to withdraw");
			String s_with = user.nextLine();
			try {
				try {
					Double.parseDouble(s_with);
				}
				catch(NumberFormatException e) {
					throw new InvalidAmountException("Please enter a proper amount");
				}
				if(Integer.parseInt(s_with)<0) {
					throw new InvalidAmountException("Please enter a proper amount");
				}
			}
			catch(InvalidAmountException e){
				System.out.println("Please enter a proper amount");
				correct = false;
			}
			if (correct) {
			Long withdraw = Long.parseLong(s_with);
			Long curr_balance = curr.getBalance();
			curr.setBalance(curr.getBalance() - withdraw);
			boolean negative = false;
			
			
			
			try {
				if(curr.getBalance() < 0) {
					System.out.println("");
					curr.setBalance(curr_balance);
					negative = true;
				}
				if(!negative) {
					bank.withdraw(curr.getUsername(), withdraw);
					System.out.println("");
					System.out.println("Withdrawl successful");
					System.out.println("");
				}
				else{
					throw new NegativeBalanceException("Negative balance if continued!");
				}
			}
			catch(NegativeBalanceException e) {
				System.out.println("Negative balance if continued");
				System.out.println("Returning to main menu");
				System.out.println("");
			}
				System.out.println("Press 1 to do more");
				System.out.println("Press 2 to logout");
				String option1 = user.nextLine();
				if(option1.equals("2")) {
					System.out.println("");
					System.out.println("Logout Successful");
					System.out.println("");
					run2 = false;
					login = false;
				}
		}
		}
		
		if(option.equals("4")) {
			System.out.println("");
			System.out.println("Logout Successful");
			System.out.println("");
			run2 = false;
			login = false;
		}
		}
		
		
		
	}
	}
	

}
