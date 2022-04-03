package com.weflors.Interface;
public interface ContragentsServiceImpl {

   public List<ContragentsEntity> findAllContragents();
   public ContragentsEntity loadContragentByContragentID(Integer contragentId);
}