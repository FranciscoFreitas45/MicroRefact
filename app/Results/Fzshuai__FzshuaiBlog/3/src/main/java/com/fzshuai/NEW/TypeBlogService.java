package com.fzshuai.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.dao.TypeRepository;
import com.fzshuai.po.Type;
@Service
public class TypeBlogService {

@Autowired
 private TypeRepository typerepository;


public void setType(Long id,Type type){
typerepository.setType(id,type);
}


public Type getType(Long id){
return typerepository.getType(id);
}


}