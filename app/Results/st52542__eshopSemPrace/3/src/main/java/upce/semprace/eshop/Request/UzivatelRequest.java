package upce.semprace.eshop.Request;
import upce.semprace.eshop.DTO.Uzivatel;
public interface UzivatelRequest {

   public void setUzivatel(Uzivatel uzivatel,Long id);
   public Uzivatel getUzivatel(Long id);
}