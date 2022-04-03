package com.ec.survey.DTO;
 import com.ec.survey.model.survey.Element;
import org.springframework.beans.factory.annotation.Configurable;
import javax.persistence;
import java.util.HashMap;
import java.util.Map;
public class Statistics {

 private  int id;

 private  int surveyId;

 private  String filterHash;

 private  Map<String,Integer> requestedRecords;

 private  Map<String,Double> requestedRecordsPercent;

 private  Map<String,Double> totalsPercent;

 private  Integer requestID;

 private  Map<String,Integer> requestedRecordsScore;

 private  Map<String,Double> requestedRecordsPercentScore;

 private  Integer maxScore;

 private  Integer bestScore;

 private  Double meanScore;

 private  Integer total;

 private  Map<String,Integer> maxSectionScore;

 private  Map<String,Double> meanSectionScore;

 private  Map<String,Double> bestSectionScore;

 private  Boolean invalid;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://10";


@Transient
public int getRequestedRecordsForRatingQuestion(Element question,Integer answer){
    String id = question.getId().toString() + answer.toString();
    Object result = requestedRecords.get(id);
    return (int) (result != null ? result : 0);
}


@ElementCollection
public Map<String,Double> getBestSectionScore(){
    return bestSectionScore;
}


@Id
@Column(name = "ACCESS_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "BESTSCORE")
public Integer getBestScore(){
    return bestScore;
}


@Column(name = "MEANSCORE")
public Double getMeanScore(){
    return meanScore;
}


@Transient
public int getRequestedRecordsForMatrix(Element question,Element answer){
    String id = question.getId().toString() + answer.getId().toString();
    Object result = requestedRecords.get(id);
    return (int) (result != null ? result : 0);
}


@Transient
public double getRequestedRecordsPercentForMatrix(Element question,Element answer){
    String id = question.getId().toString() + answer.getId().toString();
    Object result = requestedRecordsPercent.get(id);
    return (double) (result != null ? result : 0);
}


@ElementCollection
public Map<String,Integer> getMaxSectionScore(){
    return maxSectionScore;
}


@Transient
public double getRequestedRecordsPercentForGallery(Element question,int index){
    String id = question.getId().toString() + "-" + index;
    Object result = requestedRecordsPercent.get(id);
    return (double) (result != null ? result : 0);
}


@ElementCollection
public Map<String,Integer> getRequestedRecordsScore(){
    return requestedRecordsScore;
}


@Transient
public double getTotalsPercentForGallery(Element question,int index){
    String id = question.getId().toString() + "-" + index;
    Object result = totalsPercent.get(id);
    return (double) (result != null ? result : 0);
}


@Column(name = "INVALID")
public Boolean getInvalid(){
    return invalid;
}


@ElementCollection
public Map<String,Integer> getRequestedRecords(){
    return requestedRecords;
}


@Transient
public double getRequestedRecordsPercentForRatingQuestion(Element question,Integer answer){
    String id = question.getId().toString() + answer.toString();
    Object result = requestedRecordsPercent.get(id);
    return (double) (result != null ? result : 0);
}


@ElementCollection
public Map<String,Double> getTotalsPercent(){
    return totalsPercent;
}


@Transient
public double getTotalsPercentForMatrix(Element question,Element answer){
    String id = question.getId().toString() + answer.getId().toString();
    Object result = totalsPercent.get(id);
    return (double) (result != null ? result : 0);
}


@Transient
public int getRequestedRecordsForGallery(Element question,int index){
    String id = question.getId().toString() + "-" + index;
    Object result = requestedRecords.get(id);
    return (int) (result != null ? result : 0);
}


@ElementCollection
public Map<String,Double> getMeanSectionScore(){
    return meanSectionScore;
}


@Column(name = "FILTER")
public String getFilterHash(){
    return filterHash;
}


@ElementCollection
public Map<String,Double> getRequestedRecordsPercentScore(){
    return requestedRecordsPercentScore;
}


@Column(name = "MAXSCORE")
public Integer getMaxScore(){
    return maxScore;
}


@Column(name = "SURVEYID")
public int getSurveyId(){
    return surveyId;
}


@Column(name = "NUMRESULTS")
public Integer getTotal(){
    return total != null ? total : 0;
}


@ElementCollection
public Map<String,Double> getRequestedRecordsPercent(){
    return requestedRecordsPercent;
}


@Transient
public double getTotalsPercentForRatingQuestion(Element question,Integer answer){
    String id = question.getId().toString() + answer.toString();
    Object result = totalsPercent.get(id);
    return (double) (result != null ? result : 0.0);
}


@Transient
public Integer getRequestID(){
    return requestID;
}


public void setSurveyId(int surveyId){
    this.surveyId = surveyId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setSurveyId"))

.queryParam("surveyId",surveyId)
;
restTemplate.put(builder.toUriString(),null);
}


}