package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.BSystemAccount;
public interface BSystemAccountService extends BaseService<BSystemAccount, Long>{


public boolean usernameExists(String username)
;

public Page<BSystemAccount> findChildrenPage(BSystemAccount bSystemAccount,Pageable pageable)
;

public boolean usernameDisabled(String username)
;

public BSystemAccount findByUsername(String username)
;

public BSystemAccount findByUserName(String username)
;

public BSystemAccount findLeaf(Integer index)
;

public boolean emailUnique(String previousEmail,String currentEmail)
;

public List<BSystemAccount> findListByEmail(String email)
;

public boolean emailExists(String email)
;

}