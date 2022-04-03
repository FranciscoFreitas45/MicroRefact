package org.opengeoportal.proxy.controllers;
 import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.opengeoportal.config.proxy.ProxyConfigRetriever;
import org.opengeoportal.metadata.LayerInfoRetriever;
import org.opengeoportal.solr.SolrRecord;
import org.opengeoportal.utilities.OgpUtils;
import org.opengeoportal.utilities.http.HttpRequester;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.opengeoportal.Interface.HttpRequester;
import org.opengeoportal.Interface.ProxyConfigRetriever;
@Controller
@RequestMapping("/featureInfo")
public class GetFeatureInfoController {

 private  int NUMBER_OF_FEATURES;

 private  String SRS_CODE;

 private  String RESPONSE_FORMAT;

 private  String EXCEPTION_FORMAT;

 final  Logger logger;

@Autowired
@Qualifier("httpRequester.generic")
 private  HttpRequester httpRequester;

@Autowired
 private  LayerInfoRetriever layerInfoRetriever;

@Autowired
 private  ProxyConfigRetriever proxyConfigRetriever;


public SolrRecord getSolrRecord(String layerId){
    Set<String> layerIds = new HashSet<String>();
    layerIds.add(layerId);
    List<SolrRecord> allLayerInfo = this.layerInfoRetriever.fetchAllowedRecords(layerIds);
    if (allLayerInfo.isEmpty()) {
        throw new Exception("No allowed records returned for Layer Id: ['" + layerId + "'");
    }
    SolrRecord layerInfo = allLayerInfo.get(0);
    return layerInfo;
}


public String createRequestFromSolrRecord(SolrRecord layerInfo,String xCoord,String yCoord,String bbox,String height,String width){
    String layerName = OgpUtils.getLayerNameNS(layerInfo.getWorkspaceName(), layerInfo.getName());
    return this.createWmsGetFeatureInfoQuery(layerName, xCoord, yCoord, bbox, height, width);
}


@RequestMapping(method = RequestMethod.GET)
public void getFeatureInfo(String layerId,String bbox,String xCoord,String yCoord,String width,String height,HttpServletRequest request,HttpServletResponse response){
    SolrRecord layerInfo = getSolrRecord(layerId);
    String wmsEndpoint = proxyConfigRetriever.getInternalUrl("wms", layerInfo.getInstitution(), layerInfo.getAccess(), layerInfo.getLocation());
    // filter any query terms
    wmsEndpoint = OgpUtils.filterQueryString(wmsEndpoint);
    String query = createRequestFromSolrRecord(layerInfo, xCoord, yCoord, bbox, height, width);
    logger.info("executing WMS getFeatureRequest: " + wmsEndpoint + "?" + query);
    sendGetRequest(wmsEndpoint, query, request, response);
}


public String createWmsGetFeatureInfoQuery(String layerName,String xCoord,String yCoord,String bbox,String height,String width){
    // in caps to support ogc services through arcgis server 9.x
    String query = "SERVICE=WMS&VERSION=1.1.1&REQUEST=GetFeatureInfo&INFO_FORMAT=" + RESPONSE_FORMAT + "&SRS=" + SRS_CODE + "&FEATURE_COUNT=" + NUMBER_OF_FEATURES + "&STYLES=&HEIGHT=" + height + "&WIDTH=" + width + "&BBOX=" + bbox + "&X=" + xCoord + "&Y=" + yCoord + "&QUERY_LAYERS=" + layerName + "&LAYERS=" + layerName + "&EXCEPTIONS=" + EXCEPTION_FORMAT;
    return query;
}


public void sendGetRequest(String wmsEndpoint,String wmsQuery,HttpServletRequest request,HttpServletResponse response){
    /*if (!wmsEndpoint.contains("http")){
			//this is a relative path
			request.getRequestDispatcher(wmsEndpoint + "?" + wmsQuery).forward(request, response);

		} else {*/
    InputStream input = null;
    try {
        String method = "GET";
        input = httpRequester.sendRequest(wmsEndpoint, wmsQuery, method);
        logger.debug(httpRequester.getContentType());
        response.setContentType(httpRequester.getContentType());
        IOUtils.copy(input, response.getOutputStream());
    } finally {
        IOUtils.closeQuietly(input);
    }
// }
}


}