package org.vaadin.paul.spring.DTO;
 import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
public class User implements UserDetails{

 private  int id;

 private  Rol rol;

 private  String nombre;

 private  String apellidos;

 private  String dni;

 private  String direccion;

 private  String tlf;

 private  boolean baja;

 private  String username;

 private  String password;

public User() {
}public User(String nombre, String apellidos, String username) {
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.username = username;
}
public void Baja(){
    this.setBaja(true);
}


public int getId(){
    return this.id;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
    list.add(new SimpleGrantedAuthority("ROLE_" + rol.getNombre()));
    return list;
}


@Override
public String getUsername(){
    return username;
}


public Rol getRol(){
    return rol;
}


public boolean getBaja(){
    return baja;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public String getDni(){
    return dni;
}


public String getNombre(){
    return nombre;
}


public void setApellidos(String apellidos){
    this.apellidos = apellidos;
}


@Override
public boolean isAccountNonLocked(){
    return true;
}


public String getNombreyApellidos(){
    return this.nombre + " " + this.apellidos;
}


public String getTlf(){
    return this.tlf;
}


@Override
public String getPassword(){
    return password;
}


public void setDireccion(String direccion){
    this.direccion = direccion;
}


public String toString(){
    return super.toString() + this.nombre + this.apellidos;
}


public String getApellidos(){
    return apellidos;
}


public String getDireccion(){
    return this.direccion;
}


}