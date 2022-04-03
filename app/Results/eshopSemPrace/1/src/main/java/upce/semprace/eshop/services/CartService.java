package upce.semprace.eshop.services;
 import upce.semprace.eshop.DTO.Produkt;
import java.util.List;
public interface CartService {


public void order(Long idUzivatel,Long idDoprava,Long idPlatba,List<Produkt> polozky)
;

}