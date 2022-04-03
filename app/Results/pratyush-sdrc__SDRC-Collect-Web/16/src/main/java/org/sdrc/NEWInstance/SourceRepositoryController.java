package org.sdrc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SourceRepositoryController {

 private SourceRepository sourcerepository;


@GetMapping
("/findByIUS_Nid")
public List<UtIndicatorClassificationsEn> findByIUS_Nid(@RequestParam(name = "iusNid") Integer iusNid){
  return sourcerepository.findByIUS_Nid(iusNid);
}


@GetMapping
("/findByIUSandLevel_Nid")
public List<UtIndicatorClassificationsEn> findByIUSandLevel_Nid(@RequestParam(name = "iusNid") Integer iusNid,@RequestParam(name = "levelNid") Integer levelNid){
  return sourcerepository.findByIUSandLevel_Nid(iusNid,levelNid);
}


}