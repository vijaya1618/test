package com.pennantExamination.DAO;

import java.util.List;

import com.pennantExamination.pojos.CityModel;

public interface CityDAO {

	public List<CityModel> findAll();
	
	public CityModel findById();
	
	public void insert(CityModel cty);
	
	public void update(CityModel cty);
	
	public void citydelete(int id);
}
