package com.zis.shop.dto;
 import java.util.List;
import com.zis.shop.bean.ShopItemMapping;
public class ApiQueryItemsDto {

 private  List<ShopItemMapping> shopItemMappings;

 private  Long totalResults;


public List<ShopItemMapping> getShopItemMappings(){
    return shopItemMappings;
}


public void setShopItemMappings(List<ShopItemMapping> shopItemMappings){
    this.shopItemMappings = shopItemMappings;
}


public void setTotalResults(Long totalResults){
    this.totalResults = totalResults;
}


public Long getTotalResults(){
    return totalResults;
}


}