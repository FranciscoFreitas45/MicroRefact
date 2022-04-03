package cn.com.cnc.fcc.Interface;
public interface QmsSupplierRepository {

   public Optional<QmsSupplier> findQmsSupplierBySupplierCdAndFlagStatus(String supplierCd,String flagStatus);
}