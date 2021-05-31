import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhWardrobeRepositoryController {

 private PhWardrobeRepository phwardroberepository;


@GetMapping
("/deleteByGoodsIds")
public int deleteByGoodsIds(@RequestParam(name = "goodsIds") List<Long> goodsIds){
  return phwardroberepository.deleteByGoodsIds(goodsIds);
}


}