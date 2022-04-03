package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Collection;
@Entity
@Data
@Table(name = "STUDENT")
public class StudentEntity implements UserDetails{

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotBlank(message = "student name is required")
 private  String name;

@NotBlank(message = "student username is required")
 private  String username;

@NotBlank(message = "student password is required")
 private  String password;

 private  String collage;

 private  String major;

 private  LocalDateTime creationDate;


@Override
public String getPassword(){
    return this.password;
}


@Override
public boolean isAccountNonExpired(){
    return true;
}


@Override
public boolean isCredentialsNonExpired(){
    return true;
}


@Override
public boolean isEnabled(){
    return true;
}


@PrePersist
public void prePersist(){
    this.creationDate = LocalDateTime.now();
}


@Override
public boolean isAccountNonLocked(){
    return true;
}


@Override
public Collection<? extends GrantedAuthority> getAuthorities(){
    return null;
}


}