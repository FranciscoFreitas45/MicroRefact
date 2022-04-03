package es.gva.dgti.gvgeoportal.service.domain.impl;
 import java.util.Iterator;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import org.springframework.dao.EmptyResultDataAccessException;
import es.gva.dgti.gvgeoportal.domain.SistemaCoordenadas;
import es.gva.dgti.gvgeoportal.service.domain.SistemaCoordenadasService;
public class SistemaCoordenadasServiceImpl implements SistemaCoordenadasService{


public String getListCrsName(){
    // obtenemos sistemas de coordenadas
    List<SistemaCoordenadas> listaSistemasCoordenadas = findAllSistemaCoordenadas();
    // creamos string
    StringBuilder nombresCoordenadas = new StringBuilder();
    Iterator<SistemaCoordenadas> iterator = listaSistemasCoordenadas.iterator();
    while (iterator.hasNext()) {
        SistemaCoordenadas sistemaCoordenadas = iterator.next();
        nombresCoordenadas.append(sistemaCoordenadas.getCodigo());
        // comprobamos si no es el ultimo elemento y anyadimos coma
        if (iterator.hasNext()) {
            nombresCoordenadas.append(",");
        }
    }
    return nombresCoordenadas.toString();
}


public SistemaCoordenadas findSistemaCoordenadasByCodigoEquals(String codigo){
    SistemaCoordenadas sistemaCoordenadas = null;
    TypedQuery<SistemaCoordenadas> result = SistemaCoordenadas.findSistemaCoordenadasByCodigoEquals(codigo);
    try {
        sistemaCoordenadas = result.getSingleResult();
    } catch (NoResultException exception) {
        // no hay resultados
        return null;
    } catch (EmptyResultDataAccessException ex) {
        // no hay resultados
        return null;
    }
    return sistemaCoordenadas;
}


}