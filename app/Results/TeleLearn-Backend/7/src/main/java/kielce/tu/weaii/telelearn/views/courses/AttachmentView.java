package kielce.tu.weaii.telelearn.views.courses;
 import kielce.tu.weaii.telelearn.models.Attachment;
import lombok.Value;
@Value
public class AttachmentView {

 private Long id;

 private String fileName;


public AttachmentView form(Attachment model){
    return new AttachmentView(model.getId(), model.getFileName());
}


}