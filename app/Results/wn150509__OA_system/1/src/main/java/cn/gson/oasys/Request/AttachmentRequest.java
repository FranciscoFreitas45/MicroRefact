package cn.gson.oasys.Request;
import cn.gson.oasys.DTO.Attachment;
public interface AttachmentRequest {

   public Attachment getProFileid(Long attachmentId);
   public void setProFileid(Attachment proFileid,Long attachmentId);
}