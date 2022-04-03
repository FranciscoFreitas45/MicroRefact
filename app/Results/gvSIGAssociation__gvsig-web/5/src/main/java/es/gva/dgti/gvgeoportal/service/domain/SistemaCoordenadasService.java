package es.gva.dgti.gvgeoportal.service.domain;
 import org.springframework.roo.addon.layers.service.RooService;
import es.gva.dgti.gvgeoportal.domain.SistemaCoordenadas;
@RooService(domainTypes = { es.gva.dgti.gvgeoportal.domain.SistemaCoordenadas.class })
public interface SistemaCoordenadasService {


public String getListCrsName()
;

public SistemaCoordenadas findSistemaCoordenadasByCodigoEquals(String codigo)
;

}