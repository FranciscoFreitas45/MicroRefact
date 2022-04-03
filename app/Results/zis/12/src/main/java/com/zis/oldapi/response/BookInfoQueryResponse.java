package com.zis.oldapi.response;
 import java.util.List;
public class BookInfoQueryResponse extends BaseApiResponse{

 private  List<BookInfoQueryData> resultList;


public List<BookInfoQueryData> getResultList(){
    return resultList;
}


public void setResultList(List<BookInfoQueryData> resultList){
    this.resultList = resultList;
}


}