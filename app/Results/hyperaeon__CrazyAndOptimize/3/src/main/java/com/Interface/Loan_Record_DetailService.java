package com.Interface;
public interface Loan_Record_DetailService {

   public List<Loan_Record_Detail> findCreditCardOverdue(Integer reportId);
   public List<Loan_Record_Detail> findCreditCardNoOverdue(Integer reportId);
   public List<Loan_Record_Detail> findCreditCardOverdueSixty(Integer reportId);
   public List<Loan_Record_Detail> findHouserLoadNoOverdue(Integer reportId);
   public List<Loan_Record_Detail> findHouserLoadOverdue(Integer reportId);
   public List<Loan_Record_Detail> findOtherLoadNoOverdue(Integer reportId);
   public List<Loan_Record_Detail> findOtherLoadOverdue(Integer reportId);
}