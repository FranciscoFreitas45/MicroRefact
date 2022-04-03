package run.halo.app.model.params;
 import lombok.Data;
import run.halo.app.model.enums.AttachmentType;
@Data
public class AttachmentQuery {

 private  String keyword;

 private  String mediaType;

 private  AttachmentType attachmentType;


}