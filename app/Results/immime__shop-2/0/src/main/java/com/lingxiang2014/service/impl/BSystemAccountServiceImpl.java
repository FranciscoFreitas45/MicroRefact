package com.lingxiang2014.service.impl;
 import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.Setting;
import com.lingxiang2014.dao.BSystemAccountDao;
import com.lingxiang2014.dao.DepositDao;
import com.lingxiang2014.entity.BSystemAccount;
import com.lingxiang2014.service.BSystemAccountService;
import com.lingxiang2014.util.SettingUtils;
import com.lingxiang2014.DTO.Setting;
@Service("bSystemAccountServiceImpl")
public class BSystemAccountServiceImpl extends BaseServiceImpl<BSystemAccount, Long>implements BSystemAccountService{

@Resource(name = "bSystemAccountDaoImpl")
 private  BSystemAccountDao bSystemAccountDao;

@Resource(name = "depositDaoImpl")
 private  DepositDao depositDao;


@Transactional(readOnly = true)
public boolean usernameExists(String username){
    return bSystemAccountDao.usernameExists(username);
}


public Page<BSystemAccount> findChildrenPage(BSystemAccount bSystemAccount,Pageable pageable){
    return bSystemAccountDao.findChildrenPage(bSystemAccount, pageable);
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
public BSystemAccount findByUsername(String username){
    return bSystemAccountDao.findByUsername(username);
}


@Resource(name = "bSystemAccountDaoImpl")
public void setBaseDao(BSystemAccountDao bSystemAccountDao){
    super.setBaseDao(bSystemAccountDao);
}


public BSystemAccount findByUserName(String username){
    return bSystemAccountDao.findByUsername(username);
}


public BSystemAccount findLeaf(Integer index){
    return bSystemAccountDao.findLeaf(index);
}


@Transactional(readOnly = true)
public boolean emailUnique(String previousEmail,String currentEmail){
    if (StringUtils.equalsIgnoreCase(previousEmail, currentEmail)) {
        return true;
    } else {
        if (bSystemAccountDao.emailExists(currentEmail)) {
            return false;
        } else {
            return true;
        }
    }
}


@Transactional(readOnly = true)
public List<BSystemAccount> findListByEmail(String email){
    return bSystemAccountDao.findListByEmail(email);
}


@Transactional(readOnly = true)
public boolean emailExists(String email){
    return bSystemAccountDao.emailExists(email);
}


}