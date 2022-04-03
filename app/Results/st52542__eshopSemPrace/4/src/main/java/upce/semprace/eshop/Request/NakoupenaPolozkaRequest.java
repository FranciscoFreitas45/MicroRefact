package upce.semprace.eshop.Request;
import upce.semprace.eshop.DTO.NakoupenaPolozka;
public interface NakoupenaPolozkaRequest {

   public void setNakoupenaPolozka(NakoupenaPolozka nakoupenaPolozka,Long id);
   public NakoupenaPolozka getNakoupenaPolozka(Long id);
}