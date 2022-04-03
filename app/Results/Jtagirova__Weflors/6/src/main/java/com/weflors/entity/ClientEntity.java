package com.weflors.entity;
 import com.fasterxml.jackson.annotation.JsonManagedReference;
import javax.persistence;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;
@Entity
@Table(name = "client", schema = "flowershop", catalog = "postgres")
public class ClientEntity {

 private  int clientId;

 private  String clientName;

 private  String clientSurname;

 private  Date dateOfBirth;

 private  String eMail;

 private  String phone;

 private  Integer discount;

 private  String address;

 private  String zipCode;

 private  BigDecimal totalAmountPurchased;

 private  Collection<SaleEntity> salesByClientId;

public ClientEntity() {
}
public void setClientName(String clientName){
    this.clientName = clientName;
}


@Basic
@Column(name = "client_surname", nullable = false, length = 50)
public String getClientSurname(){
    return clientSurname;
}


@Basic
@Column(name = "phone", nullable = false, length = 15)
public String getPhone(){
    return phone;
}


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
}


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "client_id", nullable = false)
public int getClientId(){
    return clientId;
}


@Basic
@Column(name = "date_of_birth", nullable = false)
public Date getDateOfBirth(){
    return dateOfBirth;
}


public void setAddress(String address){
    this.address = address;
}


@Basic
@Column(name = "zip_code", nullable = true, length = 15)
public String getZipCode(){
    return zipCode;
}


public void setAmountPurchased(BigDecimal amountPurchased){
    this.totalAmountPurchased = amountPurchased;
}


public void setPhone(String phone){
    this.phone = phone;
}


@OneToMany(mappedBy = "clientByClientId", orphanRemoval = true)
@JsonManagedReference(value = "client-sale")
public Collection<SaleEntity> getSalesByClientId(){
    return salesByClientId;
}


@Basic
@Column(name = "e_mail", nullable = false, length = 50)
public String geteMail(){
    return eMail;
}


public void setDateOfBirth(Date dateOfBirth){
    this.dateOfBirth = dateOfBirth;
}


public void setSalesByClientId(Collection<SaleEntity> salesByClientId){
    this.salesByClientId = salesByClientId;
}


@Basic
@Column(name = "discount", nullable = true)
public Integer getDiscount(){
    return discount;
}


@Override
public int hashCode(){
    int result = clientId;
    result = 31 * result + (clientName != null ? clientName.hashCode() : 0);
    result = 31 * result + (clientSurname != null ? clientSurname.hashCode() : 0);
    result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
    result = 31 * result + (eMail != null ? eMail.hashCode() : 0);
    result = 31 * result + (phone != null ? phone.hashCode() : 0);
    result = 31 * result + (discount != null ? discount.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
    result = 31 * result + (totalAmountPurchased != null ? totalAmountPurchased.hashCode() : 0);
    return result;
}


@Override
public boolean equals(Object o){
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;
    ClientEntity that = (ClientEntity) o;
    if (clientId != that.clientId)
        return false;
    if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null)
        return false;
    if (clientSurname != null ? !clientSurname.equals(that.clientSurname) : that.clientSurname != null)
        return false;
    if (dateOfBirth != null ? !dateOfBirth.equals(that.dateOfBirth) : that.dateOfBirth != null)
        return false;
    if (eMail != null ? !eMail.equals(that.eMail) : that.eMail != null)
        return false;
    if (phone != null ? !phone.equals(that.phone) : that.phone != null)
        return false;
    if (discount != null ? !discount.equals(that.discount) : that.discount != null)
        return false;
    if (address != null ? !address.equals(that.address) : that.address != null)
        return false;
    if (zipCode != null ? !zipCode.equals(that.zipCode) : that.zipCode != null)
        return false;
    if (totalAmountPurchased != null ? !totalAmountPurchased.equals(that.totalAmountPurchased) : that.totalAmountPurchased != null)
        return false;
    return true;
}


public void seteMail(String eMail){
    this.eMail = eMail;
}


public void setDiscount(Integer discount){
    this.discount = discount;
}


@Basic
@Column(name = "address", nullable = true, length = 50)
public String getAddress(){
    return address;
}


public void setClientSurname(String clientSurname){
    this.clientSurname = clientSurname;
}


public void setClientId(int clientId){
    this.clientId = clientId;
}


@Basic
@Column(name = "client_name", nullable = false, length = 50)
public String getClientName(){
    return clientName;
}


@Basic
@Column(name = "total_amount_purchased", nullable = true, precision = 2)
public BigDecimal getAmountPurchased(){
    return totalAmountPurchased;
}


}