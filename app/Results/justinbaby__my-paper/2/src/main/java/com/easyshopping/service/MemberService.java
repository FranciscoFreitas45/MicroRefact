package com.easyshopping.service;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.easyshopping.entity.Admin;
import com.easyshopping.entity.Member;
public interface MemberService extends BaseService<Member, Long>{


public boolean usernameExists(String username)
;

public boolean usernameDisabled(String username)
;

public Member findByUsername(String username)
;

public String getCurrentUsername()
;

public boolean emailUnique(String previousEmail,String currentEmail)
;

public void save(Member member,Admin operator)
;

public void update(Member member,Integer modifyPoint,BigDecimal modifyBalance,String depositMemo,Admin operator)
;

public List<Member> findListByEmail(String email)
;

public boolean isAuthenticated()
;

public Member getCurrent()
;

public List<Object[]> findPurchaseList(Date beginDate,Date endDate,Integer count)
;

public boolean emailExists(String email)
;

}