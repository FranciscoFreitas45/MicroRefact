package com.easyshopping.service;
 import java.math.BigDecimal;
import com.easyshopping.entity.MemberRank;
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