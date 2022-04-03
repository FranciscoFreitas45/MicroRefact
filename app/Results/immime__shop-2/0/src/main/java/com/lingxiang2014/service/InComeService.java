package com.lingxiang2014.service;
 import java.math.BigDecimal;
import java.util.Date;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.InCome;
import com.lingxiang2014.entity.Member;
public interface InComeService extends BaseService<InCome, Long>{


public BigDecimal findInCome(Date beginDate,Date endDate)
;

public Page<InCome> findPage(Member member,Pageable pageable)
;

}