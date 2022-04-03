package guru.springframework.controllers;
 import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import guru.springframework.domain.BulkOrder;
import guru.springframework.domain.Customer;
import guru.springframework.domain.GateTransportDetails;
import guru.springframework.domain.PackageType;
import guru.springframework.repositories.BulkOrderRepository;
import guru.springframework.repositories.CustomerRepository;
import guru.springframework.repositories.GateTransportDetailsRepository;
import guru.springframework.repositories.UserRepository;
import guru.springframework.services.CustomerService;
import guru.springframework.services.GateTransportDetailsService;
import guru.springframework.services.PackageTypeService;
import guru.springframework.services.ProductService;
@Controller
public class ManualOrderController {

 private  ProductService productService;

@Autowired
 private GateTransportDetailsRepository gtdRepo;

@Autowired
 private  PackageTypeService packagetypeService;

@Autowired
 private  CustomerService customerservice;

@Autowired
 private  GateTransportDetailsService gatetransportdetailsService;

@Autowired
 private  CustomerRepository customerRepo;

@Autowired
 private  UserRepository userRepo;

@Autowired
 private  BulkOrderRepository bulkorderRepo;


@Autowired
public void setProductService(ProductService productService){
    this.productService = productService;
}


@RequestMapping(value = "/manual/new", method = RequestMethod.POST)
public String newManual(String po_number,int customer_id,Model model){
    BulkOrder bulkorder = new BulkOrder();
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    User user = userRepo.findByEmail(auth.getName());
    Customer custmer = customerRepo.findCustomerById(user.getCustomer_id());
    bulkorder.setSource_id(1);
    bulkorder.setCustomer_id(user.getCustomer_id());
    bulkorder.setPo_number(po_number);
    bulkorder.setOrder_no("MANUAL");
    bulkorder.setCreated(bulkorder.getCreated());
    bulkorder.setRequester_id(user.getId());
    // bulkorder.setCreated(created);
    // bulkorder.setSales_org_id(custmer.getSales_org_id());
    bulkorderRepo.save(bulkorder);
    return "redirect:/manual/new";
}


}