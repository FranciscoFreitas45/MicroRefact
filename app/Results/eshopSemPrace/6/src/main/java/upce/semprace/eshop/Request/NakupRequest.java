package upce.semprace.eshop.Request;
import upce.semprace.eshop.DTO.Nakup;
import java.util.*;

public interface NakupRequest {

   public Set<Nakup> getNakup(Long id);
   public void setNakup(Set<Nakup> nakup,Long id);
}