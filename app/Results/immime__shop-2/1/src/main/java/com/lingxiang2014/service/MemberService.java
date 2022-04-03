package com.lingxiang2014.service;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Admin;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Member.Zone;
import com.lingxiang2014.entity.MemberRank;
public interface MemberService extends BaseService<Member, Long>{


public Page<Member> findChildrenPage(Member member,Pageable pageable)
;

public Member findByUserName(String username)
;

public void save(Member member,Admin operator)
;

public void update(Member member,Integer modifyPoint,BigDecimal modifyBalance,String depositMemo,Admin operator)
;

public boolean isAuthenticated()
;

public BigDecimal countBalance(Integer type,Date beginDate,Date endDate)
;

public Member find(Member parent,Integer index,MemberRank membeRank)
;

public List<Member> findListByNumber(String number)
;

public String getMemberNumber(String currentNumber)
;

public List<Member> findMember(Integer leve,Integer leve2,boolean isActive)
;

public Member findLeftChild(Member member)
;

public boolean usernameDisabled(String username)
;

public String getCurrentUsername()
;

public Member findByNumber(String number)
;

public long count(MemberRank memberRank,Date beginDate,Date endDate)
;

public Member findRightChild(Member member)
;

public boolean emailExists(String email)
;

public boolean usernameExists(String username)
;

public Member findChild(Member member,Zone zone)
;

public Member findByUsername(String username)
;

public Member findLeaf(String number,Member member,Integer index,Zone zone)
;

public boolean emailUnique(String previousEmail,String currentEmail)
;

public List<Member> findTop(Member member)
;

public List<Member> findListByEmail(String email)
;

public Member getCurrent()
;

public List<Member> findChildren(Member member,Zone zone)
;

public Member findLeafChild(Member member,Zone zone)
;

public List<Member> findMemberByDefaultMemberRank(Boolean isDefault)
;

public boolean numberExists(String number)
;

}