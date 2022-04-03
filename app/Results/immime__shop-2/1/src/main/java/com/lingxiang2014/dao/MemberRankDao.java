package com.lingxiang2014.dao;
 import java.math.BigDecimal;
import com.lingxiang2014.entity.MemberRank;
public interface MemberRankDao extends BaseDao<MemberRank, Long>{


public MemberRank findDefault()
;

public MemberRank findByAmount(BigDecimal amount)
;

public boolean amountExists(BigDecimal amount)
;

public boolean nameExists(String name)
;

}