package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SubOrderController {

 private SubOrder suborder;

 private SubOrder suborder;


@PutMapping
("/setItemCount")
public void setItemCount(@RequestParam(name = "itemCount") Integer itemCount){
suborder.setItemCount(itemCount);
}


@PutMapping
("/setItemId")
public void setItemId(@RequestParam(name = "itemId") Integer itemId){
suborder.setItemId(itemId);
}


@PutMapping
("/setItemName")
public void setItemName(@RequestParam(name = "itemName") String itemName){
suborder.setItemName(itemName);
}


@PutMapping
("/setItemOutNum")
public void setItemOutNum(@RequestParam(name = "itemOutNum") String itemOutNum){
suborder.setItemOutNum(itemOutNum);
}


@PutMapping
("/setItemPrice")
public void setItemPrice(@RequestParam(name = "itemPrice") Double itemPrice){
suborder.setItemPrice(itemPrice);
}


@PutMapping
("/setSkuId")
public void setSkuId(@RequestParam(name = "skuId") Integer skuId){
suborder.setSkuId(skuId);
}


}