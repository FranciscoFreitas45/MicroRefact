package com.lingxiang2014.dao;
 import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.BSystemAccount;
public interface BSystemAccountDao extends BaseDao<BSystemAccount, Long>{


public boolean usernameExists(String username)
;

public Page<BSystemAccount> findChildrenPage(BSystemAccount bSystemAccount,Pageable pageable)
;

public BSystemAccount findByUsername(String username)
;

public BSystemAccount findLeaf(Integer index)
;

public List<BSystemAccount> findListByEmail(String email)
;

public boolean emailExists(String email)
;

}