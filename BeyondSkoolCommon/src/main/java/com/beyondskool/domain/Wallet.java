package com.beyondskool.domain;

import java.io.Serializable;

public class Wallet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Wallet(){
		
	}
	
	private int id;	
	private String userName;
	private int totalPurchase;
	private int balanceRemaining;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public int getBalanceRemaining() {
		return balanceRemaining;
	}
	public void setBalanceRemaining(int balanceRemaining) {
		this.balanceRemaining = balanceRemaining;
	}
}
