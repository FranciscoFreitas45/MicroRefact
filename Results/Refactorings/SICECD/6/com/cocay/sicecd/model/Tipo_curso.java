import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@Table(name = "Tipo_curso")
public class Tipo_curso {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_tipo_curso")
 private int pk_id_tipo_curso;

@Column(name = "nombre", nullable = false, length = 40)
 private String nombre;

@OneToMany(mappedBy = "fk_id_tipo_curso", targetEntity = Curso.class)
@LazyCollection(LazyCollectionOption.FALSE)
 private  List<Curso> cursos;


public List<Curso> getCursos(){
    return cursos;
}


public void setPk_id_tipo_curso(int pk_id_tipo_curso){
    this.pk_id_tipo_curso = pk_id_tipo_curso;
}


public int getPk_id_tipo_curso(){
    return pk_id_tipo_curso;
}


public String getNombre(){
    return nombre;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setCursos(List<Curso> cursos){
    this.cursos = cursos;
}


}