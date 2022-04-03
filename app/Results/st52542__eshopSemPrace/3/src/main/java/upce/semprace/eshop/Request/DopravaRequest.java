package upce.semprace.eshop.Request;
import upce.semprace.eshop.DTO.Doprava;
public interface DopravaRequest {

   public void setDoprava(Doprava doprava,Long id);
   public Doprava getDoprava(Long id);
}