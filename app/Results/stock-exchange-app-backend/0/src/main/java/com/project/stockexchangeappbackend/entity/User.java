package com.project.stockexchangeappbackend.entity;
 import lombok.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import com.project.stockexchangeappbackend.Request.TagRequest;
import com.project.stockexchangeappbackend.Request.Impl.TagRequestImpl;
import com.project.stockexchangeappbackend.DTO.*;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

@Id
@GeneratedValue(generator = "USER_SEQUENCE")
 private  Long id;

@Column(name = "FIRST_NAME", nullable = false)
 private  String firstName;

@Column(name = "LAST_NAME", nullable = false)
 private  String lastName;

@Column(name = "EMAIL", nullable = false, unique = true, updatable = false)
 private  String email;

@Column(name = "PASSWORD", nullable = false)
 private  String password;

@Column(name = "ROLE", nullable = false)
@Enumerated(EnumType.STRING)
 private  Role role;

@Column(name = "MONEY", nullable = false, precision = 14, scale = 2)
 private  BigDecimal money;

@Column(name = "IS_ACTIVE", nullable = false)
 private  Boolean isActive;

@OneToMany(mappedBy = "user")
 private  List<Order> orders;

@OneToMany(mappedBy = "user")
 private  List<Resource> userStocks;

@Transient
 private  Tag tag;

@Column(name = "VERSION", nullable = false)
@Version
 private  Long version;

@Column(name = "tag_id")
 private Long tag_id;

@Transient
 private TagRequest tagrequest = new TagRequestImpl();;


}