package io.delivery.entity;
 import javax.persistence;
@Entity
@Table(name = "news")
public class News {

@Id
@Column(name = "news_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  long id;

@Column(name = "news_name")
 private  String name;


public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


}