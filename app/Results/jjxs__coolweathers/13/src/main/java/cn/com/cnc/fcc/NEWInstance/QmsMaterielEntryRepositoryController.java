package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QmsMaterielEntryRepositoryController {

 private QmsMaterielEntryRepository qmsmaterielentryrepository;


@GetMapping
("/findByMaterielIdAndFlagStatus")
public List<QmsMaterielEntry> findByMaterielIdAndFlagStatus(@RequestParam(name = "materielId") Integer materielId,@RequestParam(name = "flagStatus") String flagStatus){
  return qmsmaterielentryrepository.findByMaterielIdAndFlagStatus(materielId,flagStatus);
}


}