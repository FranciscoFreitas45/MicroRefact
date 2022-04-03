package com.ec.survey.DTO;
 import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.ec.survey.model.survey.Question;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.log4j.Logger;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
public class ECFCompetency implements Comparable<ECFCompetency>,Serializable{

 private  long serialVersionUID;

 private  int id;

 private  String competenceUid;

 private  String name;

 private  String description;

 private  List<Question> questions;

 private  List<ECFExpectedScore> expectedScores;

 private  ECFCluster ecfCluster;

 private  Integer orderNumber;

 protected  Logger logger;

private ECFCompetency() {
}public ECFCompetency(String competenceUid, String name, String description) {
    this.competenceUid = competenceUid;
    this.description = description;
    this.name = name;
}public ECFCompetency(String competenceUid, String name, String description, ECFCluster ecfCluster) {
    this.competenceUid = competenceUid;
    this.name = name;
    this.description = description;
    this.ecfCluster = ecfCluster;
}public ECFCompetency(String competenceUid, String name, String description, ECFCluster ecfCluster, Integer orderNumber) {
    this.competenceUid = competenceUid;
    this.name = name;
    this.description = description;
    this.ecfCluster = ecfCluster;
    this.orderNumber = orderNumber;
}
@Column(name = "COMPETENCY_NAME")
public String getName(){
    return name;
}


@JsonIgnore
@OneToMany(mappedBy = "ecfCompetency", cascade = CascadeType.ALL, orphanRemoval = true)
@Fetch(value = FetchMode.SELECT)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<Question> getQuestions(){
    return questions;
}


@JsonIgnore
@OneToMany(mappedBy = "id.competency", cascade = CascadeType.ALL, orphanRemoval = true)
@Fetch(value = FetchMode.SELECT)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public List<ECFExpectedScore> getECFExpectedScores(){
    return expectedScores;
}


@Id
@Column(name = "COMPETENCY_ID")
@GeneratedValue
public Integer getId(){
    return id;
}


@Column(name = "ORDER_NUMBER")
public Integer getOrderNumber(){
    return this.orderNumber;
}


@Column(name = "COMPETENCY_DESC")
public String getDescription(){
    return description;
}


@JsonIgnore
@ManyToOne
@JoinColumn(name = "ECF_CLUSTER", referencedColumnName = "ECF_CLUSTER_ID")
public ECFCluster getEcfCluster(){
    return ecfCluster;
}


@Column(name = "COMPETENCY_UID")
public String getCompetenceUid(){
    return competenceUid;
}


}