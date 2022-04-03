package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RolepowerlistDaoController {

 private RolepowerlistDao rolepowerlistdao;


@GetMapping
("/findname")
public List<Rolemenu> findname(@RequestParam(name = "id") Long id,@RequestParam(name = "roleid") Long roleid,@RequestParam(name = "bo") Boolean bo,@RequestParam(name = "le") Boolean le,@RequestParam(name = "name") String name){
  return rolepowerlistdao.findname(id,roleid,bo,le,name);
}


@GetMapping
("/findbyparentxianall")
public List<Rolemenu> findbyparentxianall(@RequestParam(name = "id") Long id,@RequestParam(name = "roleid") Long roleid,@RequestParam(name = "bo") Boolean bo,@RequestParam(name = "le") Boolean le){
  return rolepowerlistdao.findbyparentxianall(id,roleid,bo,le);
}


}