package com.rahul.balance;

import java.util.List;

import com.rahul.customers.Custmer_details;

public interface BalanceInterface {
public List<BalanceDetails> findAll();
	
	
	public List<BalanceDetails> search(int loan_id);
	
	public int BalanceView(BalanceDetails lm);
}
