package kielce.tu.weaii.telelearn.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import kielce.tu.weaii.telelearn.repositories.jpa.TaskJPARepository;
import kielce.tu.weaii.telelearn.models.courses.Task;
@Service
public class TaskAttachmentService {

@Autowired
 private TaskJPARepository taskjparepository;


}