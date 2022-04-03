package com.byr.warehouse.pojo;
 import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Entity
public class DaliyCount {

@Id
// 设置主键并且设置主键为自增
@GeneratedValue(strategy = GenerationType.AUTO)
 private  int rowId;

 private  int size;

 private  String type;

 private  Date computeDate;

public DaliyCount() {
}public DaliyCount(int size, String type, Date computeDate) {
    this.size = size;
    this.type = type;
    this.computeDate = computeDate;
}
public int getSize(){
    return size;
}


public void setSize(int size){
    this.size = size;
}


public void setRowId(int rowId){
    this.rowId = rowId;
}


public String getType(){
    return type;
}


public void setComputeDate(Date computeDate){
    this.computeDate = computeDate;
}


public int getRowId(){
    return rowId;
}


public void setType(String type){
    this.type = type;
}


public Date getComputeDate(){
    return computeDate;
}


}