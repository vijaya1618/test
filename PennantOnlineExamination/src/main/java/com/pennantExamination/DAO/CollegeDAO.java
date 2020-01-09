package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.CityModel;
import com.pennantExamination.pojos.CollegeModel;

public interface CollegeDAO {

	public List<CollegeModel> findAll();
	
	public CollegeModel findById();
	
	public void insert(CollegeModel clg);
	
	public void update(CollegeModel clg);

	public List<CityModel> getcityList();

	public void collegedelete(int id);

	public String getCityNamebyId(int cityId);

	
}
