package lk.sliit.csse.procurementsystem.controllers;
 import java.io.IOException;
import java.util.List;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import lk.sliit.csse.procurementsystem.models.GoodReceipt;
import lk.sliit.csse.procurementsystem.models.ReceiptMaterial;
import lk.sliit.csse.procurementsystem.repositories.GoodReceiptRepository;
import lk.sliit.csse.procurementsystem.repositories.ReceiptMaterialRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
@Data
@Named
public class GoodReceiptController {

 public  GoodReceipt goodReceipt;

 public  ReceiptMaterial receiptMaterial;

@Autowired
 private  GoodReceiptRepository goodReceiptRepository;

@Autowired
 private  ReceiptMaterialRepository receiptMaterialRepository;


public void deleteReceipt(long id){
    goodReceiptRepository.deleteById(id);
    ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
}


public List<ReceiptMaterial> getReceiptItem(){
    return receiptMaterialRepository.findAll();
}


public List<GoodReceipt> getReceipt(){
    return goodReceiptRepository.findAll();
}


public void addReceiptItem(){
    receiptMaterialRepository.save(receiptMaterial);
    this.receiptMaterial = new ReceiptMaterial();
}


public void addNewReceipt(){
    goodReceiptRepository.save(goodReceipt);
    this.goodReceipt = new GoodReceipt();
}


}