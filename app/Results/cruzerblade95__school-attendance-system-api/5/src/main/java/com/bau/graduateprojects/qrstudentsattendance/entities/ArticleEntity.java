package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Entity
@Data
@Table(name = "ARTICLE")
public class ArticleEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

@NotNull
 private  String title;

@NotNull
 private  String content;

 private  String imageUrl;

 private  LocalDateTime creationDate;


@PrePersist
public void preUpdate(){
    this.creationDate = LocalDateTime.now();
}


}