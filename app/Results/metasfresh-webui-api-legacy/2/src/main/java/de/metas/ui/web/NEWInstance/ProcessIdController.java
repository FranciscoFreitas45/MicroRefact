package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProcessIdController {

 private ProcessId processid;


@GetMapping
("/toDocumentId")
public DocumentId toDocumentId(){
  return processid.toDocumentId();
}


}