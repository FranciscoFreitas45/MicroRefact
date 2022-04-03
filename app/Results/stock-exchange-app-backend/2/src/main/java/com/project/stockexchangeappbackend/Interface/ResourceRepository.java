package com.project.stockexchangeappbackend.Interface;
import org.springframework.data.jpa.domain.*;
import org.springframework.data.domain.*;
import com.project.stockexchangeappbackend.DTO.Resource;
import com.project.stockexchangeappbackend.DTO.User;
import com.project.stockexchangeappbackend.DTO.Stock;
import java.util.*;
public interface ResourceRepository {

   public Page<Resource> findAll(Specification<Resource> var1,Pageable var2);
   public Optional<Resource> findByUserAndStock(User user,Stock stock);
   public Object delete(Object Object);
   public  <S extends Resource> S save(S s);
}