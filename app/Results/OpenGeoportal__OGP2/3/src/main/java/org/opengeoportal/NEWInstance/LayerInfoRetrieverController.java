package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LayerInfoRetrieverController {

 private LayerInfoRetriever layerinforetriever;


@GetMapping
("/fetchAllowedRecords")
public List<SolrRecord> fetchAllowedRecords(@RequestParam(name = "layerIdSet") Set<String> layerIdSet){
  return layerinforetriever.fetchAllowedRecords(layerIdSet);
}


@GetMapping
("/getAllLayerInfo")
public SolrRecord getAllLayerInfo(@RequestParam(name = "layerId") String layerId){
  return layerinforetriever.getAllLayerInfo(layerId);
}


@GetMapping
("/getSolrServer")
public SolrServer getSolrServer(){
  return layerinforetriever.getSolrServer();
}


@GetMapping
("/query")
public Object query(@RequestParam(name = "Object") Object Object){
  return layerinforetriever.query(Object);
}


@GetMapping
("/fetchAllLayerInfo")
public List<SolrRecord> fetchAllLayerInfo(@RequestParam(name = "layerIds") Set<String> layerIds){
  return layerinforetriever.fetchAllLayerInfo(layerIds);
}


}