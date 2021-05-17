import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CursoCertificadoController {

 private CursoCertificadoService cursocertificadoservice;


@GetMapping
("/Certificado/{id}/Curso/getFk_id_curso")
public Curso getFk_id_curso(@PathVariable(name="id") int int){
cursocertificadoservice.getFk_id_curso(int);
}


@PutMapping
("/Certificado/{id}/Curso/setFk_id_curso")
public void setFk_id_curso(@PathVariable(name="id") int int,@RequestBody Curso fk_id_curso){
cursocertificadoservice.setFk_id_curso(int,fk_id_curso);
}


}