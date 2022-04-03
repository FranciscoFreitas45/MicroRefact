package com.lingxiang2014.service;
 import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.entity.Bonuds;
import com.lingxiang2014.entity.Bonuds.Type;
import com.lingxiang2014.entity.Member;
public interface BonudsService extends BaseService<Bonuds, Long>{


public BigDecimal countBalance(Type type,Date beginDate,Date endDate)
;

public List<Bonuds> findList(Member member,Type type,Date beginDate,Date endDate)
;

public BigDecimal count(Member member,Type type,Date beginDate,Date endDate)
;

public Page<Bonuds> findPage(Member member,Pageable pageable)
;

public List<Bonuds> findToday(Type type,Member member)
;

}