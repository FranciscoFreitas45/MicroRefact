import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class QmsEnclosureRepositoryController {

 private QmsEnclosureRepository qmsenclosurerepository;


@GetMapping
("/findAllByInspectionInfoIdAndInspectionKbn")
public List<QmsEnclosure> findAllByInspectionInfoIdAndInspectionKbn(@RequestParam(name = "inspectionInfoId") Integer inspectionInfoId,@RequestParam(name = "inpectionKbn") String inpectionKbn){
  return qmsenclosurerepository.findAllByInspectionInfoIdAndInspectionKbn(inspectionInfoId,inpectionKbn);
}


}