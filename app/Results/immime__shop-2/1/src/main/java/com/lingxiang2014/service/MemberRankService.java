package com.lingxiang2014.service;
 import java.math.BigDecimal;
import com.lingxiang2014.entity.MemberRank;
public interface MemberRankService extends BaseService<MemberRank, Long>{


public MemberRank findDefault()
;

public boolean nameUnique(String previousName,String currentName)
;

public MemberRank findByAmount(BigDecimal amount)
;

public boolean amountUnique(BigDecimal previousAmount,BigDecimal currentAmount)
;

public boolean amountExists(BigDecimal amount)
;

public boolean nameExists(String name)
;

}