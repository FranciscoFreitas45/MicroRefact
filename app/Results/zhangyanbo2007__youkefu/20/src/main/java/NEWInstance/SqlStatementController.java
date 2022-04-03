package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SqlStatementController {

 private SqlStatement sqlstatement;

 private SqlStatement sqlstatement;


@PutMapping
("/close")
public void close(){
sqlstatement.close();
}


}