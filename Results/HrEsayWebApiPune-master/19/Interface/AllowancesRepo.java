public interface AllowancesRepo {

   public List<Allowances> findBydelStatusAndIsActive(int del,int active);
}