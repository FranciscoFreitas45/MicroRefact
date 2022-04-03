package com.fzshuai.DTO;
 import org.hibernate.validator.constraints.NotBlank;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
public class Type {

 private  Long id;

 private  String name;

 private  List<Blog> blogs;

public Type() {
}
public String getName(){
    return name;
}


public Long getId(){
    return id;
}


public List<Blog> getBlogs(){
    return blogs;
}


}