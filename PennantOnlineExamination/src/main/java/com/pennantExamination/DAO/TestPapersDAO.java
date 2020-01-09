package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.TestPapersModel;
import com.pennantExamination.pojos.TestSegmentModel;

public interface TestPapersDAO{

	public List<TestPapersModel> findAll();
	
	public TestPapersModel findById();
	
	public void insert(TestPapersModel tpm);
	
	public void update(TestPapersModel tpm);
	
	public void testpapersDelete(int id);
	
	public List<TestSegmentModel> getTestSegmentList();
}
