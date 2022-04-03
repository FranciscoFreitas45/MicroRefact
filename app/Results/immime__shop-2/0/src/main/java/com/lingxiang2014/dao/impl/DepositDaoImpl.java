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
import com.lingxiang2014.dao.DepositDao;
import com.lingxiang2014.entity.Deposit;
import com.lingxiang2014.entity.Deposit.Type;
import com.lingxiang2014.entity.Member;
@Repository("depositDaoImpl")
public class DepositDaoImpl extends BaseDaoImpl<Deposit, Long>implements DepositDao{


public BigDecimal countBalance(Type type,Date beginDate,Date endDate){
    List<Deposit> list = findList(null, Type.adminRecharge, beginDate, endDate);
    List<Deposit> list1 = findList(null, Type.memberRecharge, beginDate, endDate);
    BigDecimal money = new BigDecimal(0);
    if (list != null && list.size() > 0) {
        for (Deposit deposit : list) {
            money = money.add(deposit.getBalance());
        }
    }
    if (list1 != null && list1.size() > 0) {
        for (Deposit deposit : list1) {
            money = money.add(deposit.getBalance());
        }
    }
    return money;
}


public List<Deposit> findList(Member member,Type type,Date beginDate,Date endDate){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Deposit> criteriaQuery = criteriaBuilder.createQuery(Deposit.class);
    Root<Deposit> root = criteriaQuery.from(Deposit.class);
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
    List<Deposit> list = findList(member, type, beginDate, endDate);
    for (Deposit deposit : list) {
        money = money.add(deposit.getBalance());
    }
    return money;
}


public Page<Deposit> findPage(Member member,Pageable pageable){
    if (member == null) {
        return new Page<Deposit>(Collections.<Deposit>emptyList(), 0, pageable);
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Deposit> criteriaQuery = criteriaBuilder.createQuery(Deposit.class);
    Root<Deposit> root = criteriaQuery.from(Deposit.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.get("member"), member));
    return super.findPage(criteriaQuery, pageable);
}


}