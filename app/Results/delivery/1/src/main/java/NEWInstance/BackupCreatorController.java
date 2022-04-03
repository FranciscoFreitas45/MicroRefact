package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BackupCreatorController {

 private BackupCreator backupcreator;


@GetMapping
("/createBackup")
public String createBackup(){
  return backupcreator.createBackup();
}


}