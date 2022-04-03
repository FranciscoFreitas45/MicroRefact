package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProcessListDaoController {

 private ProcessListDao processlistdao;


@GetMapping
("/findlastthree")
public List<ProcessList> findlastthree(@RequestParam(name = "userid") long userid){
  return processlistdao.findlastthree(userid);
}


}