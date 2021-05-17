import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name = "Estado")
public class Estado {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "pk_id_estado")
 private int pk_id_estado;

@Column(name = "nombre", nullable = false, length = 50, unique = true)
 private String nombre;

@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
 private  List<Profesor> profesores;


public int getPk_id_estado(){
    return pk_id_estado;
}


public String getNombre(){
    return nombre;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setPk_id_estado(int pk_id_estado){
    this.pk_id_estado = pk_id_estado;
}


}