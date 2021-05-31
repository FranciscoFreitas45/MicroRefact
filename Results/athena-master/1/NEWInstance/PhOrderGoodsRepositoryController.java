import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class PhOrderGoodsRepositoryController {

 private PhOrderGoodsRepository phordergoodsrepository;


@PutMapping
("/updateStock")
public void updateStock(@RequestParam(name = "orderNo") String orderNo){
phordergoodsrepository.updateStock(orderNo);
}


}