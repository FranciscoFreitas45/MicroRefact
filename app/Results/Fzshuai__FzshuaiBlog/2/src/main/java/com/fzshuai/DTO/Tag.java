package com.fzshuai.DTO;
 import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class Tag {

 private  Long id;

 private  String name;

 private  List<Blog> blogs;

public Tag() {
}
public String getName(){
    return name;
}


public Long getId(){
    return id;
}


public void setBlogs(List<Blog> blogs){
    this.blogs = blogs;
}


public List<Blog> getBlogs(){
    return blogs;
}


}