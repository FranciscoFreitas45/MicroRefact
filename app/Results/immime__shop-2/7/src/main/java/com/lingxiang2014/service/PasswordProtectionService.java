package com.lingxiang2014.service;
 import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.PasswordProtection;
import com.lingxiang2014.entity.Member;
public interface PasswordProtectionService extends BaseService<PasswordProtection, Long>{


public List<PasswordProtection> findListByMember(Member member)
;

public Page<PasswordProtection> findPage(Pageable pageable,Member member)
;

}