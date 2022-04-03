package org.vaadin.paul.spring.entities;
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
@Entity
public class User implements UserDetails{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  int id;

@JoinColumn(name = "rol")
@OneToOne
 private  Rol rol;

@Column(name = "nombre", length = 128)
 private  String nombre;

@Column(name = "apellidos", length = 128)
 private  String apellidos;

@Column(name = "dni")
 private  String dni;

@Column(name = "direccion", length = 128)
 private  String direccion;

@Column(name = "telefono", length = 128)
 private  String tlf;

@Column(name = "baja")
 private  boolean baja;

 private  String username;

 private  String password;

public User() {
}public User(String nombre, String apellidos, String username) {
    this.nombre = nombre;
    this.apellidos = apellidos;
    this.username = username;
}
public void setPassword(String password){
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
    this.password = encoder.encode(password);
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


public void setRol(Rol rol){
    this.rol = rol;
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


@Override
public boolean isAccountNonExpired(){
    return true;
}


public void setNombre(String nombre){
    this.nombre = nombre;
}


public void setDni(String dni){
    this.dni = dni;
}


public String getDni(){
    return dni;
}


public String getNombre(){
    return nombre;
}


public void setUsername(String username){
    this.username = username;
}


public void setApellidos(String apellidos){
    this.apellidos = apellidos;
}


public void Alta(){
    this.setBaja(false);
}


@Override
public boolean isAccountNonLocked(){
    return true;
}


public void setTlf(String tlf){
    this.tlf = tlf;
}


public String getNombreyApellidos(){
    return this.nombre + " " + this.apellidos;
}


public String getTlf(){
    return this.tlf;
}


public void setBaja(boolean baja){
    this.baja = baja;
}


@Override
public String getPassword(){
    return password;
}


@Override
public boolean isCredentialsNonExpired(){
    return true;
}


public void setDireccion(String direccion){
    this.direccion = direccion;
}


@Override
public boolean isEnabled(){
    return true;
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