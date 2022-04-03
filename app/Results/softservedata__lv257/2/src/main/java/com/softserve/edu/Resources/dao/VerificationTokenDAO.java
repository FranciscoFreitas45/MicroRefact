package com.softserve.edu.Resources.dao;
 import com.softserve.edu.Resources.entity.VerificationToken;
public interface VerificationTokenDAO {


public void makeTransient(VerificationToken verificationToken)
;

public VerificationToken makePersistent(VerificationToken verificationToken)
;

public VerificationToken findByToken(String token)
;

}