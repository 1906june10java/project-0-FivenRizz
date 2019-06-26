package com.revature.eval.java.core;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.revature.repository.BankRepositoryJdbc;

public class BankTest {

	private static final BankRepositoryJdbc evaluationService = new BankRepositoryJdbc();
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	@Test
	public void loginUsername(){
		final String name = "user1";
		final boolean expected = true;
	assertEquals(expected, evaluationService.loginUser(name));
	}
	
	@Test
	public void loginPassword(){
		final String name = "user2";
		final String password = "password2";
		final boolean expected = true;
	assertEquals(expected, evaluationService.loginPassword(name, password));
	}
	
	
}
