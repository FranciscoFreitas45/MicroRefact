package com.zis.api.response;
 import java.util.List;
public class RequiredAmountQueryResponse extends BaseApiResponse{

 private  List<RequiredAmountQueryData> resultList;


public List<RequiredAmountQueryData> getResultList(){
    return resultList;
}


public void setResultList(List<RequiredAmountQueryData> resultList){
    this.resultList = resultList;
}


}