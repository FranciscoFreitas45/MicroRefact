package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SearchConfigRetrieverController {

 private SearchConfigRetriever searchconfigretriever;


@GetMapping
("/getInternalSearchUrl")
public URL getInternalSearchUrl(){
  return searchconfigretriever.getInternalSearchUrl();
}


}