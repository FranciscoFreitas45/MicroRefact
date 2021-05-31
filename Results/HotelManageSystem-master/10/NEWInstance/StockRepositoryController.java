import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class StockRepositoryController {

 private StockRepository stockrepository;


@GetMapping
("/findByStockType")
public List<Stock> findByStockType(@RequestParam(name = "stockType") StockType stockType){
  return stockrepository.findByStockType(stockType);
}


}