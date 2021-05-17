import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class CertificadoCursoController {

 private CertificadoCursoService certificadocursoservice;


@PutMapping
("/Curso/{id}/Certificado/setCertificados")
public void setCertificados(@PathVariable(name="id") int pk_id_curso,@RequestBody List<Certificado> certificados){
certificadocursoservice.setCertificados(pk_id_curso,certificados);
}


@GetMapping
("/Curso/{id}/Certificado/getCertificados")
public List<Certificado> getCertificados(@PathVariable(name="id") int pk_id_curso){
certificadocursoservice.getCertificados(pk_id_curso);
}


}