package org.jugbd.mnet.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.jugbd.mnet.DTO.PictureInformation;
import org.jugbd.mnet.Request.PictureInformationRequest;
public class PictureInformationRequestImpl implements PictureInformationRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public PictureInformation getPictureInformation(Long id){
 PictureInformation aux = restTemplate.getForObject("http://5/OutdoorRegister/{id}/PictureInformation/getPictureInformation",PictureInformation.class,id);
return aux;
}


public OutdoorRegister setPictureInformation(PictureInformation pictureInformation,Long id){
 OutdoorRegister aux = restTemplate.getForObject("http://5/OutdoorRegister/{id}/PictureInformation/setPictureInformation?'pictureInformation'=pictureInformation&'id'=id',",OutdoorRegister.class,id);
return aux;
}


}