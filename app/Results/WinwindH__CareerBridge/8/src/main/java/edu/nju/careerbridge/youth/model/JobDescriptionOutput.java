package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "job_description_output")
public class JobDescriptionOutput {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "word_id")
@Id
 private  Integer wordId;

 private  String jobId;

 private  String word;

 private  Double weight;

public JobDescriptionOutput() {
}public JobDescriptionOutput(String jobId, String word, Double weight) {
    this.jobId = jobId;
    this.word = word;
    this.weight = weight;
}
public Integer getWordId(){
    return wordId;
}


public void setJobId(String jobId){
    this.jobId = jobId;
}


public String getJobId(){
    return jobId;
}


public Double getWeight(){
    return weight;
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


public void setWord(String word){
    this.word = word;
}


}