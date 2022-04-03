package com.fzshuai.po;
 import javax.persistence;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "t_tag")
public class Tag {

@Id
@GeneratedValue
 private  Long id;

 private  String name;

@ManyToMany(mappedBy = "tags")
 private  List<Blog> blogs;

public Tag() {
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public void setId(Long id){
    this.id = id;
}


public Long getId(){
    return id;
}


@Override
public String toString(){
    return "Tag{" + "id=" + id + ", name='" + name + '\'' + '}';
}


public void setBlogs(List<Blog> blogs){
    this.blogs = blogs;
}


public List<Blog> getBlogs(){
    return blogs;
}


}