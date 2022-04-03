package de.metas.ui.web.pickingV2.packageable;
 import java.time.LocalDate;
import org.adempiere.warehouse.WarehouseTypeId;
import de.metas.bpartner.BPartnerId;
import de.metas.order.OrderId;
import de.metas.shipping.ShipperId;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class PackageableViewFilterVO {

 public  PackageableViewFilterVO ANY;

 public  String FILTER_ID;

 public  String PARAM_C_Order_ID;

 public  String PARAM_Customer_ID;

 public  String PARAM_M_Warehouse_Type_ID;

 public  String PARAM_DeliveryDate;

 public  String PARAM_PreparationDate;

 public  String PARAM_M_Shipper_ID;

 private OrderId salesOrderId;

 private BPartnerId customerId;

 private WarehouseTypeId warehouseTypeId;

 private LocalDate deliveryDate;

 private LocalDate preparationDate;

 private ShipperId shipperId;


}