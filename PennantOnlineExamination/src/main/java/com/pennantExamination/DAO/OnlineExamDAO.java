package com.pennantExamination.DAO;

import com.pennantExamination.pojos.EduModel;
import com.pennantExamination.pojos.QuestionModel;
import com.pennantExamination.pojos.TechModel;
import com.pennantExamination.pojos.Users;

public interface OnlineExamDAO {
	
	public void TechInsert(TechModel tm);
	
      public void EducInsert(EduModel em); 
	 
      public java.util.List<QuestionModel> getQuestions();
      
      public String getAnswerById(String id);
      
      public void update(Users usr);

      public void Totalscore(int count);
}
