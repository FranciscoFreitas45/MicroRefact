package upce.semprace.eshop.Request;
import upce.semprace.eshop.DTO.Produkt;
public interface ProduktRequest {

   public void setProdukt(Produkt produkt,Long id);
   public Produkt getProdukt(Long id);
}