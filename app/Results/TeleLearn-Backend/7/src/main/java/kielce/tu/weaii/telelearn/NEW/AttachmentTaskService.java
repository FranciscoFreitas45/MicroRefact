package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.repositories.jpa.AttachmentJPARepository;
import kielce.tu.weaii.telelearn.models.Attachment;
@Service
public class AttachmentTaskService {

@Autowired
 private AttachmentJPARepository attachmentjparepository;


}