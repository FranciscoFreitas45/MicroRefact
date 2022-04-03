package es.gva.dgti.gvgeoportal.domain;
 import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.plural.RooPlural;
@RooJavaBean
@RooPlural("Catalogos")
public class Catalogo {

 private  String id;

 private  String titulo;

 private  String resumen;

 private  String url;

 private  String tipoServicio;

 private  String imagen;


public String toString(){
    return titulo;
}


}