package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.GeoCommonsClient;
public class GeoCommonsClientImpl implements GeoCommonsClient{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public void initializeClient(String username,String password){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/initializeClient"))
    .queryParam("username",username)
    .queryParam("password",password)
;
  restTemplate.put(builder.toUriString(), null);
}


public void checkUser(String username){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkUser"))
    .queryParam("username",username)
;
  restTemplate.put(builder.toUriString(), null);
}


public String uploadWmsDataSet(String layerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/uploadWmsDataSet"))
    .queryParam("layerId",layerId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public DataSetStatus checkDataSetStatus(String location){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/checkDataSetStatus"))
    .queryParam("location",location)
;  DataSetStatus aux = restTemplate.getForObject(builder.toUriString(), DataSetStatus.class);

 return aux;
}


public String createMap(String basemap,String extent,String title,String description){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createMap"))
    .queryParam("basemap",basemap)
    .queryParam("extent",extent)
    .queryParam("title",title)
    .queryParam("description",description)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public void addLayerToMap(String mapId,DataSetStatus dataSetStatus){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addLayerToMap"))
    .queryParam("mapId",mapId)
    .queryParam("dataSetStatus",dataSetStatus)
;
  restTemplate.put(builder.toUriString(), null);
}


}