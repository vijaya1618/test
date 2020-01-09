package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.DegreeModel;

public interface DegreeDAO {

	public List<DegreeModel> findAll();
	
	public DegreeModel findById();
	
	public void insert(DegreeModel dgr);
	
	public void update(DegreeModel dgr);
	
	public void degreedelete(int id);
}
