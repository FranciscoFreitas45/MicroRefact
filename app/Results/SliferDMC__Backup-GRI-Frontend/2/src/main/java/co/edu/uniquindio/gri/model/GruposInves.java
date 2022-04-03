package co.edu.uniquindio.gri.model;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity(name = "GRUPOS_INVES")
@Table(name = "GRUPOS_INVES", schema = "gri")
public class GruposInves implements Serializable{

 private  long serialVersionUID;

@EmbeddedId
 private  CompositeKey id;

@ManyToOne(fetch = FetchType.EAGER)
@MapsId("grupos")
 private  Grupo grupos;

@ManyToOne(fetch = FetchType.EAGER)
@MapsId("investigadores")
 private  Investigador investigadores;

@Column(name = "ESTADO", length = 50)
 private  String estado;

public GruposInves() {
}public GruposInves(Grupo grupos, Investigador investigadores, String estado) {
    this.id = new CompositeKey(grupos.getId(), investigadores.getId());
    this.grupos = grupos;
    this.investigadores = investigadores;
    this.estado = estado;
}
@JsonIgnore
public Investigador getInvestigador(){
    return investigadores;
}


public String getEstado(){
    return estado;
}


public void setEstado(String estado){
    this.estado = estado;
}


public void setId(CompositeKey id){
    this.id = id;
}


public CompositeKey getId(){
    return id;
}


public void setGrupo(Grupo grupos){
    this.grupos = grupos;
}


@JsonIgnore
public void setInvestigador(Investigador investigadores){
    this.investigadores = investigadores;
}


public Grupo getGrupo(){
    return grupos;
}


}