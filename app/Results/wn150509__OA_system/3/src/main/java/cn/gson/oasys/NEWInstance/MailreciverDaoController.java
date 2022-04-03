package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MailreciverDaoController {

 private MailreciverDao mailreciverdao;


@GetMapping
("/findByReadAndDelAndReciverId")
public List<Mailreciver> findByReadAndDelAndReciverId(@RequestParam(name = "read") Boolean read,@RequestParam(name = "del") Boolean del,@RequestParam(name = "reciverid") User reciverid){
  return mailreciverdao.findByReadAndDelAndReciverId(read,del,reciverid);
}


}