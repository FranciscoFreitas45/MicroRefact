package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
@Entity
@Data
@Table(name = "SECRET_KEY")
public class SecretKeyEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotNull
 private  Long courseId;

@NotNull
 private  String sKey;


}