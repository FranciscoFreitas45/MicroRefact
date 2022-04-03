package es.gva.dgti.gvgeoportal.domain;
 import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.plural.RooPlural;
import org.springframework.roo.addon.tostring.RooToString;
@RooJavaBean
@RooToString
@RooPlural("SistemaCoordenadas")
@GvNIXJpaAudit
@RooJpaActiveRecord(sequenceName = "sistema_coordenadas_id_seq", identifierField = "id", finders = { "findSistemaCoordenadasByCodigoEquals" })
public class SistemaCoordenadas {

@NotNull
 private  String codigo;

@NotNull
 private  String nombre;

 private  String descripcion;

@ManyToMany(cascade = CascadeType.ALL, mappedBy = "coordenadas")
 private  Set<ServicioWeb> serviciosWeb;


public String toString(){
    return this.nombre;
}


}