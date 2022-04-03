package com.csquard.mregister.payload;
 import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints;
public class SignUpRequest {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

 private  Long asm_id;

 private  Long sales_region_id;

 private  Long sales_area_id;

 private  Long id_no;

@NotBlank
@Size(min = 3, max = 15)
 private  String username;

@NotBlank
@Size(min = 3, max = 15)
 private  String name;

@NotBlank
@Size(max = 40)
@Email
 private  String email;

@NotBlank
@Size(min = 6, max = 20)
 private  String password;

@Size(min = 4, max = 40)
 private  String first_name;

@Size(min = 4, max = 40)
 private  String last_name;


public void setName(String name){
    this.name = name;
}


public void setId_no(Long id_no){
    this.id_no = id_no;
}


public void setPassword(String password){
    this.password = password;
}


public String getName(){
    return name;
}


public void setUsername(String username){
    this.username = username;
}


public Long getId(){
    return id;
}


public void setSales_region_id(Long sales_region_id){
    this.sales_region_id = sales_region_id;
}


public Long getSales_area_id(){
    return sales_area_id;
}


public void setLast_name(String last_name){
    this.last_name = last_name;
}


public String getUsername(){
    return username;
}


public void setFirst_name(String first_name){
    this.first_name = first_name;
}


public Long getAsm_id(){
    return asm_id;
}


public String getPassword(){
    return password;
}


public void setEmail(String email){
    this.email = email;
}


public String getLast_name(){
    return last_name;
}


public void setAsm_id(Long asm_id){
    this.asm_id = asm_id;
}


public String getFirst_name(){
    return first_name;
}


public Long getSales_region_id(){
    return sales_region_id;
}


public void setId(Long id){
    this.id = id;
}


public String getEmail(){
    return email;
}


public Long getId_no(){
    return id_no;
}


public void setSales_area_id(Long sales_area_id){
    this.sales_area_id = sales_area_id;
}


}