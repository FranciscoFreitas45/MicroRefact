package es.gva.dgti.gvgeoportal.domain;
 import javax.validation.constraints.NotNull;
import org.gvnix.addon.jpa.annotations.audit.GvNIXJpaAudit;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.plural.RooPlural;
import org.springframework.roo.addon.tostring.RooToString;
@RooJavaBean
@RooToString
@RooJpaActiveRecord(identifierField = "id")
@RooPlural("GestorCatalogos")
@GvNIXJpaAudit
public class GestorCatalogo {

@NotNull
@NotEmpty
 private  String nombre;

@NotNull
@NotEmpty
 private  String url;


public String toString(){
    return nombre.concat(" - ").concat(url);
}


}