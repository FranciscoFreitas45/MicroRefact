package com.fzshuai.service;
 import com.fzshuai.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
public interface TypeService {


public Type saveType(Type type)
;

public Type getType(Long id)
;

public Type getTypeByName(String name)
;

public void deleteType(Long id)
;

public List<Type> listType()
;

public List<Type> listTypeTop(Integer size)
;

public Type updateType(Long id,Type type)
;

}