package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "keywords")
public class Keywords {

@Column(name = "keywords")
@Id
 private  String keywords;

@Column(name = "classification")
 private  Integer classification;

public Keywords() {
}public Keywords(String keywords, Integer classification) {
    this.keywords = keywords;
    this.classification = classification;
}
public void setClassification(Integer classification){
    this.classification = classification;
}


public String getKeywords(){
    return keywords;
}


@Override
public String toString(){
    return "Keywords{" + "keywords='" + keywords + '\'' + ", classification=" + classification + '}';
}


public void setKeywords(String keywords){
    this.keywords = keywords;
}


public Integer getClassification(){
    return classification;
}


}