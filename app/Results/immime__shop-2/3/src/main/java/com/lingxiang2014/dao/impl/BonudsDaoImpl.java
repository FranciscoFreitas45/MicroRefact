package com.lingxiang2014.dao.impl;
 import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.BonudsDao;
import com.lingxiang2014.entity.Bonuds;
import com.lingxiang2014.entity.Bonuds.Type;
import com.lingxiang2014.entity.Member;
@Repository("bonudsDaoImpl")
public class BonudsDaoImpl extends BaseDaoImpl<Bonuds, Long>implements BonudsDao{


public BigDecimal countBalance(Type type,Date beginDate,Date endDate){
    BigDecimal money = new BigDecimal(0);
    List<Bonuds> list = findList(null, type, beginDate, endDate);
    for (Bonuds bonuds : list) {
        money = money.add(bonuds.getBalance());
    }
    return money;
}


public List<Bonuds> findList(Member member,Type type,Date beginDate,Date endDate){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Bonuds> criteriaQuery = criteriaBuilder.createQuery(Bonuds.class);
    Root<Bonuds> root = criteriaQuery.from(Bonuds.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (member != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
    }
    if (type != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("type"), type));
    }
    if (beginDate != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createDate"), beginDate));
    }
    if (endDate != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createDate"), endDate));
    }
    criteriaQuery.where(restrictions);
    return super.findList(criteriaQuery, null, null, null, null);
}


public BigDecimal count(Member member,Type type,Date beginDate,Date endDate){
    BigDecimal money = new BigDecimal(0);
    List<Bonuds> list = findList(member, type, beginDate, endDate);
    for (Bonuds bonuds : list) {
        money = money.add(bonuds.getBalance());
    }
    return money;
}


public Page<Bonuds> findPage(Member member,Pageable pageable){
    if (member == null) {
        return new Page<Bonuds>(Collections.<Bonuds>emptyList(), 0, pageable);
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Bonuds> criteriaQuery = criteriaBuilder.createQuery(Bonuds.class);
    Root<Bonuds> root = criteriaQuery.from(Bonuds.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
    return super.findPage(criteriaQuery, pageable);
}


public List<Bonuds> findToday(Type type,Member member){
    try {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Bonuds> criteriaQuery = criteriaBuilder.createQuery(Bonuds.class);
        Root<Bonuds> root = criteriaQuery.from(Bonuds.class);
        criteriaQuery.select(root);
        Predicate restrictions = criteriaBuilder.conjunction();
        if (member != null) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("member"), member));
        } else {
            return null;
        }
        DateTime now = new DateTime();
        DateTime date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 0, 0, 0);
        Date beginDate = date.toDate();
        date = new DateTime(now.getYear(), now.getMonthOfYear(), now.getDayOfMonth(), 23, 59, 59);
        Date endDate = date.toDate();
        if (type != null) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("type"), type));
        }
        if (beginDate != null) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createDate"), beginDate));
        }
        if (endDate != null) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createDate"), endDate));
        }
        criteriaQuery.where(restrictions);
        return super.findList(criteriaQuery, null, null, null, null);
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return null;
    }
}


}