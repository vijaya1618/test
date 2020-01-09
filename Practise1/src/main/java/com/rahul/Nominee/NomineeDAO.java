package com.rahul.Nominee;

import java.util.List;

import com.rahul.balance.BalanceDetails;

public interface NomineeDAO {
	
	public int NomineeInsert(Nominee_Model nm);
	
	public List<Nominee_Model> search(int nominee_id);

	public List<Nominee_Model> findAll();
}
