package com.github.haseoo.courier.DTO;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.EAGER;
public class LogisticianModel extends EmployeeModel{

 private  MagazineModel magazine;

 private Long id;


}