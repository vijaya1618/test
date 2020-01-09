package com.pennantExamination.DAO;

import com.pennantExamination.pojos.Users;

public interface LogInDAO {

	public Users userAuthentication(String userName, String password);

}
