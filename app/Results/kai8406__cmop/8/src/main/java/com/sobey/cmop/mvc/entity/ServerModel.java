package com.sobey.cmop.mvc.entity;
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
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
@Table(name = "server_model", catalog = "cmop")
public class ServerModel {

 private  Integer id;

 private  String company;

 private  String companyAlias;

 private  String name;

 private  Integer cpu;

 private  Integer memory;

 private  Integer disk;

 private  Integer pci;

 private  Integer port;

 private  Set<HostServer> hostServers;

// Constructors
/**
 * default constructor
 */
public ServerModel() {
}/**
 * full constructor
 */
public ServerModel(String company, String companyAlias, String name, Integer cpu, Integer memory, Integer disk, Integer pci, Integer port, Set<HostServer> hostServers) {
    this.company = company;
    this.companyAlias = companyAlias;
    this.name = name;
    this.cpu = cpu;
    this.memory = memory;
    this.disk = disk;
    this.pci = pci;
    this.port = port;
    this.hostServers = hostServers;
}
public void setName(String name){
    this.name = name;
}


@Column(name = "name", nullable = false, length = 45)
public String getName(){
    return name;
}


public void setCpu(Integer cpu){
    this.cpu = cpu;
}


@Column(name = "pci")
public Integer getPci(){
    return pci;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return id;
}


@JsonIgnore
@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "serverModel")
public Set<HostServer> getHostServers(){
    return hostServers;
}


@Column(name = "company_alias", nullable = false, length = 45)
public String getCompanyAlias(){
    return companyAlias;
}


public void setMemory(Integer memory){
    this.memory = memory;
}


@Column(name = "disk")
public Integer getDisk(){
    return disk;
}


public void setPort(Integer port){
    this.port = port;
}


@Column(name = "memory")
public Integer getMemory(){
    return memory;
}


@Column(name = "company", nullable = false, length = 45)
public String getCompany(){
    return company;
}


@Column(name = "port")
public Integer getPort(){
    return port;
}


public void setDisk(Integer disk){
    this.disk = disk;
}


public void setPci(Integer pci){
    this.pci = pci;
}


public void setHostServers(Set<HostServer> hostServers){
    this.hostServers = hostServers;
}


public void setId(Integer id){
    this.id = id;
}


@Column(name = "cpu")
public Integer getCpu(){
    return cpu;
}


public void setCompanyAlias(String companyAlias){
    this.companyAlias = companyAlias;
}


public void setCompany(String company){
    this.company = company;
}


}