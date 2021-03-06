package run.halo.app.controller.admin.api;
 import org.springframework.data.domain.Sort.Direction.DESC;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import run.halo.app.model.dto.AttachmentDTO;
import run.halo.app.model.entity.Attachment;
import run.halo.app.model.enums.AttachmentType;
import run.halo.app.model.params.AttachmentParam;
import run.halo.app.model.params.AttachmentQuery;
import run.halo.app.service.AttachmentService;
@RestController
@RequestMapping("/api/admin/attachments")
public class AttachmentController {

 private  AttachmentService attachmentService;

public AttachmentController(AttachmentService attachmentService) {
    this.attachmentService = attachmentService;
}
@GetMapping
public Page<AttachmentDTO> pageBy(Pageable pageable,AttachmentQuery attachmentQuery){
    return attachmentService.pageDtosBy(pageable, attachmentQuery);
}


@PutMapping("{attachmentId:\\d+}")
@ApiOperation("Updates a attachment")
public AttachmentDTO updateBy(Integer attachmentId,AttachmentParam attachmentParam){
    Attachment attachment = attachmentService.getById(attachmentId);
    attachmentParam.update(attachment);
    return new AttachmentDTO().convertFrom(attachmentService.update(attachment));
}


@GetMapping("media_types")
@ApiOperation("Lists all of media types")
public List<String> listMediaTypes(){
    return attachmentService.listAllMediaType();
}


@GetMapping("{id:\\d+}")
@ApiOperation("Gets attachment detail by id")
public AttachmentDTO getBy(Integer id){
    Attachment attachment = attachmentService.getById(id);
    return attachmentService.convertToDto(attachment);
}


@DeleteMapping
@ApiOperation("Deletes attachments permanently in batch by id array")
public List<Attachment> deletePermanentlyInBatch(List<Integer> ids){
    return attachmentService.removePermanently(ids);
}


@PostMapping(value = "uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
@ApiOperation("Uploads multi files (Invalid in Swagger UI)")
public List<AttachmentDTO> uploadAttachments(MultipartFile[] files){
    List<AttachmentDTO> result = new LinkedList<>();
    for (MultipartFile file : files) {
        // Upload single file
        Attachment attachment = attachmentService.upload(file);
        // Convert and add
        result.add(attachmentService.convertToDto(attachment));
    }
    return result;
}


@GetMapping("types")
@ApiOperation("Lists all of types.")
public List<AttachmentType> listTypes(){
    return attachmentService.listAllType();
}


@PostMapping("upload")
@ApiOperation("Uploads single file")
public AttachmentDTO uploadAttachment(MultipartFile file){
    return attachmentService.convertToDto(attachmentService.upload(file));
}


@DeleteMapping("{id:\\d+}")
@ApiOperation("Deletes attachment permanently by id")
public AttachmentDTO deletePermanently(Integer id){
    return attachmentService.convertToDto(attachmentService.removePermanently(id));
}


}