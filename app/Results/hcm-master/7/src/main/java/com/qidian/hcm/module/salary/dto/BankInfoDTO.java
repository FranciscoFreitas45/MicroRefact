package com.qidian.hcm.module.salary.dto;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotBlank;
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@ApiModel(value = "更新银行卡对象DTO")
public class BankInfoDTO {

@ApiModelProperty(value = "卡号", required = true)
@Length(min = 12, max = 22)
 private  String cardNo;

@ApiModelProperty(value = "开户行名称", required = true)
@Length(min = 4)
@NotBlank
 private  String bankName;

@ApiModelProperty(value = "开户行地址", required = true)
 private  String bankAddress;


}