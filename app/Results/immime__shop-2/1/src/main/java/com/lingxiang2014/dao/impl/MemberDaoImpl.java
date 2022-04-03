package com.lingxiang2014.dao.impl;
 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.Order;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.MemberDao;
import com.lingxiang2014.dao.MemberRankDao;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Member.Zone;
import com.lingxiang2014.entity.MemberRank;
@Repository("memberDaoImpl")
public class MemberDaoImpl extends BaseDaoImpl<Member, Long>implements MemberDao{

@Resource(name = "memberRankDaoImpl")
 private  MemberRankDao memberRankDao;


public Member findLeftChild(Member member){
    if (member == null) {
        return null;
    }
    try {
        String jpql = "select members from Member members where members.leftChildren = :member";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("member", member).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


public Page<Member> findChildrenPage(Member member,Pageable pageable){
    if (member == null) {
        return new Page<Member>(Collections.<Member>emptyList(), 0, pageable);
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
    Root<Member> root = criteriaQuery.from(Member.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.get("parent"), member));
    return super.findPage(criteriaQuery, pageable);
}


public Member findByNumber(String number){
    if (number == null) {
        return null;
    }
    try {
        String jpql = "select members from Member members where lower(members.number) = lower(:number)";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("number", number).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


public long count(MemberRank membeRank,Date beginDate,Date endDate){
    List<Member> list = find(membeRank, beginDate, endDate);
    return list == null ? 0 : list.size();
}


public Member findRightChild(Member member){
    if (member == null) {
        return null;
    }
    try {
        String jpql = "select members from Member members where members.rightChildren = :member";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("member", member).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


public boolean emailExists(String email){
    if (email == null) {
        return false;
    }
    String jpql = "select count(*) from Member members where lower(members.email) = lower(:email)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getSingleResult();
    return count > 0;
}


public boolean usernameExists(String username){
    if (username == null) {
        return false;
    }
    String jpql = "select count(*) from Member members where lower(members.username) = lower(:username)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
    return count > 0;
}


public Member findChild(Member member,Zone zone){
    if (Zone.left == zone) {
        if (member.getLeftChildren() == null) {
            return member;
        } else {
            return findChild(member.getLeftChildren(), Zone.left);
        }
    } else {
        if (member.getRightChildren() == null) {
            return member;
        } else {
            return findChild(member.getRightChildren(), Zone.left);
        }
    }
}


public BigDecimal countBalance(Integer type,Date beginDate,Date endDate){
    List<Member> list = find(null, beginDate, endDate);
    if (list != null && list.size() > 0) {
        BigDecimal money = new BigDecimal(0);
        for (Member member : list) {
            if (type == null) {
                money = money.add(member.getBalance()).add(member.getBalance1()).add(member.getBalance2()).add(member.getBalance3());
            } else if (type == 0) {
                money = money.add(member.getBalance());
            } else if (type == 1) {
                money = money.add(member.getBalance1());
            } else if (type == 2) {
                money = money.add(member.getBalance2());
            } else if (type == 3) {
                money = money.add(member.getBalance3());
            }
        }
        return money;
    } else {
        return new BigDecimal(0);
    }
}


public Member findByUsername(String username){
    if (username == null) {
        return null;
    }
    try {
        String jpql = "select members from Member members where lower(members.username) = lower(:username)";
        return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


public List<Member> find(MemberRank memberRank,Date beginDate,Date endDate){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
    Root<Member> root = criteriaQuery.from(Member.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (memberRank != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("memberRank"), memberRank));
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


public Member findLeaf(String number,Member member,Integer index,Zone zone){
    // 左边的人
    Member left = member.getLeftChildren();
    // 右边的人
    Member right = member.getRightChildren();
    Member leaf = null;
    if (left == null || right == null) {
        // 自己就是叶子节点
        leaf = member;
        if (number.equalsIgnoreCase(leaf.getNumber())) {
        } else {
            leaf = findLeaf(leaf, 0, zone);
        }
    } else {
        if (zone == null) {
            leaf = findLeaf(left, 0, zone);
            leaf = findLeaf(right, 0, zone);
        } else if (Zone.left == zone) {
            leaf = findLeaf(left, 0, zone);
        } else {
            leaf = findLeaf(right, 0, zone);
        }
    }
    return leaf;
}


public List<Member> findListByNumber(String number){
    if (number == null) {
        return Collections.<Member>emptyList();
    }
    String jpql = "select members from Member members where lower(members.email) = lower(:email)";
    return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", number).getResultList();
}


public List<Member> findTop(Member member){
    if (member == null) {
        return null;
    }
    String jqpl = "select members from Member members where members.top = :member";
    return entityManager.createQuery(jqpl, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("top", member).getResultList();
}


public List<Member> findListByEmail(String email){
    if (email == null) {
        return Collections.<Member>emptyList();
    }
    String jpql = "select members from Member members where lower(members.email) = lower(:email)";
    return entityManager.createQuery(jpql, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("email", email).getResultList();
}


public List<Member> findChildren(Member member,Zone zone){
    List<Member> list = new ArrayList<Member>();
    Member left = member.getLeftChildren();
    Member right = member.getRightChildren();
    if (Zone.left == zone) {
        if (left != null) {
            list.add(left);
            list.addAll(findChildren(left, zone));
        }
    } else if (Zone.right == zone) {
        if (left != null) {
            list.add(right);
            list.addAll(findChildren(right, zone));
        }
    } else {
        if (left != null) {
            list.add(left);
            list.addAll(findChildren(left, zone));
        }
        if (left != null) {
            list.add(right);
            list.addAll(findChildren(right, zone));
        }
    }
    return list;
}


public Member findLeafChild(Member member,Zone zone){
    Member left = member.getLeftChildren();
    Member right = member.getRightChildren();
    if (Zone.left == zone) {
        if (left != null) {
            return findLeafChild(left, zone);
        } else {
            return member;
        }
    } else if (Zone.right == zone) {
        if (right != null) {
            return findLeafChild(right, zone);
        } else {
            return member;
        }
    } else {
        if (left != null) {
            return findLeafChild(left, Zone.left);
        } else if (right != null) {
            return findLeafChild(right, Zone.left);
        } else {
            return member;
        }
    }
}


public List<Member> findMemberByDefaultMemberRank(Boolean isDefault){
    String jqpl = "select members from Member members where members.memberRank.isDefault = :isDefault";
    return entityManager.createQuery(jqpl, Member.class).setFlushMode(FlushModeType.COMMIT).setParameter("isDefault", isDefault).getResultList();
}


public boolean numberExists(String number){
    if (number == null) {
        return false;
    }
    String jpql = "select count(*) from Member members where lower(members.number) = lower(:number)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("number", number).getSingleResult();
    return count > 0;
}


public List<Member> findMember(Integer leve,Integer leve2,boolean isActive){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
    Root<Member> root = criteriaQuery.from(Member.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (isActive) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.notEqual(root.get("memberRank"), memberRankDao.findDefault()));
    }
    if (leve <= 0 || leve2 <= 0) {
        return null;
    } else {
        if (leve < leve2) {
            leve2 = leve;
        }
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Integer>get("leve"), leve - leve2));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThan(root.<Integer>get("leve"), leve));
    }
    criteriaQuery.where(restrictions);
    return super.findList(criteriaQuery, null, null, null, null);
}


}