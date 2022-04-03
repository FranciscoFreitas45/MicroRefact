package com.cocay.sicecd.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GrupoRepController {

 private GrupoRep gruporep;


@GetMapping
("/findByIdAsesor")
public List<Grupo> findByIdAsesor(@RequestParam(name = "id_asesor") Integer id_asesor){
  return gruporep.findByIdAsesor(id_asesor);
}


@GetMapping
("/findAll")
public List<Grupo> findAll(){
  return gruporep.findAll();
}


@GetMapping
("/findByClaveGrupoIdCurso")
public Grupo findByClaveGrupoIdCurso(@RequestParam(name = "clave") String clave,@RequestParam(name = "curso") Curso curso){
  return gruporep.findByClaveGrupoIdCurso(clave,curso);
}


@PutMapping
("/saveC")
public void saveC(@RequestParam(name = "clave") String clave,@RequestParam(name = "fk_id_curso") Integer fk_id_curso){
gruporep.saveC(clave,fk_id_curso);
}


}