package kielce.tu.weaii.telelearn.controllers;
 import kielce.tu.weaii.telelearn.models.Attachment;
import kielce.tu.weaii.telelearn.services.ports.AttachmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.transaction.Transactional;
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class FileController {

 private  AttachmentService attachmentService;


@GetMapping(path = "/{id}")
@Transactional
public ResponseEntity<byte[]> getById(Long id){
    Attachment attachment = attachmentService.getById(id);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", attachment.getFileName())).header(HttpHeaders.CONTENT_TYPE, attachment.getFileType()).body(attachment.getAttachmentData().get(0).getData());
}


}