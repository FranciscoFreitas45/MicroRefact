package com.easyshopping.dao;
 import java.util.Date;
import java.util.List;
import com.easyshopping.entity.Member;
public interface MemberDao extends BaseDao<Member, Long>{


public boolean usernameExists(String username)
;

public Member findByUsername(String username)
;

public List<Member> findListByEmail(String email)
;

public List<Object[]> findPurchaseList(Date beginDate,Date endDate,Integer count)
;

public boolean emailExists(String email)
;

}