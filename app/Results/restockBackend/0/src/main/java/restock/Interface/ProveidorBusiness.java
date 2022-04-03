package restock.Interface;
import restock.DTO.Proveidor;
public interface ProveidorBusiness {

   public List<Proveidor> getProveidorsPerOrganitzacio(Integer orgId);
}