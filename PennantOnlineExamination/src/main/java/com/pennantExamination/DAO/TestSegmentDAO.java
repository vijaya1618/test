package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.TestSegmentModel;

public interface TestSegmentDAO {

	public List<TestSegmentModel> findAll();
	
	public TestSegmentModel findById();
	
	public void insert(TestSegmentModel tst);
	
	public void update(TestSegmentModel tst);
	
	public void testsegmentdelete(int id);
}
