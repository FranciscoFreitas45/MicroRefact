package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "company_description_output")
public class CompanyDescriptionOutput {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "word_id")
@Id
 private  Integer wordId;

 private  String companyName;

 private  String word;

 private  Double weight;

public CompanyDescriptionOutput() {
}public CompanyDescriptionOutput(String companyName, String word, Double weight) {
    this.companyName = companyName;
    this.word = word;
    this.weight = weight;
}
public Integer getWordId(){
    return wordId;
}


public Double getWeight(){
    return weight;
}


public String getCompanyName(){
    return companyName;
}


public String getWord(){
    return word;
}


public void setWeight(Double weight){
    this.weight = weight;
}


public void setWordId(Integer wordId){
    this.wordId = wordId;
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setWord(String word){
    this.word = word;
}


}