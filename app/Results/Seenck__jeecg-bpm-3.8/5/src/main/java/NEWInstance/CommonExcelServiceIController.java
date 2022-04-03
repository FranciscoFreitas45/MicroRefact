package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CommonExcelServiceIController {

 private CommonExcelServiceI commonexcelservicei;


@GetMapping
("/exportExcel")
public HSSFWorkbook exportExcel(@RequestParam(name = "title") String title,@RequestParam(name = "titleSet") Collection<?> titleSet,@RequestParam(name = "dataSet") Collection<?> dataSet){
  return commonexcelservicei.exportExcel(title,titleSet,dataSet);
}


}