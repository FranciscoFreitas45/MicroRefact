package com.yalcin.Interface;
public interface AdressService {

   public List<Adress> getAdress(String userId);
   public void adressSave(AdressFrom adressFrom);
}