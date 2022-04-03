package com.crontab;
 import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;
public class AverageCalculator implements Calculator{

 private  Logger LOG;


@Override
public BigDecimal calculate(List<BigDecimal> list){
    if (list == null || list.isEmpty()) {
        LOG.debug("list is null or empty");
        return null;
    }
    BigDecimal total = BigDecimal.ZERO;
    for (BigDecimal bigDecimal : list) {
        total = total.add(bigDecimal);
    }
    if (total.compareTo(BigDecimal.ZERO) == 0) {
        LOG.debug("the average number is 0");
        return BigDecimal.ZERO;
    }
    BigDecimal average = total.divide(new BigDecimal(list.size()), 0, BigDecimal.ROUND_DOWN);
    LOG.debug("the average number is " + average);
    return average;
}


}