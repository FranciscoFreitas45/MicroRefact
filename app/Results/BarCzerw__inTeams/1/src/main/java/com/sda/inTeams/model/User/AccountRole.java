package com.sda.inTeams.model.User;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountRole {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long id;

 private  String name;


}