package com.cocay.sicecd.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CursoRepController {

 private CursoRep cursorep;


@GetMapping
("/loadAllCursos")
public List<Curso> loadAllCursos(){
  return cursorep.loadAllCursos();
}


@GetMapping
("/findByParams")
public List<Curso> findByParams(@RequestParam(name = "nombre") String nombre,@RequestParam(name = "clave") String clave,@RequestParam(name = "tipo") Integer tipo){
  return cursorep.findByParams(nombre,clave,tipo);
}


@GetMapping
("/findByNombre")
public Curso findByNombre(@RequestParam(name = "nombre") String nombre){
  return cursorep.findByNombre(nombre);
}


@GetMapping
("/findAll")
public List<Curso> findAll(){
  return cursorep.findAll();
}


@GetMapping
("/findForClave")
public Curso findForClave(@RequestParam(name = "clave") String clave){
  return cursorep.findForClave(clave);
}


@GetMapping
("/findByUniqueClave")
public Curso findByUniqueClave(@RequestParam(name = "clave") String clave){
  return cursorep.findByUniqueClave(clave);
}


@GetMapping
("/findByUniqueClaveCurso")
public Curso findByUniqueClaveCurso(@RequestParam(name = "clave") String clave){
  return cursorep.findByUniqueClaveCurso(clave);
}


@PutMapping
("/saveC")
public void saveC(@RequestParam(name = "clave") String clave,@RequestParam(name = "nombre") String nombre){
cursorep.saveC(clave,nombre);
}


}