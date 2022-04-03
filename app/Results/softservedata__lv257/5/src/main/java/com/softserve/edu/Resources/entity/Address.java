package com.softserve.edu.Resources.entity;
 import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.softserve.edu.Resources.Constants;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
@Table(name = "address", uniqueConstraints = { @UniqueConstraint(columnNames = { "locality", "street", "building", "apartment" }) })
@Entity
public class Address {

@Id()
@GeneratedValue(generator = Constants.ID_GENERATOR)
@JsonProperty("addressId")
 private  long id;

@NotEmpty
@Length(max = 30, message = "Country name is too big.")
@JsonProperty("country")
 private  String country;

@NotEmpty
@Size(min = 5, max = 30)
@JsonProperty("region")
 private  String region;

@JsonProperty("district")
@NotEmpty
@Size(min = 5, max = 30)
 private  String district;

@JsonProperty("postal_index")
@Column(name = "postal_index")
@NotEmpty
 private  String postalIndex;

@NotEmpty
@JsonProperty("locality")
@Size(min = 5, max = 30)
 private  String locality;

@NotEmpty
@JsonProperty("street")
@Size(min = 5, max = 30)
 private  String street;

@Min(1)
@JsonProperty("building")
 private  int building;

@JsonProperty("block")
 private  String block;

@Min(1)
@JsonProperty("apartment")
 private  int apartment;

@OneToMany(mappedBy = "address", fetch = FetchType.EAGER)
 private  List<Owner> owners;

public Address() {
    owners = new ArrayList<>();
}
public Address setCountry(String country){
    this.country = country;
    return this;
}


public String getCountry(){
    return country;
}


public int getApartment(){
    return apartment;
}


public Address setBlock(String block){
    this.block = block;
    return this;
}


public long getId(){
    return id;
}


@JsonIgnore
public List<Owner> getOwner(){
    return owners;
}


public Address setOwner(List<Owner> owners){
    this.owners = owners;
    return this;
}


public String getPostalIndex(){
    return postalIndex;
}


public String getStreet(){
    return street;
}


@Override
public int hashCode(){
    int result = (int) (id ^ (id >>> 32));
    result = 31 * result + country.hashCode();
    result = 31 * result + region.hashCode();
    result = 31 * result + (district != null ? district.hashCode() : 0);
    result = 31 * result + postalIndex.hashCode();
    result = 31 * result + locality.hashCode();
    result = 31 * result + street.hashCode();
    result = 31 * result + building;
    result = 31 * result + (block != null ? block.hashCode() : 0);
    result = 31 * result + apartment;
    result = 31 * result + (owners != null ? owners.hashCode() : 0);
    return result;
}


public String getDistrict(){
    return district;
}


public Address setId(long id){
    this.id = id;
    return this;
}


public Address setDistrict(String district){
    this.district = district;
    return this;
}


public Address setRegion(String region){
    this.region = region;
    return this;
}


public String getBlock(){
    return block;
}


public Address setStreet(String street){
    this.street = street;
    return this;
}


public Address addOwner(Owner owner){
    this.owners.add(owner);
    return this;
}


public Address setPostalIndex(String postalIndex){
    this.postalIndex = postalIndex;
    return this;
}


public String getRegion(){
    return region;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (!(o instanceof Address))
        return false;
    Address address = (Address) o;
    if (id != address.id)
        return false;
    if (building != address.building)
        return false;
    if (apartment != address.apartment)
        return false;
    if (!country.equals(address.country))
        return false;
    if (!region.equals(address.region))
        return false;
    if (district != null ? !district.equals(address.district) : address.district != null)
        return false;
    if (!postalIndex.equals(address.postalIndex))
        return false;
    if (!locality.equals(address.locality))
        return false;
    if (!street.equals(address.street))
        return false;
    if (block != null ? !block.equals(address.block) : address.block != null)
        return false;
    return owners != null ? owners.equals(address.owners) : address.owners == null;
}


public Address setBuilding(int building){
    this.building = building;
    return this;
}


@Override
public String toString(){
    return "Address{" + "id=" + id + ", country='" + country + '\'' + ", region='" + region + '\'' + ", district='" + district + '\'' + ", postalIndex='" + postalIndex + '\'' + ", locality='" + locality + '\'' + ", street='" + street + '\'' + ", building=" + building + ", block='" + block + '\'' + ", apartment=" + apartment + '}';
}


public Address setLocality(String locality){
    this.locality = locality;
    return this;
}


public String customToString(){
    return country + ", " + region + ", " + district + ", " + postalIndex + ", " + locality + ", " + street + ", " + building + (block.isEmpty() ? ", " : ", " + block + ", ") + apartment + ".";
}


public Address setApartment(int apartment){
    this.apartment = apartment;
    return this;
}


public int getBuilding(){
    return building;
}


public String getLocality(){
    return locality;
}


}