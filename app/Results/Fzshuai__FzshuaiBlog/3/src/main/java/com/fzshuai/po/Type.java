package com.fzshuai.po;
 import org.hibernate.validator.constraints.NotBlank;
import javax.persistence;
import java.util.ArrayList;
import java.util.List;
import com.fzshuai.Request.BlogRequest;
import com.fzshuai.Request.Impl.BlogRequestImpl;
import com.fzshuai.DTO.Blog;
@Entity
@Table(name = "t_type")
public class Type {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
 private  Long id;

@NotBlank(message = "分类名称不能为空")
 private  String name;

@Transient
 private  List<Blog> blogs;

@Transient
 private BlogRequest blogrequest = new BlogRequestImpl();;

public Type() {
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
    return "Type{" + "id=" + id + ", name='" + name + '\'' + '}';
}


public void setBlogs(List<Blog> blogs){
 blogrequest.setBlogs(blogs,this.id);
}



public List<Blog> getBlogs(){
  this.blogs = blogrequest.getBlogs(this.id);
return this.blogs;
}


}