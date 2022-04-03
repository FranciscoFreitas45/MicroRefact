package app.qienuren.Interface;
public interface KlantContactPersoonService {

   public KlantContactPersoon getKCPById(long id);
   public KlantContactPersoon kcpWachtwoordWijzigen(long id,KlantContactPersoon kcp);
}