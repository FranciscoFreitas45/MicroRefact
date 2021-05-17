import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class AssetLogRepoController {

 private AssetLogRepo assetlogrepo;


@GetMapping
("/save")
public AssetLog save(@RequestParam(name = "log") AssetLog log){
  return assetlogrepo.save(log);
}


}