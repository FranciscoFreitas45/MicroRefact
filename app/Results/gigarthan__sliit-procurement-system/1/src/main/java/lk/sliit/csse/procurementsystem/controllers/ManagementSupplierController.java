package lk.sliit.csse.procurementsystem.controllers;
 import lk.sliit.csse.procurementsystem.models.Supplier;
import lk.sliit.csse.procurementsystem.repositories.SupplierRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.inject.Named;
import java.util.List;
@Data
@Named
public class ManagementSupplierController {

 private  Supplier supplier;

@Autowired
 private  SupplierRepository supplierRepository;


public List<Supplier> getAllSuppliers(){
    return supplierRepository.findAll();
}


public void addSupplier(){
    supplier.setPassword(new BCryptPasswordEncoder().encode("1234"));
    supplier.setBlackListed(false);
    supplierRepository.save(this.supplier);
    this.supplier = new Supplier();
}


public void deleteSupplier(Supplier sup){
    supplierRepository.delete(sup);
}


}