package com.lingxiang2014.dao.impl;
 import javax.persistence.FlushModeType;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.dao.BankTypeDao;
import com.lingxiang2014.entity.BankType;
@Repository("bankTypeDaoImpl")
public class BankTypeDaoImpl extends BaseDaoImpl<BankType, Long>implements BankTypeDao{


public boolean usernameExists(String fullName){
    if (fullName == null) {
        return false;
    }
    String jpql = "select count(*) from BankType bankType where lower(bankType.fullName) = lower(:fullName)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("fullName", fullName).getSingleResult();
    return count > 0;
}


}