package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CgFormVersionDaoController {

 private CgFormVersionDao cgformversiondao;


@GetMapping
("/getCgFormById")
public CgFormHeadEntity getCgFormById(@RequestParam(name = "id") String id){
  return cgformversiondao.getCgFormById(id);
}


}