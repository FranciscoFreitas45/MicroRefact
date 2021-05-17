import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "Url_ws_inscripcion")
public class Url_ws_inscripcion {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_url_ws_inscripcion")
 private  int pk_id_url_ws_inscripcion;

@Column(name = "nombre", nullable = true)
 private  String nombre;

@Column(name = "url", nullable = false, length = 200, unique = true)
 private  String url;

@Column(name = "activa", nullable = true)
 private  boolean activa;


public boolean isActiva(){
    return activa;
}


public String getUrl(){
    return url;
}


public String getNombre(){
    return nombre;
}


public int getPk_id_url_ws_inscripcion(){
    return pk_id_url_ws_inscripcion;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setPk_id_url_ws_inscripcion(int pk_id_url_ws_inscripcion){
    this.pk_id_url_ws_inscripcion = pk_id_url_ws_inscripcion;
}


public void setUrl(String url){
    this.url = url;
}


public void setActiva(boolean activa){
    this.activa = activa;
}


}