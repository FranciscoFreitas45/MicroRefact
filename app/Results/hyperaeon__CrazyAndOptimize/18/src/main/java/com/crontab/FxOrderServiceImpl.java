package com.crontab;
 import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.ssc.faw.util.GenException;
public class FxOrderServiceImpl implements FxOrderService{

 private  Logger LOG;

 private  FxOrderDao fxOrderDao;


public void setFxOrderDao(FxOrderDao fxOrderDao){
    this.fxOrderDao = fxOrderDao;
}


@Override
public List<LegacyFxOrder> getFxOrderInRange(int dateRange,List<String> logNumKeyList){
    try {
        List<LegacyFxOrder> origList = fxOrderDao.getFxOrderInRange(dateRange);
        return filterByLogNum(origList, logNumKeyList);
    } catch (GenException e) {
        LOG.error("Exception when get order from database, return null.", e);
        return null;
    }
}


public List<LegacyFxOrder> filterByLogNum(List<LegacyFxOrder> origList,List<String> logNumKeyList){
    if (logNumKeyList == null || logNumKeyList.isEmpty()) {
        return origList;
    }
    for (String logNumKey : logNumKeyList) {
        LOG.debug("logNumKey: " + logNumKey + ".");
    }
    List<LegacyFxOrder> returnList = new ArrayList<LegacyFxOrder>();
    for (LegacyFxOrder order : origList) {
        for (String logNumKey : logNumKeyList) {
            if (StringUtils.isBlank(logNumKey)) {
                continue;
            }
            if (order.getLogNum().contains(logNumKey)) {
                returnList.add(order);
                break;
            }
        }
    }
    return returnList;
}


}