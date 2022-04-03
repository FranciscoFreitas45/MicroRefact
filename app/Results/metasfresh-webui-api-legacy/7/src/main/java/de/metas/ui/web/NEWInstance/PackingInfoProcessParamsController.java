package de.metas.ui.web.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PackingInfoProcessParamsController {

 private PackingInfoProcessParams packinginfoprocessparams;


@GetMapping
("/builder")
public Object builder(@RequestParam(name = "Object") Object Object){
  return packinginfoprocessparams.builder(Object);
}


@GetMapping
("/defaultLUTUConfigManager")
public Object defaultLUTUConfigManager(@RequestParam(name = "Object") Object Object){
  return packinginfoprocessparams.defaultLUTUConfigManager(Object);
}


@PutMapping
("/setTU_HU_PI_Item_Product_ID")
public void setTU_HU_PI_Item_Product_ID(@RequestParam(name = "tu_HU_PI_Item_Product_ID") int tu_HU_PI_Item_Product_ID){
packinginfoprocessparams.setTU_HU_PI_Item_Product_ID(tu_HU_PI_Item_Product_ID);
}


@PutMapping
("/setLuPiItemId")
public void setLuPiItemId(@RequestParam(name = "lu_PI_Item_ID") int lu_PI_Item_ID){
packinginfoprocessparams.setLuPiItemId(lu_PI_Item_ID);
}


@PutMapping
("/setQtyLU")
public void setQtyLU(@RequestParam(name = "qtyLU") BigDecimal qtyLU){
packinginfoprocessparams.setQtyLU(qtyLU);
}


@PutMapping
("/setQtyTU")
public void setQtyTU(@RequestParam(name = "qtyTU") BigDecimal qtyTU){
packinginfoprocessparams.setQtyTU(qtyTU);
}


@PutMapping
("/setQtyCU")
public void setQtyCU(@RequestParam(name = "qtyCU") BigDecimal qtyCU){
packinginfoprocessparams.setQtyCU(qtyCU);
}


}