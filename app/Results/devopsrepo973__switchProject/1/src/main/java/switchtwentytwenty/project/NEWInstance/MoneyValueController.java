package switchtwentytwenty.project.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MoneyValueController {

 private MoneyValue moneyvalue;


@GetMapping
("/isPositiveOrZero")
public boolean isPositiveOrZero(){
  return moneyvalue.isPositiveOrZero();
}


@GetMapping
("/compare")
public int compare(@RequestParam(name = "moneyValue") MoneyValue moneyValue){
  return moneyvalue.compare(moneyValue);
}


@GetMapping
("/subtract")
public MoneyValue subtract(@RequestParam(name = "moneyValue") MoneyValue moneyValue){
  return moneyvalue.subtract(moneyValue);
}


@GetMapping
("/sum")
public MoneyValue sum(@RequestParam(name = "moneyValue") MoneyValue moneyValue){
  return moneyvalue.sum(moneyValue);
}


@GetMapping
("/toDouble")
public double toDouble(){
  return moneyvalue.toDouble();
}


@GetMapping
("/toString")
public String toString(){
  return moneyvalue.toString();
}


}