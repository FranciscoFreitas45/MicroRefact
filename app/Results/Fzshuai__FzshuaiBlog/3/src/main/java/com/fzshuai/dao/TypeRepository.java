package com.fzshuai.dao;
 import com.fzshuai.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface TypeRepository extends JpaRepository<Type, Long>{


public Type findByName(String name)
;

@Query("select t from Type t")
public List<Type> findTop(Pageable pageable)
;

public void setType(Long id,Type type);

public Type getType(Long id);

}