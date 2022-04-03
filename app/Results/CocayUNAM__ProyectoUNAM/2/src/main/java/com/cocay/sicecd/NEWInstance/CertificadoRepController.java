package com.cocay.sicecd.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CertificadoRepController {

 private CertificadoRep certificadorep;


@GetMapping
("/findCertificado")
public Certificado findCertificado(@RequestParam(name = "id_profesor") Integer id_profesor,@RequestParam(name = "id_curso") Integer id_curso,@RequestParam(name = "id_grupo") Integer id_grupo){
  return certificadorep.findCertificado(id_profesor,id_curso,id_grupo);
}


}