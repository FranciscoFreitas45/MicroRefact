package com.example.steam.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.steam.Interface.SpikeGameService;
public class SpikeGameServiceImpl implements SpikeGameService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public List<SpikeGameDetail> findAllSpikeGameDetail(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAllSpikeGameDetail"))
;  List<SpikeGameDetail> aux = restTemplate.getForObject(builder.toUriString(), List<SpikeGameDetail>.class);

 return aux;
}


public SpikeGameDetail findOneSpikeGameDetail(long spikeId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findOneSpikeGameDetail"))
    .queryParam("spikeId",spikeId)
;  SpikeGameDetail aux = restTemplate.getForObject(builder.toUriString(), SpikeGameDetail.class);

 return aux;
}


}