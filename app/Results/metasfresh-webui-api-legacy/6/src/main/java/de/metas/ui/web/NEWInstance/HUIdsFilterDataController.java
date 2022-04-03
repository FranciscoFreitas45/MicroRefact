package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HUIdsFilterDataController {

 private HUIdsFilterData huidsfilterdata;


@GetMapping
("/copy")
public HUIdsFilterData copy(){
  return huidsfilterdata.copy();
}


@GetMapping
("/newEmpty")
public HUIdsFilterData newEmpty(){
  return huidsfilterdata.newEmpty();
}


}