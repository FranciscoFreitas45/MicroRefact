package com.qidian.hcm.DTO;
 import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.Id;
public class PositionGradeDTO {

 private  Long id;

 private  String name;

 private  Long gradeId;

 private  String gradeName;


}