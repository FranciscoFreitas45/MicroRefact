package lk.sliit.csse.procurementsystem.Interface;
public interface SupplierRepository {

   public List<Supplier> findByBlackListedFalse();
   public int setBlackListedFor(Boolean isBlacklisted,String name);
}