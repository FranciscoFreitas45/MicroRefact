package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.sys.repository.MenuRepository;
import org.live.sys.entity.Menu;
@Service
public class MenuPageElementService {

@Autowired
 private MenuRepository menurepository;


public Menu getMenu(String idVM7Z){
return menurepository.getMenu(idVM7Z);
}


public void setMenu(String idVM7Z,Menu menu){
menurepository.setMenu(idVM7Z,menu);
}


}