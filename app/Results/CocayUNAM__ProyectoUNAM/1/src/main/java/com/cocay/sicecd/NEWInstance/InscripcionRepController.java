package com.cocay.sicecd.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class InscripcionRepController {

 private InscripcionRep inscripcionrep;


@PutMapping
("/saveI")
public void saveI(@RequestParam(name = "fk_id_grupo") int fk_id_grupo,@RequestParam(name = "fk_id_profesor") int fk_id_profesor,@RequestParam(name = "calif") String calif,@RequestParam(name = "aprobado") boolean aprobado){
inscripcionrep.saveI(fk_id_grupo,fk_id_profesor,calif,aprobado);
}


}