import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
public class Certificado {

 private  int pk_id_certificado;

 private  String ruta;

 private  long tiempo_creado;

 private  Profesor fk_id_profesor;

 private  Curso fk_id_curso;

 private  Grupo fk_id_grupo;


public int getPk_id_certificado(){
    return pk_id_certificado;
}


public long getTiempo_creado(){
    return this.tiempo_creado;
}


public Curso getFk_id_curso(){
    return fk_id_curso;
}


public String getRuta(){
    return ruta;
}


public Profesor getFk_id_profesor(){
    return fk_id_profesor;
}


public Grupo getFk_id_grupo(){
    return fk_id_grupo;
}


}