package com.qidian.hcm.module.center.request;
 import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@ApiModel
public class UploadFileRequest {

@ApiModelProperty(value = "Base64文件", name = "fileBase64Str", required = true)
 private  String fileBase64Str;

@ApiModelProperty(value = "上传的文件名", name = "filename", required = true)
 private  String fileName;


}