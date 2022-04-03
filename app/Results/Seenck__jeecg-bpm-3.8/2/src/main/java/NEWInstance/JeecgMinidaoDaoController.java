package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JeecgMinidaoDaoController {

 private JeecgMinidaoDao jeecgminidaodao;


@GetMapping
("/getProCity")
public List<Map<String,String>> getProCity(@RequestParam(name = "pid") String pid){
  return jeecgminidaodao.getProCity(pid);
}


@GetMapping
("/getAllRegions")
public List<Map<String,String>> getAllRegions(){
  return jeecgminidaodao.getAllRegions();
}


}