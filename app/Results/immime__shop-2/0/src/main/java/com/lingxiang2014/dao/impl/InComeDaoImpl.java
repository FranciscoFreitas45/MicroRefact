package com.lingxiang2014.dao.impl;
 import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.InComeDao;
import com.lingxiang2014.entity.InCome;
import com.lingxiang2014.entity.Member;
@Repository("inComeDaoImpl")
public class InComeDaoImpl extends BaseDaoImpl<InCome, Long>implements InComeDao{


public List<InCome> findInComeList(Date beginDate,Date endDate){
    try {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<InCome> criteriaQuery = criteriaBuilder.createQuery(InCome.class);
        Root<InCome> root = criteriaQuery.from(InCome.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (beginDate != null) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createDate"), beginDate));
        }
        if (endDate != null) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createDate"), endDate));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, null);
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


public BigDecimal findInCome(Date beginDate,Date endDate){
    BigDecimal inComeMoney = new BigDecimal(0);
    List<InCome> inComes = findInComeList(beginDate, endDate);
    for (InCome inCome : inComes) {
        inComeMoney = inComeMoney.add(inCome.getBalance());
    }
    return inComeMoney;
}


public Page<InCome> findPage(Member member,Pageable pageable){
    if (member == null) {
        return new Page<InCome>(Collections.<InCome>emptyList(), 0, pageable);
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<InCome> criteriaQuery = criteriaBuilder.createQuery(InCome.class);
    Root<InCome> root = criteriaQuery.from(InCome.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
    return super.findPage(criteriaQuery, pageable);
}


}