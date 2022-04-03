package co.edu.uniquindio.gri.model;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class CompositeKey implements Serializable{

 private  long serialVersionUID;

@Column(name = "grupos_id")
 private  long grupos;

@Column(name = "investigadores_id")
 private  long investigadores;

public CompositeKey(long grupos_id, long investigadores_id) {
    this.grupos = grupos_id;
    this.investigadores = investigadores_id;
}public CompositeKey() {
}
public long getInvestigador(){
    return investigadores;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (grupos ^ (grupos >>> 32));
    result = prime * result + (int) (investigadores ^ (investigadores >>> 32));
    return result;
}


@Override
public boolean equals(Object obj){
    if (this == obj)
        return true;
    if (obj == null)
        return false;
    if (getClass() != obj.getClass())
        return false;
    CompositeKey other = (CompositeKey) obj;
    if (grupos != other.grupos)
        return false;
    if (investigadores != other.investigadores)
        return false;
    return true;
}


public void setGrupo(long grupos){
    this.grupos = grupos;
}


public void setInvestigador(long investigadores){
    this.investigadores = investigadores;
}


public long getGrupo(){
    return grupos;
}


}