package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PlanDaoController {

 private PlanDao plandao;


@GetMapping
("/findByUserlimit")
public List<Plan> findByUserlimit(@RequestParam(name = "userid") long userid){
  return plandao.findByUserlimit(userid);
}


}