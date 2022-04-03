package com.app.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
public class Teacher {

 private  Integer id;

 private  Institute institute;

 private  Login login;

 private  Permissions permissions;

 private  String fname;

 private  String lname;

 private  String email;

 private  String contactno;

public Teacher() {
}public Teacher(String fname, String lname, String email, String contactno) {
    super();
    this.fname = fname;
    this.lname = lname;
    this.email = email;
    this.contactno = contactno;
}public Teacher(Institute institute, String fname, String lname, String email, String contactno) {
    this.institute = institute;
    this.fname = fname;
    this.lname = lname;
    this.email = email;
    this.contactno = contactno;
}public Teacher(Institute institute, Login login, Permissions permissions, String fname, String lname, String email, String contactno) {
    this.institute = institute;
    this.login = login;
    this.permissions = permissions;
    this.fname = fname;
    this.lname = lname;
    this.email = email;
    this.contactno = contactno;
}
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "instid", nullable = false)
public Institute getInstitute(){
    return this.institute;
}


@Column(name = "lname", nullable = false, length = 50)
public String getLname(){
    return this.lname;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "permissions")
public Permissions getPermissions(){
    return this.permissions;
}


@Column(name = "contactno", nullable = false, length = 11)
public String getContactno(){
    return this.contactno;
}


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "loginid")
public Login getLogin(){
    return this.login;
}


@Column(name = "fname", nullable = false, length = 50)
public String getFname(){
    return this.fname;
}


@Column(name = "email", nullable = false, length = 50)
public String getEmail(){
    return this.email;
}


}