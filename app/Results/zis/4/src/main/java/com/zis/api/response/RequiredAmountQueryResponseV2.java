package com.zis.api.response;
 import java.util.List;
public class RequiredAmountQueryResponseV2 extends BaseApiResponse{

 private  List<RequiredAmountQueryDataV2> resultList;


public List<RequiredAmountQueryDataV2> getResultList(){
    return resultList;
}


public void setResultList(List<RequiredAmountQueryDataV2> resultList){
    this.resultList = resultList;
}


}