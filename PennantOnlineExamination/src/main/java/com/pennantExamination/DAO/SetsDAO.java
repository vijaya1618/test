package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.SetsModel;

public interface SetsDAO {

	public List<SetsModel> findAll();
	
	public SetsModel findById();
	
	public void insert(SetsModel sm);
	
	public void update(SetsModel sm);
	
	public void setsdelete(int id);
	
}
