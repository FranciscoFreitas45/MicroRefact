package co.edu.uniquindio.gri.dao;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.uniquindio.gri.model.Grupo;
import co.edu.uniquindio.gri.repository.GrupoRepository;
@Service
public class GrupoDAO {

@Autowired
 private GrupoRepository grupoRepository;


public Grupo findOne(Long grupoId){
    return grupoRepository.findOne(grupoId);
}


public List<Grupo> getGruposFacultad(Long facultadId){
    List<Grupo> gruposC = grupoRepository.getGruposFacultadC(facultadId);
    List<Grupo> gruposP = grupoRepository.getGruposFacultadP(facultadId);
    for (Grupo grupo : gruposC) {
        if (!gruposP.contains(grupo)) {
            gruposP.add(grupo);
        }
    }
    return gruposP;
}


public List<Grupo> findAll(){
    return grupoRepository.findAll();
}


public List<Grupo> getGruposPertenecientes(Long id,String type){
    if (type.equals("f")) {
        return getGruposFacultad(id);
    } else if (type.equals("c")) {
        return getGruposCentro(id);
    } else if (type.equals("p")) {
        return getGruposPrograma(id);
    } else {
        return findAll();
    }
}


public List<Grupo> getGruposPrograma(Long programaId){
    return grupoRepository.getGruposPrograma(programaId);
}


public List<Grupo> getGruposCentro(Long centroId){
    return grupoRepository.getGruposCentro(centroId);
}


}