package com.lingxiang2014.dao;
 import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Transfer;
import com.lingxiang2014.entity.Member;
import com.lingxiang2014.entity.Transfer.Method;
public interface TransferDao extends BaseDao<Transfer, Long>{


public Page<Transfer> findPage(Member fromMember,Member toMember,Method method,Pageable pageable)
;

}