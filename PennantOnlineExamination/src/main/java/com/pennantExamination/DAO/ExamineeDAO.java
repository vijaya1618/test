package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.CollegeModel;
import com.pennantExamination.pojos.DegreeModel;
import com.pennantExamination.pojos.ExamineeModel;
import com.pennantExamination.pojos.SpecializationModel;

public interface ExamineeDAO {

	public List<ExamineeModel> findAll();
	
	public ExamineeModel findById();
	
	public void insert(ExamineeModel exm);
	
	public void update(ExamineeModel exm);
	
	public void examineedelete(int id);
	
	public List<DegreeModel> getDegreeList();

	public List<SpecializationModel> getSpecializationList();
	
	public List<CollegeModel> getCollegeList();
}
