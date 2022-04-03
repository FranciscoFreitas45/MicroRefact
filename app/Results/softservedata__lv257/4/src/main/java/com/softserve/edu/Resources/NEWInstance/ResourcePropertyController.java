package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourcePropertyController {

 private ResourceTypeDAOImpl resourcetypedaoimpl;


@GetMapping
("/equals/{id}")
public boolean equals(@PathVariable(name = "id") Long id,@RequestParam(name = "o") Object o){
 return resourcetypedaoimpl.equals(id,o);
}


@GetMapping
("/compareTo/{id}")
public int compareTo(@PathVariable(name = "id") Long id,@RequestParam(name = "o") ResourceProperty o){
 return resourcetypedaoimpl.compareTo(id,o);
}


}