package co.edu.uniquindio.gri.controller;
 import java.math.BigInteger;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.uniquindio.gri.dao.FacultadDAO;
import co.edu.uniquindio.gri.model.Facultad;
@RestController
@RequestMapping("/rest/service")
public class FacultadController {

@Autowired
 private FacultadDAO facultadDAO;


@GetMapping("/stats")
public List<BigInteger> getStats(){
    return facultadDAO.getStats();
}


@GetMapping("/facultades/{id}")
public ResponseEntity<Facultad> getFacultadById(Long idFacultad){
    Facultad facultad = facultadDAO.getFacultadById(idFacultad);
    if (facultad == null) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(facultad);
}


@GetMapping("/facultades")
public List<Facultad> getAllFacultades(){
    return facultadDAO.getAllFacultades();
}


}