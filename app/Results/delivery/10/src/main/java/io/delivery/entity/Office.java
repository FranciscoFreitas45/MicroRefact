package io.delivery.entity;
 import javax.persistence;
@Entity
@Table(name = "offices")
public class Office {

@Id
@Column(name = "office_id")
@GeneratedValue(strategy = GenerationType.AUTO)
 private  long id;

 private  String address;

 private  String coords;

 private  String description;


public String getCoords(){
    return coords;
}


public void setAddress(String address){
    this.address = address;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public String getAddress(){
    return address;
}


public void setDescription(String description){
    this.description = description;
}


public void setCoords(String coords){
    this.coords = coords;
}


public String getDescription(){
    return description;
}


}