package com.zis.api.response;
 import java.util.Map;
public class DepartmentRecordQueryResponse extends BaseApiResponse{

 private  Map<String,Integer> resultList;


public Map<String,Integer> getResultList(){
    return resultList;
}


public void setResultList(Map<String,Integer> resultList){
    this.resultList = resultList;
}


}