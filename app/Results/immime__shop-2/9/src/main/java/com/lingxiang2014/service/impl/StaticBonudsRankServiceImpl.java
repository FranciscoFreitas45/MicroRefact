package com.lingxiang2014.service.impl;
 import java.math.BigDecimal;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.lingxiang2014.dao.StaticBonudsRankDao;
import com.lingxiang2014.entity.StaticBonudsRank;
import com.lingxiang2014.service.StaticBonudsRankService;
@Service("staticBonudsRankServiceImpl")
public class StaticBonudsRankServiceImpl extends BaseServiceImpl<StaticBonudsRank, Long>implements StaticBonudsRankService{

@Resource(name = "staticBonudsRankDaoImpl")
 private  StaticBonudsRankDao staticBonudsRankDao;


@Transactional(readOnly = true)
public StaticBonudsRank findDefault(){
    return staticBonudsRankDao.findDefault();
}


@Transactional(readOnly = true)
public boolean nameUnique(String previousName,String currentName){
    if (StringUtils.equalsIgnoreCase(previousName, currentName)) {
        return true;
    } else {
        if (staticBonudsRankDao.nameExists(currentName)) {
            return false;
        } else {
            return true;
        }
    }
}


@Transactional(readOnly = true)
public boolean everyAmountExists(BigDecimal everyAmount){
    return staticBonudsRankDao.everyAmountExists(everyAmount);
}


@Resource(name = "staticBonudsRankDaoImpl")
public void setBaseDao(StaticBonudsRankDao staticBonudsRankDao){
    super.setBaseDao(staticBonudsRankDao);
}


@Transactional(readOnly = true)
public StaticBonudsRank findByAmount(BigDecimal amount){
    return staticBonudsRankDao.findByAmount(amount);
}


@Transactional(readOnly = true)
public boolean amountUnique(BigDecimal previousAmount,BigDecimal currentAmount){
    if (previousAmount != null && previousAmount.compareTo(currentAmount) == 0) {
        return true;
    } else {
        if (staticBonudsRankDao.amountExists(currentAmount)) {
            return false;
        } else {
            return true;
        }
    }
}


@Transactional(readOnly = true)
public boolean amountExists(BigDecimal amount){
    return staticBonudsRankDao.amountExists(amount);
}


@Transactional(readOnly = true)
public boolean nameExists(String name){
    return staticBonudsRankDao.nameExists(name);
}


public List<StaticBonudsRank> findLTEQ(Integer myPeople){
    return staticBonudsRankDao.findLTEQ(myPeople);
}


}