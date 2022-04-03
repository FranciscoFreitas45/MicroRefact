package de.metas.ui.web.order.products_proposal.filters;
 import lombok.Builder;
import lombok.Value;
@Builder
@Value
public class ProductsProposalViewFilter {

 public  ProductsProposalViewFilter ANY;

 public  String FILTER_ID;

 static  String PARAM_ProductName;

 private String productName;


}