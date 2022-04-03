package com.cocay.sicecd.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.cocay.sicecd.Interface.ProfesorRep;
public class ProfesorRepImpl implements ProfesorRep{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public Profesor findByCurp(String curp){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByCurp"))
    .queryParam("curp",curp)
;  Profesor aux = restTemplate.getForObject(builder.toUriString(), Profesor.class);

 return aux;
}


public Profesor findByRFC(String rfc){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByRFC"))
    .queryParam("rfc",rfc)
;  Profesor aux = restTemplate.getForObject(builder.toUriString(), Profesor.class);

 return aux;
}


public void updateProfesorByRFC(String nombre,String apellido_paterno,String apellido_materno,String correo,String clave_plantel,String ciudad_localidad,String rfc){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateProfesorByRFC"))
    .queryParam("nombre",nombre)
    .queryParam("apellido_paterno",apellido_paterno)
    .queryParam("apellido_materno",apellido_materno)
    .queryParam("correo",correo)
    .queryParam("clave_plantel",clave_plantel)
    .queryParam("ciudad_localidad",ciudad_localidad)
    .queryParam("rfc",rfc)
;
  restTemplate.put(builder.toUriString(), null);
}


public void saveT(String firstname,String apellido_paterno,String apellido_materno,String curp,String rfc,String email,String institution,String city){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveT"))
    .queryParam("firstname",firstname)
    .queryParam("apellido_paterno",apellido_paterno)
    .queryParam("apellido_materno",apellido_materno)
    .queryParam("curp",curp)
    .queryParam("rfc",rfc)
    .queryParam("email",email)
    .queryParam("institution",institution)
    .queryParam("city",city)
;
  restTemplate.put(builder.toUriString(), null);
}


}