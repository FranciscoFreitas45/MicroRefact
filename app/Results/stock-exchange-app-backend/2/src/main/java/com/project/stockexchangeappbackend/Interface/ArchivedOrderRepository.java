package com.project.stockexchangeappbackend.Interface;
import java.util.*;
import com.project.stockexchangeappbackend.DTO.ArchivedOrder;
public interface ArchivedOrderRepository {

   public  <S extends ArchivedOrder> List<S> saveAll(Iterable<S> var1);
}