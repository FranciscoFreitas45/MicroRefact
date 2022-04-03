package com.zis.oldapi.response;
 import java.util.List;
public class DepartmentQueryResponse extends BaseApiResponse{

 private  List<DepartmentQueryData> resultList;


public List<DepartmentQueryData> getResultList(){
    return resultList;
}


public void setResultList(List<DepartmentQueryData> resultList){
    this.resultList = resultList;
}


}