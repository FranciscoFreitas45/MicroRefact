import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class GrupoCertificadoController {

 private GrupoCertificadoService grupocertificadoservice;


@PutMapping
("/Certificado/{id}/Grupo/setFk_id_grupo")
public void setFk_id_grupo(@PathVariable(name="id") int int,@RequestBody Grupo fk_id_grupo){
grupocertificadoservice.setFk_id_grupo(int,fk_id_grupo);
}


@GetMapping
("/Certificado/{id}/Grupo/getFk_id_grupo")
public Grupo getFk_id_grupo(@PathVariable(name="id") int int){
grupocertificadoservice.getFk_id_grupo(int);
}


}