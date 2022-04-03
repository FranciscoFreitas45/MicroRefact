package lk.sliit.csse.procurementsystem.controllers;
 import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import lk.sliit.csse.procurementsystem.models.SupplyMaterial;
import lk.sliit.csse.procurementsystem.repositories.ReceiptMaterialRepository;
import lk.sliit.csse.procurementsystem.repositories.SupplyMaterialRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
@Data
@Named
public class SupplyMaterialController {

 public  SupplyMaterial supplyMaterial;

@Autowired
 private  SupplyMaterialRepository supplyMaterialRepository;


public void addNewItem(){
    supplyMaterialRepository.save(supplyMaterial);
    this.supplyMaterial = new SupplyMaterial();
}


public void deleteItem(long id){
    supplyMaterialRepository.deleteById(id);
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
}


public List<SupplyMaterial> getItem(){
    return supplyMaterialRepository.findAll();
}


public void updateItem(long id){
// Optional it = itemRepository.findById(id);
// itemList = (SupplyItemList) it.get();
// ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
// ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
}


public void buttonAction(ActionEvent actionEvent){
    addMessage("Successfully Done");
}


public void addMessage(String summary){
    FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
    FacesContext.getCurrentInstance().addMessage(null, message);
}


}