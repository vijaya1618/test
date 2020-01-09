package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.DegreeModel;
import com.pennantExamination.pojos.SpecializationModel;

public interface SpecializationDAO {

	public List<SpecializationModel> findAll();
	
	public SpecializationModel findById();
	
	public void insert(SpecializationModel slz);
	
	public void update(SpecializationModel slz);

	public List<DegreeModel> getdegreeList();

	public String getDegreeNamebyId(int degreeId);
	
	public void specializationdelete(int id);
	



	
}
