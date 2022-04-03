package com.bau.graduateprojects.qrstudentsattendance.entities;
 import lombok.Data;
import javax.persistence;
import java.time.LocalDate;
@Entity
@Data
@Table(name = "LECTURE")
public class LectureEntity {

@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
 private  Long id;

 private  String date;


@PrePersist
public void prePersist(){
    this.date = LocalDate.now().toString().replace('-', '/');
}


}