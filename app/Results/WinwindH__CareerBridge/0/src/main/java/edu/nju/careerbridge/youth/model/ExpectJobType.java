package edu.nju.careerbridge.youth.model;
 import javax.persistence;
@Entity
@Table(name = "expect_job_type")
public class ExpectJobType {

@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
@Id
 private  Integer id;

 private  String phone;

 private  String expectJobType;

public ExpectJobType() {
}public ExpectJobType(String phone, String expectJobType) {
    this.phone = phone;
    this.expectJobType = expectJobType;
}
public String getPhone(){
    return phone;
}


public void setExpectJobType(String expectJobType){
    this.expectJobType = expectJobType;
}


public void setId(Integer id){
    this.id = id;
}


public String getExpectJobType(){
    return expectJobType;
}


public Integer getId(){
    return id;
}


public void setPhone(String phone){
    this.phone = phone;
}


}