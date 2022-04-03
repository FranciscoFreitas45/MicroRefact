package run.halo.app.service;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.exception.FileOperationException;
import run.halo.app.model.dto.AttachmentDTO;
import run.halo.app.model.entity.Attachment;
import run.halo.app.model.enums.AttachmentType;
import run.halo.app.model.params.AttachmentQuery;
import run.halo.app.service.base.CrudService;
public interface AttachmentService extends CrudService<Attachment, Integer>{


@NonNull
public Page<AttachmentDTO> pageDtosBy(Pageable pageable,AttachmentQuery attachmentQuery)
;

@NonNull
public Attachment upload(MultipartFile file)
;

@NonNull
public List<Attachment> removePermanently(Collection<Integer> ids)
;

public List<AttachmentType> listAllType()
;

@NonNull
public AttachmentDTO convertToDto(Attachment attachment)
;

public List<String> listAllMediaType()
;

}