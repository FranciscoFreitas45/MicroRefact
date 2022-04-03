package ar.com.veterinaria.app.entities.Request;
import ar.com.veterinaria.app.entities.DTO.Address;
public interface AddressRequest {

   public void setAddress(Address address,Integer id);
   public Address getAddress(Integer id);
}