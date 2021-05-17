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
public class Inscripcion {

 private int pk_id_inscripcion;

 private String calif;

 private Boolean aprobado;

 private String tempGrupo;

 private String tempCurso;

 private String tempProfesor;

 private Integer stTabla;

 private Grupo fk_id_grupo;

 private Profesor fk_id_profesor;


public String getTempCurso(){
    return tempCurso;
}


public Profesor getFk_id_profesor(){
    return fk_id_profesor;
}


public int getPk_id_inscripcion(){
    return pk_id_inscripcion;
}


public String getTempProfesor(){
    return tempProfesor;
}


public Integer getStTabla(){
    return stTabla;
}


public String getCalif(){
    return calif;
}


public String getTempGrupo(){
    return tempGrupo;
}


public Grupo getFk_id_grupo(){
    return fk_id_grupo;
}


}