package com.lingxiang2014.dao;
 import java.math.BigDecimal;
import java.util.List;
import com.lingxiang2014.entity.StaticBonudsRank;
public interface StaticBonudsRankDao extends BaseDao<StaticBonudsRank, Long>{


public StaticBonudsRank findDefault()
;

public boolean everyAmountExists(BigDecimal everyAmount)
;

public StaticBonudsRank findByAmount(BigDecimal amount)
;

public boolean amountExists(BigDecimal amount)
;

public boolean nameExists(String name)
;

public List<StaticBonudsRank> findLTEQ(Integer myPeople)
;

}