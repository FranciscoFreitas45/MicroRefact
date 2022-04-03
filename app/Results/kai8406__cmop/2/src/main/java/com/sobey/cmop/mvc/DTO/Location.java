package com.sobey.cmop.mvc.DTO;
 import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
public class Location {

 private  Integer id;

 private  String name;

 private  String alias;

 private  String city;

 private  String address;

 private  String postcode;

 private  String telephone;

 private  Set<Vlan> vlans;

 private  Date createTime;

/**
 * default constructor
 */
public Location() {
}/**
 * minimal constructor
 */
public Location(Integer id, String name, String alias, Date createTime) {
    super();
    this.id = id;
    this.name = name;
    this.alias = alias;
    this.createTime = createTime;
}/**
 * full constructor
 */
public Location(Integer id, String name, String alias, String city, String address, String postcode, String telephone, Set<Vlan> vlans, Date createTime) {
    super();
    this.id = id;
    this.name = name;
    this.alias = alias;
    this.city = city;
    this.address = address;
    this.postcode = postcode;
    this.telephone = telephone;
    this.vlans = vlans;
    this.createTime = createTime;
}
@Column(name = "create_time", nullable = false, length = 19)
public Date getCreateTime(){
    return this.createTime;
}


@Column(name = "name", nullable = false, length = 45)
public String getName(){
    return name;
}


@Column(name = "postcode", length = 45)
public String getPostcode(){
    return postcode;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return id;
}


@Column(name = "telephone", length = 45)
public String getTelephone(){
    return telephone;
}


@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "location")
public Set<Vlan> getVlans(){
    return this.vlans;
}


@Column(name = "address", length = 45)
public String getAddress(){
    return address;
}


@Column(name = "alias", nullable = false, length = 45)
public String getAlias(){
    return alias;
}


@Column(name = "city", length = 45)
public String getCity(){
    return city;
}


}