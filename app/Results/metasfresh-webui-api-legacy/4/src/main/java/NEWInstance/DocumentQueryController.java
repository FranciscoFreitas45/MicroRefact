package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DocumentQueryController {

 private DocumentQuery documentquery;

 private DocumentQuery documentquery;


@GetMapping
("/isNoSorting")
public boolean isNoSorting(){
  return documentquery.isNoSorting();
}


}