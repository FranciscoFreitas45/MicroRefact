package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JeecgDictDaoController {

 private JeecgDictDao jeecgdictdao;


@GetMapping
("/queryCustomDict")
public List<DictEntity> queryCustomDict(@RequestParam(name = "dicTable") String dicTable,@RequestParam(name = "dicCode") String dicCode,@RequestParam(name = "dicText") String dicText){
  return jeecgdictdao.queryCustomDict(dicTable,dicCode,dicText);
}


@GetMapping
("/querySystemDict")
public List<DictEntity> querySystemDict(@RequestParam(name = "dicCode") String dicCode){
  return jeecgdictdao.querySystemDict(dicCode);
}


}