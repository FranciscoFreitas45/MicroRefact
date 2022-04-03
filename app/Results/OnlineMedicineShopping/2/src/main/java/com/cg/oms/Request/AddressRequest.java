package com.cg.oms.Request;
import com.cg.oms.DTO.Address;
public interface AddressRequest {

   public Address getUserAddress(Integer addressId);
   public void setUserAddress(Address userAddress,Integer addressId);
}