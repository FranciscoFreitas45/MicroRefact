package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FacetFilterViewCacheMapController {

 private FacetFilterViewCacheMap facetfilterviewcachemap;


@GetMapping
("/newInstance")
public FacetFilterViewCacheMap newInstance(){
  return facetfilterviewcachemap.newInstance();
}


}