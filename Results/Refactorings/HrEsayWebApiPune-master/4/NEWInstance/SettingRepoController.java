import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class SettingRepoController {

 private SettingRepo settingrepo;


@GetMapping
("/findByGroup")
public List<Setting> findByGroup(@RequestParam(name = "string") String string){
  return settingrepo.findByGroup(string);
}


@GetMapping
("/findByKey")
public Setting findByKey(@RequestParam(name = "key") String key){
  return settingrepo.findByKey(key);
}


}