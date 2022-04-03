package restock.entities;
 import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "permisos")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Permisos {

 private  long serialVersionUID;

 private  Integer id;

 private  Rol rol;

 private  Menu menu;

/**
 * Permisos.
 */
public Permisos() {
}/**
 * Permisos.
 *
 * @param id the id
 */
public Permisos(final Integer id) {
    super();
    this.id = id;
}
@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "rol_id", nullable = false)
public Rol getRol(){
    return rol;
}


@Fetch(FetchMode.JOIN)
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "menu_id", nullable = false)
public Menu getMenu(){
    return menu;
}


public void setId(Integer id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return id;
}


public void setMenu(Menu menu){
    this.menu = menu;
}


public void setRol(Rol rol){
    this.rol = rol;
}


}