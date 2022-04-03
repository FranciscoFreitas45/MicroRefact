package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PurchaseRowLookupsController {

 private PurchaseRowLookups purchaserowlookups;


@GetMapping
("/createProductLookupValue")
public LookupValue createProductLookupValue(@RequestParam(name = "productId") ProductId productId,@RequestParam(name = "productValue") String productValue,@RequestParam(name = "productName") String productName){
  return purchaserowlookups.createProductLookupValue(productId,productValue,productName);
}


@GetMapping
("/createASILookupValue")
public LookupValue createASILookupValue(@RequestParam(name = "attributeSetInstanceId") AttributeSetInstanceId attributeSetInstanceId){
  return purchaserowlookups.createASILookupValue(attributeSetInstanceId);
}


@GetMapping
("/createBPartnerLookupValue")
public LookupValue createBPartnerLookupValue(@RequestParam(name = "bpartnerId") BPartnerId bpartnerId){
  return purchaserowlookups.createBPartnerLookupValue(bpartnerId);
}


@GetMapping
("/createUOMLookupValue")
public String createUOMLookupValue(@RequestParam(name = "uom") I_C_UOM uom){
  return purchaserowlookups.createUOMLookupValue(uom);
}


@GetMapping
("/newInstance")
public PurchaseRowLookups newInstance(){
  return purchaserowlookups.newInstance();
}


}