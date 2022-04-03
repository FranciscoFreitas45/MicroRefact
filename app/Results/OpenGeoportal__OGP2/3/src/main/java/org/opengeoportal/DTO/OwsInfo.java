package org.opengeoportal.DTO;
 import java.util.List;
import java.util.Map;
public class OwsInfo {

 final  OwsType type;

 private OwsProtocol owsProtocol;

 private Map<String,String> infoMap;

 private OwsDescribeInfo owsDescribeInfo;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://4";


public OwsDescribeInfo getOwsDescribeInfo(){
    return owsDescribeInfo;
}


public Map<String,String> getInfoMap(){
    return infoMap;
}


public OwsProtocol getOwsProtocol(){
    return owsProtocol;
}


public OwsInfo findWcsInfo(List<OwsInfo> info){
    for (OwsInfo infoBit : info) {
        if (infoBit.getOwsProtocol().equals(OwsProtocol.WCS)) {
            return infoBit;
        }
    }
    throw new Exception("No WCS Info found!");
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findWcsInfo"))

.queryParam("info",info)
;
OwsInfo aux = restTemplate.getForObject(builder.toUriString(),OwsInfo.class);
return aux;
}


public OwsInfo findWfsInfo(List<OwsInfo> info){
    for (OwsInfo infoBit : info) {
        if (infoBit.getOwsProtocol().equals(OwsProtocol.WFS)) {
            return infoBit;
        }
    }
    throw new Exception("No WFS Info found!");
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findWfsInfo"))

.queryParam("info",info)
;
OwsInfo aux = restTemplate.getForObject(builder.toUriString(),OwsInfo.class);
return aux;
}


public OwsInfo findWmsInfo(List<OwsInfo> info){
    for (OwsInfo infoBit : info) {
        if (infoBit.getOwsProtocol().equals(OwsProtocol.WMS)) {
            return infoBit;
        }
    }
    throw new Exception("No WMS Info found!");
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findWmsInfo"))

.queryParam("info",info)
;
OwsInfo aux = restTemplate.getForObject(builder.toUriString(),OwsInfo.class);
return aux;
}


}