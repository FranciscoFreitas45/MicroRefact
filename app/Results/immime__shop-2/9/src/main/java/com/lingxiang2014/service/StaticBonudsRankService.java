package com.lingxiang2014.service;
 import java.math.BigDecimal;
import java.util.List;
import com.lingxiang2014.entity.StaticBonudsRank;
public interface StaticBonudsRankService extends BaseService<StaticBonudsRank, Long>{


public StaticBonudsRank findDefault()
;

public boolean nameUnique(String previousName,String currentName)
;

public boolean everyAmountExists(BigDecimal amount)
;

public StaticBonudsRank findByAmount(BigDecimal amount)
;

public boolean amountUnique(BigDecimal previousAmount,BigDecimal currentAmount)
;

public boolean amountExists(BigDecimal amount)
;

public boolean nameExists(String name)
;

public List<StaticBonudsRank> findLTEQ(Integer myPeople)
;

}