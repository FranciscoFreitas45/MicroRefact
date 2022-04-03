package lk.sliit.csse.procurementsystem.controllers;
 import java.lang.System.console;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import lk.sliit.csse.procurementsystem.models.AccountingStaff;
import lk.sliit.csse.procurementsystem.models.ProcurementStaff;
import lk.sliit.csse.procurementsystem.models.SiteManager;
import lk.sliit.csse.procurementsystem.repositories.AccountingStaffRepository;
import lk.sliit.csse.procurementsystem.repositories.ProcurementStaffRepository;
import lk.sliit.csse.procurementsystem.repositories.SiteManagerRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import javax.inject.Named;
import java.util.List;
import java.util.Optional;
import javax.faces.bean.ManagedBean;
import lk.sliit.csse.procurementsystem.models.ItemList;
import lk.sliit.csse.procurementsystem.models.MaterialRequest;
import lk.sliit.csse.procurementsystem.models.Items;
import lk.sliit.csse.procurementsystem.repositories.ItemListRepository;
import lk.sliit.csse.procurementsystem.repositories.ItemsRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lk.sliit.csse.procurementsystem.repositories.MaterialRequestRepository;
@Data
@Named
@RestController
@ManagedBean
public class SiteManagerController {

 private  MaterialRequest materialRequest;

 private  Items items;

 private  ItemList itemList;

 private  String itemName;

 private String itemListStr;

@Autowired
 private  MaterialRequestRepository materialRequestRepository;

@Autowired
 private  ItemsRepository itemsRepository;

@Autowired
 private  ItemListRepository itemListRepository;

 private SimpleDateFormat formatter;

 private Date date;


public List<Items> getItems()/*String orderRef*/
{
    return itemsRepository.findAll();
}


public long getNextReqOrderNo(){
    return (materialRequestRepository.count() + 1);
}


public void addOrder(){
    materialRequest.setOrderDate(formatter.format(date));
    materialRequestRepository.save(materialRequest);
    this.materialRequest = new MaterialRequest();
}


public String getTodayDate(){
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();
    return formatter.format(date);
}


public List<ItemList> getItemsForOrder()/*String orderRef*/
{
    return itemListRepository.findAll();
}


public List<String> completeText(String query){
    List<String> results = new ArrayList<String>();
    for (int i = 0; i < 10; i++) {
        results.add(query + i);
    }
    results.add("beto");
    return results;
}


public List<MaterialRequest> getOrders(){
    return materialRequestRepository.findAll();
}


public void addItemForOrder(){
    itemList.setReqOrderNo(getNextReqOrderNo());
    itemList.getRelavantItemId(items.getItemName());
    itemListRepository.save(itemList);
    this.itemList = new ItemList();
}


}