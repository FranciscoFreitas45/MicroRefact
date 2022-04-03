package com.lingxiang2014.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.PasswordProtectionDao;
import com.lingxiang2014.entity.PasswordProtection;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.service.PasswordProtectionService;
@Service("passwordProtectionServiceImpl")
public class PasswordProtectionServiceImpl extends BaseServiceImpl<PasswordProtection, Long>implements PasswordProtectionService{

@Resource(name = "passwordProtectionDaoImpl")
 private  PasswordProtectionDao passwordProtectionDao;


public List<PasswordProtection> findListByMember(Member member){
    return passwordProtectionDao.findListByMember(member);
}


@Resource(name = "passwordProtectionDaoImpl")
public void setBaseDao(PasswordProtectionDao passwordProtectionDao){
    super.setBaseDao(passwordProtectionDao);
}


public Page<PasswordProtection> findPage(Pageable pageable,Member member){
    return passwordProtectionDao.findPage(pageable, member);
}


}