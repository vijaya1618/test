package com.rahul.Loan;

import java.util.List;

import com.rahul.Nominee.Nominee_Model;

public interface Loaninterface {
public Nominee_Model getNominee(int cust_id);
public Loan_Model getLoan_rate(Loan_Model lm);
public double getTotalvalue(double marketrate,double item_wt);
public int applyLoan(Loan_Model lm);
public int saveLoan(Loan_Model lm);
public int resubmitLoan(Loan_Model lm);
public List<Loan_Model> findAll();

public int loanapprove(Loan_Model lm);
public List<Loan_Model> getLoan_By_id(int loan_id,String loan_date);
public List<Loan_Model> psearch(int id);
public List<Loan_Model> pendingAll();
public Loan_Model getLoandetails();
}
