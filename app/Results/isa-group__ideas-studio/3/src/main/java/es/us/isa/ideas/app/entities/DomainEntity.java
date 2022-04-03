package es.us.isa.ideas.app.entities;
 import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
@Entity
@Access(AccessType.PROPERTY)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class DomainEntity {

 private  int id;

 private  int version;

public DomainEntity() {
    super();
}
@Version
public int getVersion(){
    return version;
}


public void setVersion(int version){
    this.version = version;
}


@Override
public int hashCode(){
    return this.getId();
}


@Override
public boolean equals(Object other){
    boolean result;
    if (this == other)
        result = true;
    else if (other == null)
        result = false;
    else if (other instanceof Integer)
        result = (this.getId() == (Integer) other);
    else if (!this.getClass().isInstance(other))
        result = false;
    else
        result = (this.getId() == ((DomainEntity) other).getId());
    return result;
}


public void setId(int id){
    this.id = id;
}


@Id
@GeneratedValue(strategy = GenerationType.TABLE)
public int getId(){
    return id;
}


}