package com.softserve.edu.Resources.entity;
 import com.softserve.edu.Resources.Constants;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "abstract_owner")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Person.class, name = "person"), @JsonSubTypes.Type(value = Company.class, name = "company") })
public class Owner {

@Id
@GeneratedValue(generator = Constants.ID_GENERATOR)
@Column(name = "owner_id")
 private  long id;

@Valid
@NotNull
@ManyToOne(cascade = CascadeType.ALL)
 private  Address address;

@NotEmpty
@JsonProperty("phone")
 private  String phone;

public Owner() {
}
public String getPhone(){
    return phone;
}


public String ownerType()


public String addressInfo(){
    return address.customToString();
}


public Owner setAddress(Address address){
    this.address = address;
    return this;
}


@Override
public int hashCode(){
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof Owner))
        return false;
    Owner owner = (Owner) o;
    if (id != owner.id)
        return false;
    if (address != null ? !address.equals(owner.address) : owner.address != null)
        return false;
    return phone != null ? phone.equals(owner.phone) : owner.phone == null;
}


public Owner setId(long id){
    this.id = id;
    return this;
}


public long getId(){
    return id;
}


public Address getAddress(){
    return address;
}


public Owner setPhone(String phone){
    this.phone = phone;
    return this;
}


@Override
public String toString(){
    return "Owner{" + "id=" + id + ", phone='" + phone + '\'' + '}';
}


public String customToString(){
    return "";
}


}