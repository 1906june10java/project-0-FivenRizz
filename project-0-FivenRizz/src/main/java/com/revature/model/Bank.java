package com.revature.model;

public class Bank implements Comparable<Bank>{
/*
 * 
Name       Null?    Type          
---------- -------- ------------- 
B_ID       NOT NULL NUMBER        
B_USERNAME NOT NULL VARCHAR2(100) 
B_PASSWORD NOT NULL VARCHAR2(100) 
B_BALANCE           NUMBER     
 */
	
	private long id;
	private String username;
	private String password;
	private long balance;

	public Bank() {}

	public Bank(long id, String username, String password, long balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.balance = balance;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (balance ^ (balance >>> 32));
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		if (balance != other.balance)
			return false;
		if (id != other.id)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bank [id=" + id + ", username=" + username + ", password=" + password + ", balance=" + balance + "]";
	}

	@Override
	public int compareTo(Bank o) {
		return new Long(this.id).compareTo(o.id);
	}
	
	
}
