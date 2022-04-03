package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SearchCriteriaController {

 private SearchCriteria searchcriteria;

 private SearchCriteria searchcriteria;


@PutMapping
("/addSortBy")
public void addSortBy(@RequestParam(name = "ordersBy") String ordersBy){
searchcriteria.addSortBy(ordersBy);
}


@PutMapping
("/setOrder")
public void setOrder(@RequestParam(name = "order") Order order){
searchcriteria.setOrder(order);
}


@PutMapping
("/setWhatToFilter")
public void setWhatToFilter(@RequestParam(name = "filterBy") List<String> filterBy){
searchcriteria.setWhatToFilter(filterBy);
}


@PutMapping
("/setInitialRegister")
public void setInitialRegister(@RequestParam(name = "initialRegister") Integer initialRegister){
searchcriteria.setInitialRegister(initialRegister);
}


@PutMapping
("/setNumberOfRegisters")
public void setNumberOfRegisters(@RequestParam(name = "numberOfRegisters") Integer numberOfRegisters){
searchcriteria.setNumberOfRegisters(numberOfRegisters);
}


@PutMapping
("/setFilter")
public void setFilter(@RequestParam(name = "filter") String filter){
searchcriteria.setFilter(filter);
}


}