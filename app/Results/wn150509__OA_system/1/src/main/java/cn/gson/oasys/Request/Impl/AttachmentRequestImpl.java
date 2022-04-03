package cn.gson.oasys.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.DTO.Attachment;
import cn.gson.oasys.Request.AttachmentRequest;
public class AttachmentRequestImpl implements AttachmentRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Attachment getProFileid(Long attachmentId){
 Attachment aux = restTemplate.getForObject("http://3/ProcessList/{id}/Attachment/getProFileid",Attachment.class,attachmentId);
return aux;
}


public void setProFileid(Attachment proFileid,Long attachmentId){
 restTemplate.put("http://3/ProcessList/{id}/Attachment/setProFileid",proFileid,attachmentId);
 return ;
}


}