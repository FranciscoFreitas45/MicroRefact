package org.sdrc.childinfo.domain;
 import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "counter_count")
public class CounterCount implements Serializable{

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id")
 private Integer id;

@Column(name = "no_Of_Counter")
 private Integer noOfCounter;


public Integer getNoOfCounter(){
    return noOfCounter;
}


public void setId(Integer id){
    this.id = id;
}


public Integer getId(){
    return id;
}


public void setNoOfCounter(Integer noOfCounter){
    this.noOfCounter = noOfCounter;
}


}