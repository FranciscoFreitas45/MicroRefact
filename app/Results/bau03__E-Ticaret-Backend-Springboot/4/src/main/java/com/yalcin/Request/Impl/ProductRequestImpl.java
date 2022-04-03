package com.yalcin.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.yalcin.DTO.Product;
import com.yalcin.Request.ProductRequest;
public class ProductRequestImpl implements ProductRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setProduct(Product product,Integer id){
 restTemplate.put("http://3/Showcase/{id}/Product/setProduct",product,id);
 return ;
}


public Product getProduct(Integer id){
 Product aux = restTemplate.getForObject("http://3/Showcase/{id}/Product/getProduct",Product.class,id);
return aux;
}


}