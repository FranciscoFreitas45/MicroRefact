package edu.nju.careerbridge.youth.bean;
 public class CompanyDescOutputBean {

 private  String companyName;

 private  String word;

 private  Double weight;

// CompanyDescOutputBean
public CompanyDescOutputBean() {
    System.out.println("jenkins test");
}public CompanyDescOutputBean(String companyName, String word, Double weight) {
    this.companyName = companyName;
    this.word = word;
    this.weight = weight;
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


@Override
public String toString(){
    return "CompanyDescOutputBean{" + "companyName='" + companyName + '\'' + ", word='" + word + '\'' + ", weight=" + weight + '}';
}


public void setCompanyName(String companyName){
    this.companyName = companyName;
}


public void setWord(String word){
    this.word = word;
}


}