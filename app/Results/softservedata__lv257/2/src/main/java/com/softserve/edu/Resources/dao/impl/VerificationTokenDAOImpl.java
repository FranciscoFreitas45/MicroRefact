package com.softserve.edu.Resources.dao.impl;
 import com.softserve.edu.Resources.dao.VerificationTokenDAO;
import com.softserve.edu.Resources.entity.VerificationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.invoke.MethodHandles;
@Repository("verificationTokenDAO")
@Transactional
public class VerificationTokenDAOImpl extends GenericDAOImpl<VerificationToken, Long>implements VerificationTokenDAO{

 static  Logger LOGGER;

@PersistenceContext
 private  EntityManager entityManager;

protected VerificationTokenDAOImpl() {
    super(VerificationToken.class, LOGGER);
}
@Override
public VerificationToken findByToken(String token){
    Query query = entityManager.createQuery("select i from VerificationToken i where i.token = :token").setParameter("token", token);
    VerificationToken verificationToken;
    try {
        verificationToken = (VerificationToken) query.getSingleResult();
    } catch (Exception e) {
        return null;
    }
    return verificationToken;
}


public VerificationToken getVerificationToken(Long id69IL)

public User setVerificationToken(Long id69IL,VerificationToken verificationToken)

}