package com.example.smartkitchenbackend.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = { "username" }), @UniqueConstraint(columnNames = { "email" }) })
@Data
public class User {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@NotBlank
@Size(max = 40)
 private  String name;

@NotBlank
@Size(max = 15)
 private  String username;

@NotBlank
@Size(max = 40)
@Email
 private  String email;

@NotBlank
@Size(max = 100)
 private  String password;

@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
 private  Set<Role> roles;

@ManyToMany
@JoinTable(name = "users_kitchens", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "kitchen_id"))
 private  List<Kitchen> kitchens;

public User() {
}public User(String name, String username, String email, String password) {
    this.name = name;
    this.username = username;
    this.email = email;
    this.password = password;
}
public void addKitchen(Kitchen kitchen){
    kitchens.add(kitchen);
}


}