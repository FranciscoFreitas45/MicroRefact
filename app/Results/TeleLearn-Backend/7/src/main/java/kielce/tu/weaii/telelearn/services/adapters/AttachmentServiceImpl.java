package kielce.tu.weaii.telelearn.services.adapters;
 import kielce.tu.weaii.telelearn.exceptions.AttachmentNotFound;
import kielce.tu.weaii.telelearn.exceptions.AuthorizationException;
import kielce.tu.weaii.telelearn.models.Attachment;
import kielce.tu.weaii.telelearn.models.User;
import kielce.tu.weaii.telelearn.models.UserRole;
import kielce.tu.weaii.telelearn.repositories.ports.AttachmentRepository;
import kielce.tu.weaii.telelearn.security.UserServiceDetailsImpl;
import kielce.tu.weaii.telelearn.services.ports.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
@Service
@RequiredArgsConstructor
public class AttachmentServiceImpl implements AttachmentService{

 private  AttachmentRepository repository;

 private  UserServiceDetailsImpl userServiceDetails;


@Override
@Transactional
public Attachment getById(Long id){
    Attachment attachment = repository.getById(id).orElseThrow(() -> new AttachmentNotFound(id));
    checkAttachmentAuthorization(id, attachment);
    return attachment;
}


public void checkAttachmentAuthorization(Long id,Attachment attachment){
    User currentUser = userServiceDetails.getCurrentUser();
    if (attachment.getPost() != null && ((currentUser.getUserRole().equals(UserRole.TEACHER) && !attachment.getPost().getCourse().getOwner().getId().equals(currentUser.getId())) || (currentUser.getUserRole().equals(UserRole.STUDENT) && attachment.getPost().getCourse().getStudents().stream().noneMatch(entry -> entry.getStudent().getId().equals(currentUser.getId()))))) {
        throw new AuthorizationException("załącznik", currentUser.getId(), id);
    }
    if (attachment.getTask() != null && ((currentUser.getUserRole().equals(UserRole.TEACHER) && !attachment.getTask().getCourse().getOwner().getId().equals(currentUser.getId())) || (currentUser.getUserRole().equals(UserRole.STUDENT) && attachment.getTask().getCourse().getStudents().stream().noneMatch(entry -> entry.getStudent().getId().equals(currentUser.getId()))))) {
        throw new AuthorizationException("załącznik", currentUser.getId(), id);
    }
}


}