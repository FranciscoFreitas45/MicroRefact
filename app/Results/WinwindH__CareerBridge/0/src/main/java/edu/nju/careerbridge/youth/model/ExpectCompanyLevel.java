package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "expect_company_level")
public class ExpectCompanyLevel {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String phone;

 private  String expectCompanyLevel;

public ExpectCompanyLevel() {
}public ExpectCompanyLevel(String phone, String expectCompanyLevel) {
    this.phone = phone;
    this.expectCompanyLevel = expectCompanyLevel;
}
public String getPhone(){
    return phone;
}


public String getExpectCompanyLevel(){
    return expectCompanyLevel;
}


public void setExpectCompanyLevel(String expectCompanyLevel){
    this.expectCompanyLevel = expectCompanyLevel;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


}