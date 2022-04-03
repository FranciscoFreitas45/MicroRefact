package Interface;
public interface CgUploadServiceI {

   public void writeBack(String cgFormId,String cgFormName,String cgFormField,String fileId,String fileUrl);
   public void deleteFile(CgUploadEntity file);
}