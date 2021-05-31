import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QiniuPropertiesController {

 private QiniuProperties qiniuproperties;


@GetMapping
("/getUrl")
public String getUrl(){
  return qiniuproperties.getUrl();
}


}