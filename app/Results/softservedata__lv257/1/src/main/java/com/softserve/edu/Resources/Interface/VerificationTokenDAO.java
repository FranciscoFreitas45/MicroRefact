package com.softserve.edu.Resources.Interface;
public interface VerificationTokenDAO {

   public VerificationToken makePersistent(VerificationToken verificationToken);
   public VerificationToken findByToken(String token);
   public void makeTransient(VerificationToken verificationToken);
}