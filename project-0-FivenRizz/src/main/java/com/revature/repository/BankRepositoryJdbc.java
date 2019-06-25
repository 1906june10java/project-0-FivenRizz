package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.model.Bank;
import com.revature.util.ConnectionUtil;

public class BankRepositoryJdbc implements BankRepository{
	

private static final Logger LOGGER = Logger.getLogger(BankRepositoryJdbc.class);
	
	@Override
	public boolean create(Bank bank) {
		LOGGER.trace("Entering create method with parameter: "+ bank);
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "INSERT INTO BANK VALUES (?, ?, ?, ?)";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(++parameterIndex, bank.getId());
			statement.setString(++parameterIndex, bank.getUsername());
			statement.setString(++parameterIndex, bank.getPassword());
			statement.setLong(++parameterIndex, bank.getBalance());
			
			if (statement.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			LOGGER.error("Could not create user.", e);
		}
		return false;
	}

	@Override
	public boolean createSecure(Bank bank) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Bank findByName(String name) {
		
		LOGGER.trace("Entering find user by name method with parameter: "+ name);
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql = "SELECT * FROM BANK WHERE B_USERNAME = ?";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setString(++parameterIndex,  name);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()){
				return new Bank (
						result.getLong("B_ID"),
						result.getString("B_USERNAME"),
						result.getString("B_PASSWORD"),
						result.getLong("B_BALANCE")
						);
			}
		}
		
		catch (SQLException e) {
			LOGGER.error("Could not find user.", e);
		}
		return null;
		
	}
	
	@Override
	public boolean loginUser(String username) {
		
		
		try(Connection connection = ConnectionUtil.getConnection()){
			
			int parameterIndex = 0;
			String sql = "SELECT * FROM BANK WHERE B_USERNAME = ?";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setString(++parameterIndex,  username);
			
			ResultSet result = statement.executeQuery();
			
			
			if(result.next()){
				return true;
			}
			else {
				LOGGER.error("Could not find the user");
			}
		}
		
		catch (SQLException e) {
			LOGGER.error("Could not find the user", e);
		}
		return false;
	}

	@Override
	public boolean loginPassword(String username, String password) {
		
		
		try(Connection connection = ConnectionUtil.getConnection()){
			
			int parameterIndex = 0;
			String sql = "SELECT * FROM BANK WHERE B_USERNAME = ?";
			
			PreparedStatement statement= connection.prepareStatement(sql);
			statement.setString(++parameterIndex,  username);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()){
				if(password.equals(result.getString("B_PASSWORD"))) {
					return true;
				}
				else {
					return false;
				}
		}
			else {
				LOGGER.error("Could not find the user");
			}
		}
		
		catch (SQLException e) {
			LOGGER.error("Could not find the user", e);
		}
		return false;
	}
	
	@Override
	public boolean deposit(String name, Long deposit) {
		// TODO Auto-generated method stub
		
		LOGGER.trace("Depositing " + deposit);
		
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql ="UPDATE BANK SET B_BALANCE = (B_BALANCE + ?) WHERE B_USERNAME  = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDouble(++parameterIndex, deposit);
			statement.setString(++parameterIndex, name);
			statement.executeUpdate();
			return true;
			
		}
		
		catch (Exception e) {
			// TODO: handle exception
			return false;
		}	
	}

	@Override
	public boolean withdraw(String name, Long withdraw) {
		
		LOGGER.trace("Withdrawing " + withdraw);
		
		try(Connection connection = ConnectionUtil.getConnection()){
			int parameterIndex = 0;
			String sql ="UPDATE BANK SET B_BALANCE = (B_BALANCE - ?) WHERE B_USERNAME  = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDouble(++parameterIndex, withdraw);
			statement.setString(++parameterIndex, name);
			statement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		// TODO Auto-generated method stub
}
	

	@Override
	public Long checkBalance(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public static void main(String[] args) {
//		BankRepository repository = new BankRepositoryJdbc();
//		repository.create(
//				new Bank(5, "AshokaShringla", "p4ssw0rd", 1000)
//			);
//		LOGGER.info(repository.login());
//		LOGGER.info(repository.findByName("user1"));
//		LOGGER.info(repository.deposit("user2", Long.parseLong("100")));
//	}

}