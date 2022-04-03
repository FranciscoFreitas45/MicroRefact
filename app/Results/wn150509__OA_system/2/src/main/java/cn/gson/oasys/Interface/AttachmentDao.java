package cn.gson.oasys.Interface;
public interface AttachmentDao {

   public Object save(Object Object);
   public Attachment findByAttachmentPath(String AttachmentPath);
}