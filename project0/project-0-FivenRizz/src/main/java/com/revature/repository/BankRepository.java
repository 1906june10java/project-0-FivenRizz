package com.revature.repository;

import java.sql.SQLException;

import com.revature.model.Bank;

public interface BankRepository {

	
	/**
	 * Will insert a hero into the database table.
	 * 
	 * @param hero
	 * @return if the record was inserted
	 */
	
	public boolean create(Bank bank);
	
	/**
	 * Will insert a hero into the database table.
	 * but using a stored procedure.
	 * 
	 * @param hero
	 * @return if the record was inserted
	 */
	
	public boolean createSecure(Bank bank);
	
	public Bank findByName(String name);
	
	public boolean loginUser(String username);
	
	public boolean loginPassword(String username, String password);
	
//	public Long checkBalance(String name);
	
	public boolean deposit(String name, Long deposit);
	
	public boolean withdraw(String name, Long withdraw);
	
	public Long count();
}
