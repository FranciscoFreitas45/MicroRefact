package com.project.stockexchangeappbackend.entity;
 import lombok;
import javax.persistence;
@Entity
@Table(name = "TAGS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tag {

@Id
@GeneratedValue(generator = "TAGS_SEQUENCE")
 private  Long id;

@Column(name = "NAME", nullable = false, updatable = false)
 private  String name;


}