package com.lingxiang2014.dao.impl;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.PasswordProtectionDao;
import com.lingxiang2014.entity.PasswordProtection;
import com.lingxiang2014.entity.Member;
@Repository("passwordProtectionDaoImpl")
public class PasswordProtectionDaoImpl extends BaseDaoImpl<PasswordProtection, Long>implements PasswordProtectionDao{


public List<PasswordProtection> findListByMember(Member member){
    if (member == null) {
        return new ArrayList<PasswordProtection>();
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<PasswordProtection> criteriaQuery = criteriaBuilder.createQuery(PasswordProtection.class);
    Root<PasswordProtection> root = criteriaQuery.from(PasswordProtection.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.join("member"), member));
    return super.findList(criteriaQuery, null, null, null, null);
}


public Page<PasswordProtection> findPage(Pageable pageable,Member member){
    if (member == null) {
        return new Page<PasswordProtection>(Collections.<PasswordProtection>emptyList(), 0, pageable);
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<PasswordProtection> criteriaQuery = criteriaBuilder.createQuery(PasswordProtection.class);
    Root<PasswordProtection> root = criteriaQuery.from(PasswordProtection.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.join("member"), member));
    return super.findPage(criteriaQuery, pageable);
}


}