package com.crontab;
 import java.util.ArrayList;
import java.util.List;
public class JsonCalculationFilter {

 private  List<String> logNumKeyList;

 private  List<String> custIdList;

 private  List<String> ccyPairList;


public void addCustId(String custId){
    if (this.custIdList == null) {
        this.custIdList = new ArrayList<String>();
    }
    this.custIdList.add(custId);
}


public List<List<String>> getSplitedCurrencyPairList(){
    if (ccyPairList == null) {
        return null;
    }
    List<List<String>> splitedList = new ArrayList<List<String>>();
    for (String ccyPair : ccyPairList) {
        if (ccyPair == null || ccyPair.length() != 6) {
            continue;
        }
        String ccy1 = ccyPair.substring(0, 3);
        String ccy2 = ccyPair.substring(3, 6);
        List<String> splitedPair = new ArrayList<String>();
        splitedPair.add(ccy1);
        splitedPair.add(ccy2);
        splitedList.add(splitedPair);
    }
    return splitedList;
}


public List<String> getCcyPairList(){
    return ccyPairList;
}


public void addLogNumKey(String logNumKey){
    if (this.logNumKeyList == null) {
        this.logNumKeyList = new ArrayList<String>();
    }
    this.logNumKeyList.add(logNumKey);
}


public void addCcyPair(String ccyPair){
    if (this.ccyPairList == null) {
        this.ccyPairList = new ArrayList<String>();
    }
    this.ccyPairList.add(ccyPair);
}


public List<String> getLogNumKeyList(){
    return logNumKeyList;
}


public List<String> getCustIdList(){
    return custIdList;
}


}