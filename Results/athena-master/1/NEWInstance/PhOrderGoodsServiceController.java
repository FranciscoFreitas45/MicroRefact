import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhOrderGoodsServiceController {

 private PhOrderGoodsService phordergoodsservice;


@GetMapping
("/findByBrandId")
public Page<PhOrderGoods> findByBrandId(@RequestParam(name = "brandId") String brandId,@RequestParam(name = "page") Pageable page){
  return phordergoodsservice.findByBrandId(brandId,page);
}


}