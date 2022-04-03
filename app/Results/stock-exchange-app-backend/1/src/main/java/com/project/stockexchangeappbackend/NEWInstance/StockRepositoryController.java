package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
  import com.project.stockexchangeappbackend.repository.StockRepository;
 import com.project.stockexchangeappbackend.entity.Stock;
 import java.util.*;
@RestController
@CrossOrigin
public class StockRepositoryController {

 private StockRepository stockrepository;


@GetMapping
("/findByIdAndIsDeletedFalse")
public Optional<Stock> findByIdAndIsDeletedFalse(@RequestParam(name = "id") Long id){
  return stockrepository.findByIdAndIsDeletedFalse(id);
}


@GetMapping
("/findAll")
public List<Stock> findAll(){
  return stockrepository.findAll();
}


@GetMapping
("/findByAbbreviationIgnoreCaseAndIsDeletedFalse")
public Optional<Stock> findByAbbreviationIgnoreCaseAndIsDeletedFalse(@RequestParam(name = "abbreviation") String abbreviation){
  return stockrepository.findByAbbreviationIgnoreCaseAndIsDeletedFalse(abbreviation);
}


@GetMapping
("/save")
public <S extends Stock> S save(@RequestParam(name = "s") S s){
  return stockrepository.save(s);
}


@GetMapping
("/saveAll")
public <S extends Stock> List<S> saveAll(@RequestParam(name = "var1") Iterable<S> var1){
  return stockrepository.saveAll(var1);
}


@GetMapping
("/findByAbbreviationIgnoreCase")
public Optional<Stock> findByAbbreviationIgnoreCase(@RequestParam(name = "abbreviation") String abbreviation){
  return stockrepository.findByAbbreviationIgnoreCase(abbreviation);
}


@GetMapping
("/findByNameIgnoreCase")
public Optional<Stock> findByNameIgnoreCase(@RequestParam(name = "name") String name){
  return stockrepository.findByNameIgnoreCase(name);
}


}