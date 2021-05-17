import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
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