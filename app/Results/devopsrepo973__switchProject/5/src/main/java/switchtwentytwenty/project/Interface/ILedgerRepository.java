package switchtwentytwenty.project.Interface;
public interface ILedgerRepository {

   public Ledger findByID(LedgerID id);
   public void save(Ledger entity);
}