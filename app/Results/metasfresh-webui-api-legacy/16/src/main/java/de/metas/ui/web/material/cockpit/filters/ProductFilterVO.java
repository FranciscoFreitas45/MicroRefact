package de.metas.ui.web.material.cockpit.filters;
 import org.compiere.model.I_M_Product;
import de.metas.material.cockpit.model.I_MD_Cockpit;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
public class ProductFilterVO {

 public  ProductFilterVO EMPTY;

 public  String FILTER_ID;

 public  String PARAM_ProductValue;

 private String productValue;

 public  String PARAM_ProductName;

 private String productName;

 public  String PARAM_M_Product_Category_ID;

 private int productCategoryId;

 public  String PARAM_IsPurchased;

 private Boolean isPurchased;

 public  String PARAM_IsSold;

 private Boolean isSold;


}