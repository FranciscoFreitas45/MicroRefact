package com.kingen.bean;
 import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class ClientContactRelId {

 private  String clientId;

 private  String clientContactId;

// Constructors
/**
 * default constructor
 */
public ClientContactRelId() {
}/**
 * full constructor
 */
public ClientContactRelId(String clientId, String clientContactId) {
    this.clientId = clientId;
    this.clientContactId = clientContactId;
}
@Column(name = "client_id", nullable = false, length = 32)
public String getClientId(){
    return this.clientId;
}


public int hashCode(){
    int result = 17;
    result = 37 * result + (getClientId() == null ? 0 : this.getClientId().hashCode());
    result = 37 * result + (getClientContactId() == null ? 0 : this.getClientContactId().hashCode());
    return result;
}


@Column(name = "client_contact_id", nullable = false, length = 32)
public String getClientContactId(){
    return this.clientContactId;
}


public boolean equals(Object other){
    if ((this == other))
        return true;
    if ((other == null))
        return false;
    if (!(other instanceof ClientContactRelId))
        return false;
    ClientContactRelId castOther = (ClientContactRelId) other;
    return ((this.getClientId() == castOther.getClientId()) || (this.getClientId() != null && castOther.getClientId() != null && this.getClientId().equals(castOther.getClientId()))) && ((this.getClientContactId() == castOther.getClientContactId()) || (this.getClientContactId() != null && castOther.getClientContactId() != null && this.getClientContactId().equals(castOther.getClientContactId())));
}


public void setClientContactId(String clientContactId){
    this.clientContactId = clientContactId;
}


public void setClientId(String clientId){
    this.clientId = clientId;
}


}