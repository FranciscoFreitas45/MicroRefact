package org.opengeoportal.proxy.controllers;
 import org.opengeoportal.metadata.LayerInfoRetriever;
import org.opengeoportal.ogc.AugmentedSolrRecord;
import org.opengeoportal.ogc.AugmentedSolrRecordRetriever;
import org.opengeoportal.ogc.OwsInfo;
import org.opengeoportal.solr.SolrRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/info")
public class GetOgcInfoController {

 final  Logger logger;

@Autowired
 private AugmentedSolrRecordRetriever augmentedSolrRecordRetriever;

@Autowired
 private LayerInfoRetriever layerInfoRetriever;


@RequestMapping(value = "wmsInfo", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
public OwsInfo wmsInfo(String layerId){
    return augmentedSolrRecordRetriever.getWmsInfo(layerId);
}


@RequestMapping(value = "ogcData", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
public OwsInfo ogcDataInfo(String layerId){
    return augmentedSolrRecordRetriever.getOgcDataInfo(layerId);
}


@RequestMapping(method = RequestMethod.GET, produces = "application/json")
@ResponseBody
public AugmentedSolrRecord getLayerInfo(String layerId){
    return augmentedSolrRecordRetriever.getOgcAugmentedSolrRecord(layerId);
}


@RequestMapping(value = "ogp", method = RequestMethod.GET, produces = "application/json")
@ResponseBody
public SolrRecord ogcSolrInfo(String layerId,Boolean includeMetadata){
    SolrRecord record = layerInfoRetriever.getAllLayerInfo(layerId);
    if (includeMetadata) {
        return record;
    } else {
        record.setFgdcText(null);
        return record;
    }
}


}