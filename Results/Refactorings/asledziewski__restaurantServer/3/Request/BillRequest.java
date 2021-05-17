public interface BillRequest {

   public List<Bill> getBills(Long rTableId);
   public void setBills(List<Bill> bills,Long rTableId);
   public void addBill(Bill bill,Long rTableId);
}