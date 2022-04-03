package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PricingConditionsRowLookupsController {

 private PricingConditionsRowLookups pricingconditionsrowlookups;


@GetMapping
("/newInstance")
public PricingConditionsRowLookups newInstance(){
  return pricingconditionsrowlookups.newInstance();
}


@GetMapping
("/lookupBPartner")
public LookupValue lookupBPartner(@RequestParam(name = "bpartnerId") BPartnerId bpartnerId){
  return pricingconditionsrowlookups.lookupBPartner(bpartnerId);
}


}