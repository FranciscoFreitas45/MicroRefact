package com.kingen.bean;
 import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "t_client_contact_rel")
public class ClientContactRel {

 private  ClientContactRelId id;

// Constructors
/**
 * default constructor
 */
public ClientContactRel() {
}/**
 * full constructor
 */
public ClientContactRel(ClientContactRelId id) {
    this.id = id;
}
public void setId(ClientContactRelId id){
    this.id = id;
}


@EmbeddedId
@AttributeOverrides({ @AttributeOverride(name = "clientId", column = @Column(name = "client_id", nullable = false, length = 32)), @AttributeOverride(name = "clientContactId", column = @Column(name = "client_contact_id", nullable = false, length = 32)) })
public ClientContactRelId getId(){
    return this.id;
}


}