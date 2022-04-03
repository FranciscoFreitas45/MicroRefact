package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ParadeServiceController {

 private ParadeService paradeservice;


@GetMapping
("/findOne")
public Parade findOne(@RequestParam(name = "id") int id){
  return paradeservice.findOne(id);
}


@GetMapping
("/findAll")
public List<Parade> findAll(){
  return paradeservice.findAll();
}


@GetMapping
("/isEmpty")
public Object isEmpty(@RequestParam(name = "Object") Object Object){
  return paradeservice.isEmpty(Object);
}


@GetMapping
("/save")
public Parade save(@RequestParam(name = "parade") Parade parade){
  return paradeservice.save(parade);
}


@GetMapping
("/getParadesByArea")
public List<Parade> getParadesByArea(@RequestParam(name = "area") Area area){
  return paradeservice.getParadesByArea(area);
}


@GetMapping
("/hasArea")
public Boolean hasArea(@RequestParam(name = "chapter") Chapter chapter){
  return paradeservice.hasArea(chapter);
}


@GetMapping
("/filterParadesChapter")
public List<Parade> filterParadesChapter(@RequestParam(name = "chapter") Chapter chapter,@RequestParam(name = "option") String option){
  return paradeservice.filterParadesChapter(chapter,option);
}


@GetMapping
("/reconstrucParadeStatus")
public Parade reconstrucParadeStatus(@RequestParam(name = "parade") Parade parade){
  return paradeservice.reconstrucParadeStatus(parade);
}


@PutMapping
("/deleteParadetest")
public void deleteParadetest(@RequestParam(name = "parade") Parade parade){
paradeservice.deleteParadetest(parade);
}


@PutMapping
("/putOrDeletePath")
public void putOrDeletePath(@RequestParam(name = "paradeId") Integer paradeId){
paradeservice.putOrDeletePath(paradeId);
}


}