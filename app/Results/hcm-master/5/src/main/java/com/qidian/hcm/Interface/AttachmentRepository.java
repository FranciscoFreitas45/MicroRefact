package com.qidian.hcm.Interface;
public interface AttachmentRepository {

   public Object save(Object Object);
   public Optional<Attachment> findByFileId(Long fileId);
}