package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EntrepotStatusRepositoryController {

 private EntrepotStatusRepository entrepotstatusrepository;


@GetMapping
("/getTotalSize")
public List<EntrepotStatus> getTotalSize(){
  return entrepotstatusrepository.getTotalSize();
}


@GetMapping
("/findByEnterCodeAndMaterialCode")
public List<EntrepotStatus> findByEnterCodeAndMaterialCode(@RequestParam(name = "enterCode") String enterCode,@RequestParam(name = "materialCode") String materialCode){
  return entrepotstatusrepository.findByEnterCodeAndMaterialCode(enterCode,materialCode);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return entrepotstatusrepository.delete(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return entrepotstatusrepository.save(Object);
}


@GetMapping
("/findBeforeDate")
public List<EntrepotStatus> findBeforeDate(@RequestParam(name = "date") Date date){
  return entrepotstatusrepository.findBeforeDate(date);
}


}