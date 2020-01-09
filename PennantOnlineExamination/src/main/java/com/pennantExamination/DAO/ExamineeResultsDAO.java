package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.ExamineeResultsModel;

public interface ExamineeResultsDAO {

	public List<ExamineeResultsModel> findAll();
	
	public ExamineeResultsModel findById();
	
	public void insert(ExamineeResultsModel exm);
	
	public void update(ExamineeResultsModel exm);
	
	public void examineedelete(int id);
}
