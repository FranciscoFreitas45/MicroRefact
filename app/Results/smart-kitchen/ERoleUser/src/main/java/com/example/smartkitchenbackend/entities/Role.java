package com.example.smartkitchenbackend.entities;
 import lombok.Data;
import org.hibernate.annotations.NaturalId;
import javax.persistence;
@Entity
@Data
@Table(name = "roles")
public class Role {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
 private  Long id;

@Enumerated(EnumType.STRING)
@NaturalId
@Column(length = 60)
 private  RoleName name;

public Role(RoleName name) {
    this.name = name;
}
}