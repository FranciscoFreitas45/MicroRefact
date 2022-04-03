package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentSaveStatusController {

 private DocumentSaveStatus documentsavestatus;

 private DocumentSaveStatus documentsavestatus;


@GetMapping
("/isSavedOrDeleted")
public boolean isSavedOrDeleted(){
  return documentsavestatus.isSavedOrDeleted();
}


}