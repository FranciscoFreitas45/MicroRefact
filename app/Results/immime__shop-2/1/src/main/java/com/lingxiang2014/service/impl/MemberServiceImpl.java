package com.lingxiang2014.service.impl;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.persistence.LockModeType;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.Principal;
import com.lingxiang2014.Setting;
import com.lingxiang2014.dao.DepositDao;
import com.lingxiang2014.dao.MemberDao;
import com.lingxiang2014.entity.Admin;
import com.lingxiang2014.entity.Deposit;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Member.Zone;
import com.lingxiang2014.entity.MemberRank;
import com.lingxiang2014.service.MemberService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.Interface.DepositDao;
import com.lingxiang2014.DTO.Deposit;
import com.lingxiang2014.DTO.Admin;
import com.lingxiang2014.DTO.Principal;
@Service("memberServiceImpl")
public class MemberServiceImpl extends BaseServiceImpl<Member, Long>implements MemberService{

@Resource(name = "memberDaoImpl")
 private  MemberDao memberDao;

@Resource(name = "depositDaoImpl")
 private  DepositDao depositDao;


public Page<Member> findChildrenPage(Member member,Pageable pageable){
    return memberDao.findChildrenPage(member, pageable);
}


public Member findByUserName(String username){
    return memberDao.findByUsername(username);
}


public void save(Member member,Admin operator){
    Assert.notNull(member);
    memberDao.persist(member);
    if (member.getBalance().compareTo(new BigDecimal(0)) > 0) {
        Deposit deposit = new Deposit();
        deposit.setType(operator != null ? Deposit.Type.adminRecharge : Deposit.Type.memberRecharge);
        deposit.setCredit(member.getBalance());
        deposit.setDebit(new BigDecimal(0));
        deposit.setBalance(member.getBalance());
        deposit.setOperator(operator != null ? operator.getUsername() : null);
        deposit.setMember(member);
        depositDao.persist(deposit);
    }
}


public void update(Member member,Integer modifyPoint,BigDecimal modifyBalance,String depositMemo,Admin operator){
    Assert.notNull(member);
    memberDao.lock(member, LockModeType.PESSIMISTIC_WRITE);
    if (modifyPoint != null && modifyPoint != 0 && member.getPoint() + modifyPoint >= 0) {
        member.setPoint(member.getPoint() + modifyPoint);
    }
    if (modifyBalance != null && modifyBalance.compareTo(new BigDecimal(0)) != 0 && member.getBalance().add(modifyBalance).compareTo(new BigDecimal(0)) >= 0) {
        member.setBalance(member.getBalance().add(modifyBalance));
        Deposit deposit = new Deposit();
        if (modifyBalance.compareTo(new BigDecimal(0)) > 0) {
            deposit.setType(operator != null ? Deposit.Type.adminRecharge : Deposit.Type.memberRecharge);
            deposit.setCredit(modifyBalance);
            deposit.setDebit(new BigDecimal(0));
        } else {
            deposit.setType(operator != null ? Deposit.Type.adminChargeback : Deposit.Type.memberPayment);
            deposit.setCredit(new BigDecimal(0));
            deposit.setDebit(modifyBalance);
        }
        deposit.setBalance(member.getBalance());
        deposit.setOperator(operator != null ? operator.getUsername() : null);
        deposit.setMemo(depositMemo);
        deposit.setMember(member);
        depositDao.persist(deposit);
    }
    memberDao.merge(member);
}


@Transactional(readOnly = true)
public boolean isAuthenticated(){
    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes != null) {
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
        if (principal != null) {
            return true;
        }
    }
    return false;
}


public BigDecimal countBalance(Integer type,Date beginDate,Date endDate){
    return memberDao.countBalance(type, beginDate, endDate);
}


@Resource(name = "memberDaoImpl")
public void setBaseDao(MemberDao memberDao){
    super.setBaseDao(memberDao);
}


public Member find(Member parent,Integer index,MemberRank memberRank){
    return memberDao.find(parent, index, memberRank);
}


public List<Member> findListByNumber(String number){
    return memberDao.findListByNumber(number);
}


public String getMemberNumber(String currentNumber){
    Setting setting = SettingUtils.get();
    String prefix = setting.getMemberNumberPrefix() == null ? "" : setting.getMemberNumberPrefix();
    Integer numberLength = setting.getMemberNumberLength() - prefix.length();
    Double result = (Math.pow(10 * 1.0, numberLength * 1.0));
    Boolean flag = false;
    String number = currentNumber;
    if (numberExists(number) || currentNumber == null) {
        number = "";
        flag = false;
    } else {
        flag = true;
    }
    while (!flag) {
        Integer numberInt = new Random().nextInt(result.intValue());
        Integer length = numberInt.toString().length();
        while (length < numberLength) {
            number = number + "0";
            length = length + 1;
        }
        number = prefix + number + numberInt;
        if (numberExists(number)) {
            flag = false;
        } else {
            flag = true;
        }
    }
    return number;
}


public List<Member> findMember(Integer leve,Integer leve2,boolean isActive){
    return memberDao.findMember(leve, leve2, isActive);
}


public Member findLeftChild(Member member){
    return memberDao.findLeftChild(member);
}


@Transactional(readOnly = true)
public boolean usernameDisabled(String username){
    Assert.hasText(username);
    Setting setting = SettingUtils.get();
    if (setting.getDisabledUsernames() != null) {
        for (String disabledUsername : setting.getDisabledUsernames()) {
            if (StringUtils.containsIgnoreCase(username, disabledUsername)) {
                return true;
            }
        }
    }
    return false;
}


@Transactional(readOnly = true)
public String getCurrentUsername(){
    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes != null) {
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
        if (principal != null) {
            return principal.getUsername();
        }
    }
    return null;
}


public Member findByNumber(String number){
    return memberDao.findByNumber(number);
}


public long count(MemberRank membeRank,Date beginDate,Date endDate){
    return memberDao.count(membeRank, beginDate, endDate);
}


public Member findRightChild(Member member){
    return memberDao.findRightChild(member);
}


@Transactional(readOnly = true)
public boolean emailExists(String email){
    return memberDao.emailExists(email);
}


@Transactional(readOnly = true)
public boolean usernameExists(String username){
    return memberDao.usernameExists(username);
}


public Member findChild(Member member,Zone zone){
    return memberDao.findChild(member, zone);
}


@Transactional(readOnly = true)
public Member findByUsername(String username){
    return memberDao.findByUsername(username);
}


public Member findLeaf(String number,Member member,Integer index,Zone zone){
    return memberDao.findLeaf(number, member, index, zone);
}


@Transactional(readOnly = true)
public boolean emailUnique(String previousEmail,String currentEmail){
    if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail)) {
        return true;
    } else {
        if (memberDao.emailExists(currentEmail)) {
            return false;
        } else {
            return true;
        }
    }
}


public List<Member> findTop(Member member){
    return memberDao.findTop(member);
}


@Transactional(readOnly = true)
public List<Member> findListByEmail(String email){
    return memberDao.findListByEmail(email);
}


@Transactional(readOnly = true)
public Member getCurrent(){
    RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
    if (requestAttributes != null) {
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        Principal principal = (Principal) request.getSession().getAttribute(Member.PRINCIPAL_ATTRIBUTE_NAME);
        if (principal != null) {
            return memberDao.find(principal.getId());
        }
    }
    return null;
}


public List<Member> findChildren(Member member,Zone zone){
    return memberDao.findChildren(member, zone);
}


public Member findLeafChild(Member member,Zone zone){
    return memberDao.findLeafChild(member, zone);
}


public List<Member> findMemberByDefaultMemberRank(Boolean isDefault){
    return memberDao.findMemberByDefaultMemberRank(isDefault);
}


public boolean numberExists(String number){
    return memberDao.numberExists(number);
}


}