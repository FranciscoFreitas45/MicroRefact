package com.lingxiang2014.dao;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Member.Zone;
import com.lingxiang2014.entity.MemberRank;
public interface MemberDao extends BaseDao<Member, Long>{


public Member findLeftChild(Member member)
;

public Page<Member> findChildrenPage(Member member,Pageable pageable)
;

public Member findByNumber(String number)
;

public long count(MemberRank membeRank,Date beginDate,Date endDate)
;

public Member findRightChild(Member member)
;

public boolean emailExists(String email)
;

public boolean usernameExists(String username)
;

public Member findChild(Member member,Zone zone)
;

public BigDecimal countBalance(Integer type,Date beginDate,Date endDate)
;

public Member findByUsername(String username)
;

public Member find(Member parent,Integer index,MemberRank memberRank)
;

public Member findLeaf(String number,Member member,Integer index,Zone zone)
;

public List<Member> findListByNumber(String number)
;

public List<Member> findTop(Member member)
;

public List<Member> findListByEmail(String email)
;

public List<Member> findChildren(Member member,Zone zone)
;

public Member findLeafChild(Member member,Zone zone)
;

public List<Member> findMemberByDefaultMemberRank(Boolean isDefault)
;

public boolean numberExists(String number)
;

public List<Member> findMember(Integer leve,Integer leve2,boolean isActive)
;

}