package com.rahul.customers;

import java.util.List;

public interface AccountService_Interface {
	
	public List<Custmer_details> findAll();
	
	
	public List<Custmer_details> search(String keyword);
	
	public int CustomerInsert(Custmer_details lm);
	
	public boolean validatecaccountno(long accountno);
}
