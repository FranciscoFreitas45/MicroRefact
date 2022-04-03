package com.zis.toolkit.controller;
 import java.util.List;
import java.util.Set;
import com.zis.bookinfo.bean.Bookinfo;
public class StockPosCheckResult {

 private  boolean lastRecordSuccess;

 private  String failReason;

 private  List<Bookinfo> scannedBookList;

 private  Set<String> comparablePos;

public StockPosCheckResult(boolean lastRecordSuccess, String failReason) {
    this.lastRecordSuccess = lastRecordSuccess;
    this.failReason = failReason;
}
public void setScannedBookList(List<Bookinfo> scannedBookList){
    this.scannedBookList = scannedBookList;
}


public String getFailReason(){
    return failReason;
}


public boolean getLastRecordSuccess(){
    return lastRecordSuccess;
}


public Set<String> getComparablePos(){
    return comparablePos;
}


public void setLastRecordSuccess(boolean lastRecordSuccess){
    this.lastRecordSuccess = lastRecordSuccess;
}


public void setFailReason(String failReason){
    this.failReason = failReason;
}


public void setComparablePos(Set<String> comparablePos){
    this.comparablePos = comparablePos;
}


public List<Bookinfo> getScannedBookList(){
    return scannedBookList;
}


}