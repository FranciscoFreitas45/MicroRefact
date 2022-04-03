package app.qienuren.Request;
import app.qienuren.DTO.KlantContactPersoon;
public interface KlantContactPersoonRequest {

   public void setLeidingGevende(KlantContactPersoon leidingGevende,long id0KCA);
   public KlantContactPersoon getLeidingGevende(long id0KCA);
   public void koppelKlantContactPersoon(KlantContactPersoon kcp,long id0KCA);
}