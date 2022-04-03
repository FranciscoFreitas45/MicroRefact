package com.lingxiang2014.dao;
 import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.PasswordProtection;
import com.lingxiang2014.entity.Member;
public interface PasswordProtectionDao extends BaseDao<PasswordProtection, Long>{


public List<PasswordProtection> findListByMember(Member member)
;

public Page<PasswordProtection> findPage(Pageable pageable,Member member)
;

}