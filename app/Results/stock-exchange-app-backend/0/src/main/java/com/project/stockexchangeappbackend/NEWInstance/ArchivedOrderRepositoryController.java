package com.project.stockexchangeappbackend.NEWInstance;
import org.springframework.web.bind.annotation.*;
import com.project.stockexchangeappbackend.repository.ArchivedOrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
 import com.project.stockexchangeappbackend.service.*;
 import com.project.stockexchangeappbackend.entity.*;
 import com.project.stockexchangeappbackend.DTO.*;
 import java.util.*;
@RestController
@CrossOrigin
public class ArchivedOrderRepositoryController {

 private ArchivedOrderRepository archivedorderrepository;


@GetMapping
("/saveAll")
public <S extends ArchivedOrder> List<S> saveAll(@RequestParam(name = "var1") Iterable<S> var1){
  return archivedorderrepository.saveAll(var1);
}


}