package org.opengeoportal.download.methods;
 import org.opengeoportal.download.types.generated.ogc.wms_describelayer.WMSDescribeLayerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.client.RestTemplate;
import java.util.Map;
import java.util.Collections;
public class WmsDescribeLayerImpl implements WmsDescribeLayer{

 private String VERSION;

 final  Logger logger;

@Autowired
@Qualifier("ogcRestTemplate")
 private  RestTemplate restTemplate;


@Override
public WMSDescribeLayerResponse describeLayer(String baseUrl,String qualifiedLayerName){
    Map<String, String> vars = Collections.singletonMap("qualifiedLayerName", qualifiedLayerName);
    WMSDescribeLayerResponse result = restTemplate.getForObject(baseUrl + "?service=WMS&version=" + VERSION + "&request=DescribeLayer&layers={qualifiedLayerName}", WMSDescribeLayerResponse.class, vars);
    return result;
}


public void setRestTemplate(RestTemplate restTemplate){
    this.restTemplate = restTemplate;
}


public RestTemplate getRestTemplate(){
    return restTemplate;
}


}