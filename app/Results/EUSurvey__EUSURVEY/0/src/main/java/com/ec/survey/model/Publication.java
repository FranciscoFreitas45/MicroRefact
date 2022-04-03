package com.ec.survey.model;
 import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
@Entity
@Table(name = "PUBLICATION")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Publication {

 private  long serialVersionUID;

 private  Integer id;

 private  boolean showContent;

 private  boolean showStatistics;

 private  boolean showCharts;

 private  boolean showSearch;

 private  boolean allQuestions;

 private  boolean allContributions;

 private  boolean showUploadedDocuments;

 private  ResultFilter filter;

 private  String password;


public void setPassword(String password){
    this.password = password;
}


public void setShowUploadedDocuments(Boolean showUploadedDocuments){
    this.showUploadedDocuments = showUploadedDocuments == null || showUploadedDocuments;
}


@Column(name = "PUB_CHARTS")
public boolean isShowCharts(){
    return showCharts;
}


@Column(name = "PUB_STAT")
public boolean isShowStatistics(){
    return showStatistics;
}


public void setShowCharts(boolean showCharts){
    this.showCharts = showCharts;
}


@Id
@Column(name = "PUB_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "PUB_ALLQ")
public boolean isAllQuestions(){
    return allQuestions;
}


public void setShowStatistics(boolean showStatistics){
    this.showStatistics = showStatistics;
}


@Transient
public boolean isActive(){
    return showContent || showCharts || showStatistics;
}


@Column(name = "PUB_UPLOADED")
public Boolean getShowUploadedDocuments(){
    return showUploadedDocuments;
}


public void setShowContent(boolean showContent){
    this.showContent = showContent;
}


public void setFilter(ResultFilter filter){
    this.filter = filter;
}


public void setAllContributions(boolean allContributions){
    this.allContributions = allContributions;
}


public void setShowSearch(boolean showSearch){
    this.showSearch = showSearch;
}


@OneToOne(cascade = CascadeType.ALL)
public ResultFilter getFilter(){
    return filter;
}


@Column(name = "PUB_SEARCH")
public boolean isShowSearch(){
    return showSearch;
}


@Column(name = "PUB_PASSWORD")
public String getPassword(){
    return password;
}


@Column(name = "PUB_ALLCONT")
public boolean isAllContributions(){
    return allContributions;
}


public void setAllQuestions(boolean allQuestions){
    this.allQuestions = allQuestions;
}


@Transient
public boolean isSelected(String questionId){
    return filter != null && filter.getVisibleQuestions().contains(questionId);
}


public void setId(Integer id){
    this.id = id;
}


@Transient
public boolean isFiltered(int questionId,int possibleanswerId){
    return filter != null && filter.contains(Integer.toString(questionId), Integer.toString(possibleanswerId));
}


@Column(name = "PUB_CONT")
public boolean isShowContent(){
    return showContent;
}


}