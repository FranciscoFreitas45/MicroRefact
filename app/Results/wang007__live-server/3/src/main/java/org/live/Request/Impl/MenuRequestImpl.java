package org.live.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.DTO.Menu;
import org.live.Request.MenuRequest;
public class MenuRequestImpl implements MenuRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Menu getMenu(String idVM7Z){
 Menu aux = restTemplate.getForObject("http://10/PageElement/{id}/Menu/getMenu",Menu.class,idVM7Z);
return aux;
}


public void setMenu(Menu menu,String idVM7Z){
 restTemplate.put("http://10/PageElement/{id}/Menu/setMenu",menu,idVM7Z);
 return ;
}


}