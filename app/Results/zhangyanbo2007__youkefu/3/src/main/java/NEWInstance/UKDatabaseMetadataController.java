package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UKDatabaseMetadataController {

 private UKDatabaseMetadata ukdatabasemetadata;

 private UKDatabaseMetadata ukdatabasemetadata;


@GetMapping
("/loadTables")
public List<UKTableMetaData> loadTables(@RequestParam(name = "name") String name,@RequestParam(name = "schema") String schema,@RequestParam(name = "catalog") String catalog,@RequestParam(name = "isQuoted") boolean isQuoted){
  return ukdatabasemetadata.loadTables(name,schema,catalog,isQuoted);
}


@GetMapping
("/loadTable")
public UKTableMetaData loadTable(@RequestParam(name = "name") String name,@RequestParam(name = "schema") String schema,@RequestParam(name = "catalog") String catalog,@RequestParam(name = "isQuoted") boolean isQuoted){
  return ukdatabasemetadata.loadTable(name,schema,catalog,isQuoted);
}


}