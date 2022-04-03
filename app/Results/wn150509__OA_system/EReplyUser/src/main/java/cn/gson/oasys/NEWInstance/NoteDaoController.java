package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NoteDaoController {

 private NoteDao notedao;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return notedao.findOne(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return notedao.save(Object);
}


@GetMapping
("/finduserid")
public Noteuser finduserid(@RequestParam(name = "noteid") long noteid,@RequestParam(name = "userId") Long userId){
  return notedao.finduserid(noteid,userId);
}


@GetMapping
("/findByCatalogId")
public List<Note> findByCatalogId(@RequestParam(name = "catalogId") long catalogId,@RequestParam(name = "userid") long userid){
  return notedao.findByCatalogId(catalogId,userid);
}


}