package restock.Interface;
import restock.DTO.Botiga;
import restock.entities.Usuari;
public interface BotigaBusiness {

   public Botiga getBotigaOfResponsable(Usuari usuari);
}