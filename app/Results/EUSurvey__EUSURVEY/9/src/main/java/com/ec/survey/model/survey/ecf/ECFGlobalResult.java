package com.ec.survey.model.survey.ecf;
 import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
public class ECFGlobalResult {

@JsonProperty("profileComparisonUid")
 private  String profileComparisonUid;

@JsonProperty("profileFilterUid")
 private  String profileFilterUid;

@JsonProperty("totalResults")
 private  ECFGlobalTotalResult totalResults;

@JsonProperty("individualResults")
 private  List<ECFGlobalCompetencyResult> individualResults;

@JsonProperty("pageNumber")
 private  Integer pageNumber;

@JsonProperty("pageSize")
 private  Integer pageSize;

@JsonProperty("numberOfPages")
 private  Integer numberOfPages;

@JsonProperty("numberOfResults")
 private  Integer numberOfResults;


public Integer getNumberOfPages(){
    return numberOfPages;
}


public void setProfileComparisonUid(String profileComparisonUid){
    this.profileComparisonUid = profileComparisonUid;
}


public void setTotalResult(ECFGlobalTotalResult totalResults){
    this.totalResults = totalResults;
}


public String getProfileComparisonUid(){
    return profileComparisonUid;
}


public void setProfileFilterUid(String profileFilterUid){
    this.profileFilterUid = profileFilterUid;
}


public void setNumberOfPages(Integer numberOfPages){
    this.numberOfPages = numberOfPages;
}


public void setNumberOfResults(Integer numberOfResults){
    this.numberOfResults = numberOfResults;
}


public void setIndividualResults(List<ECFGlobalCompetencyResult> individualResults){
    this.individualResults = individualResults;
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


public void setPageNumber(Integer pageNumber){
    this.pageNumber = pageNumber;
}


public Integer getNumberOfResults(){
    return numberOfResults;
}


@Override
public String toString(){
    return "ECFGlobalResult [profileComparisonUid=" + profileComparisonUid + ", profileFilterUid=" + profileFilterUid + ", totalResults=" + totalResults + ", individualResults=" + individualResults + ", pageNumber=" + pageNumber + ", pageSize=" + pageSize + ", numberOfPages=" + numberOfPages + ", numberOfResults=" + numberOfResults + "]";
}


public ECFGlobalTotalResult getTotalResults(){
    return totalResults;
}


public void addIndividualResults(ECFGlobalCompetencyResult individualResult){
    this.individualResults.add(individualResult);
}


public String getProfileFilterUid(){
    return profileFilterUid;
}


public void setPageSize(Integer pageSize){
    this.pageSize = pageSize;
}


}