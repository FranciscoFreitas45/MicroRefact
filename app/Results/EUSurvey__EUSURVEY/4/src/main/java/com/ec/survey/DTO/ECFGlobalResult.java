package com.ec.survey.DTO;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFGlobalResult {

 private  String profileComparisonUid;

 private  String profileFilterUid;

 private  ECFGlobalTotalResult totalResults;

 private  List<ECFGlobalCompetencyResult> individualResults;

 private  Integer pageNumber;

 private  Integer pageSize;

 private  Integer numberOfPages;

 private  Integer numberOfResults;


public Integer getNumberOfPages(){
    return numberOfPages;
}


public String getProfileComparisonUid(){
    return profileComparisonUid;
}


public Integer getPageNumber(){
    return pageNumber;
}


public Integer getPageSize(){
    return pageSize;
}


public List<ECFGlobalCompetencyResult> getIndividualResults(){
    return individualResults;
}


public Integer getNumberOfResults(){
    return numberOfResults;
}


public ECFGlobalTotalResult getTotalResults(){
    return totalResults;
}


public String getProfileFilterUid(){
    return profileFilterUid;
}


}