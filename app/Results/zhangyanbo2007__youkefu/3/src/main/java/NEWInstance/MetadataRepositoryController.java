package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MetadataRepositoryController {

 private MetadataRepository metadatarepository;


@GetMapping
("/findByTablename")
public MetadataTable findByTablename(@RequestParam(name = "tablename") String tablename){
  return metadatarepository.findByTablename(tablename);
}


}