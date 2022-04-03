package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AugmentedSolrRecordRetrieverController {

 private AugmentedSolrRecordRetriever augmentedsolrrecordretriever;


@GetMapping
("/getOgcAugmentedSolrRecord")
public AugmentedSolrRecord getOgcAugmentedSolrRecord(@RequestParam(name = "solrRecord") SolrRecord solrRecord){
  return augmentedsolrrecordretriever.getOgcAugmentedSolrRecord(solrRecord);
}


}