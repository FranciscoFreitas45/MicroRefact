package com.cocay.sicecd.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.DTO.Certificado;
import com.cocay.sicecd.Request.CertificadoRequest;
public class CertificadoRequestImpl implements CertificadoRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setCertificados(List<Certificado> certificados,int pk_id_profesor){
 restTemplate.put("http://2/Profesor/{id}/Certificado/setCertificados",certificados,pk_id_profesor);
 return ;
}


public List<Certificado> getCertificados(int pk_id_profesor){
 List<Certificado> aux = restTemplate.getForObject("http://2/Profesor/{id}/Certificado/getCertificados",List<Certificado>.class,pk_id_profesor);
return aux;
}


}