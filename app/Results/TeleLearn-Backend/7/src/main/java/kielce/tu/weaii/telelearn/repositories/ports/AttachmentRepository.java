package kielce.tu.weaii.telelearn.repositories.ports;
 import kielce.tu.weaii.telelearn.models.Attachment;
import java.util.Optional;
public interface AttachmentRepository {


public Optional<Attachment> getById(Long id)
;

public void delete(Attachment attachment)
;

}