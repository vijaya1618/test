package com.pennantExamination.DAO;

import java.util.List;

import org.zkoss.zul.ListModel;

import com.pennantExamination.pojos.CollegeModel;
import com.pennantExamination.pojos.Users;

public interface RegistrationDAO {
	
	public void registerUser(Users user);

	public List<CollegeModel> getCollegeList();
}
