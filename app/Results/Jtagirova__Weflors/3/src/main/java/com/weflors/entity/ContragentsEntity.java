package com.weflors.entity;
 import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence;
import java.util.Collection;
@Entity
@Table(name = "contragents", schema = "flowershop", catalog = "postgres")
public class ContragentsEntity {

 private  String address;

 private  int contragentId;

 private  String contragentName;

 private  String phone1;

 private  String phone2;

 private  String unk;

 private  String inn;

 private  String zipCode;

 private  Collection<ProcurementEntity> procurementsByContragentId;

 private  Collection<ProductEntity> productsByContragentId;


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
}


@OneToMany(mappedBy = "contragentsByContragentId", orphanRemoval = true)
@JsonManagedReference(value = "contragents-procurement")
public Collection<ProcurementEntity> getProcurementsByContragentId(){
    return procurementsByContragentId;
}


public void setAddress(String address){
    this.address = address;
}


@Basic
@Column(name = "zip_code", nullable = true, length = 15)
public String getZipCode(){
    return zipCode;
}


public void setInn(String inn){
    this.inn = inn;
}


public void setProcurementsByContragentId(Collection<ProcurementEntity> procurementsByContragentId){
    this.procurementsByContragentId = procurementsByContragentId;
}


public void setUnk(String unk){
    this.unk = unk;
}


public void setContragentId(int contragentId){
    this.contragentId = contragentId;
}


public void setProductsByContragentId(Collection<ProductEntity> productsByContragentId){
    this.productsByContragentId = productsByContragentId;
}


public void setContragentName(String contragentName){
    this.contragentName = contragentName;
}


@Basic
@Column(name = "unk", nullable = false, length = 15)
public String getUnk(){
    return unk;
}


@Basic
@Column(name = "contragent_name", nullable = false, length = 50)
public String getContragentName(){
    return contragentName;
}


@Override
public int hashCode(){
    int result = address != null ? address.hashCode() : 0;
    result = 31 * result + contragentId;
    result = 31 * result + (contragentName != null ? contragentName.hashCode() : 0);
    result = 31 * result + (phone1 != null ? phone1.hashCode() : 0);
    result = 31 * result + (phone2 != null ? phone2.hashCode() : 0);
    result = 31 * result + (unk != null ? unk.hashCode() : 0);
    result = 31 * result + (inn != null ? inn.hashCode() : 0);
    result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ContragentsEntity that = (ContragentsEntity) o;
    if (contragentId != that.contragentId)
        return false;
    if (address != null ? !address.equals(that.address) : that.address != null)
        return false;
    if (contragentName != null ? !contragentName.equals(that.contragentName) : that.contragentName != null)
        return false;
    if (phone1 != null ? !phone1.equals(that.phone1) : that.phone1 != null)
        return false;
    if (phone2 != null ? !phone2.equals(that.phone2) : that.phone2 != null)
        return false;
    if (unk != null ? !unk.equals(that.unk) : that.unk != null)
        return false;
    if (inn != null ? !inn.equals(that.inn) : that.inn != null)
        return false;
    if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null)
        return false;
    return true;
}


@Basic
@Column(name = "phone_2", nullable = true, length = 50)
public String getPhone2(){
    return phone2;
}


@Basic
@Column(name = "address", nullable = false, length = 100)
public String getAddress(){
    return address;
}


@Basic
@Column(name = "phone_1", nullable = false, length = 50)
public String getPhone1(){
    return phone1;
}


@OneToMany(mappedBy = "contragentsByContragentId", orphanRemoval = true)
@JsonManagedReference(value = "contragents-product")
public Collection<ProductEntity> getProductsByContragentId(){
    return productsByContragentId;
}


@Basic
@Column(name = "inn", nullable = false, length = 15)
public String getInn(){
    return inn;
}


public void setPhone1(String phone1){
    this.phone1 = phone1;
}


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "contragent_id", nullable = false)
public int getContragentId(){
    return contragentId;
}


public void setPhone2(String phone2){
    this.phone2 = phone2;
}


}