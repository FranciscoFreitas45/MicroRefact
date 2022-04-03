package upce.semprace.eshop.Request;
import upce.semprace.eshop.DTO.Platba;
public interface PlatbaRequest {

   public Platba getPlatba(Long id);
   public void setPlatba(Platba platba,Long id);
}