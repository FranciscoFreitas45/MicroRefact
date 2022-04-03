package run.halo.app.Interface;
public interface FileHandlers {

   public UploadResult upload(MultipartFile file,AttachmentType attachmentType);
   public void delete(Attachment attachment);
}