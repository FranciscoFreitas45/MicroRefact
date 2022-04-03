package com.softserve.edu.Resources.entity;
 import com.softserve.edu.Resources.Constants;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "RESOURCES")
public class Resource {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
@Column(name = "Id")
 private  Long id;

@OneToOne(cascade = { CascadeType.MERGE })
@JoinColumn(name = "ADDRESS_ID")
 private  Address address;

@OneToMany(mappedBy = "resource", cascade = CascadeType.REMOVE, orphanRemoval = true)
 private  List<ResourceOwning> resourceOwnings;

public Resource() {
// TODO Auto-generated constructor stub
}public Resource(Address address) {
    this.address = address;
}
public void setAddress(Address address){
    this.address = address;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((id == null) ? 0 : id.hashCode());
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
    Resource other = (Resource) obj;
    if (address == null) {
        if (other.address != null)
            return false;
    } else if (!address.equals(other.address))
        return false;
    if (id == null) {
        if (other.id != null)
            return false;
    } else if (!id.equals(other.id))
        return false;
    return true;
}


public void addResourceOwning(ResourceOwning resourceOwning){
    resourceOwnings.add(resourceOwning);
    resourceOwning.setResource(this);
}


public void setId(Long id){
    this.id = id;
}


public Address getAddress(){
    return address;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "Resource [id=" + id + ", address=" + address + "]";
}


public void removeResourceOwning(ResourceOwning resourceOwning){
    resourceOwning.setResource(null);
    this.resourceOwnings.remove(resourceOwning);
}


}