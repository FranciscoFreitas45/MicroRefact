package com.ukefu.webim.web.model;
 import javax.validation.Valid;
public class CustomerGroupForm {

@Valid
 private  EntCustomer entcustomer;

@Valid
 private  Contacts contacts;


public EntCustomer getEntcustomer(){
    return entcustomer;
}


public void setContacts(Contacts contacts){
    this.contacts = contacts;
}


public Contacts getContacts(){
    return contacts;
}


public void setEntcustomer(EntCustomer entcustomer){
    this.entcustomer = entcustomer;
}


}