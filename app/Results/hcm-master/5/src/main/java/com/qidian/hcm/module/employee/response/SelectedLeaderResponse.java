package com.qidian.hcm.module.employee.response;
 import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@ApiModel(value = "可选的主管")
@Getter
@Setter
@AllArgsConstructor
public class SelectedLeaderResponse {

 private  Long id;

 private  String name;


}