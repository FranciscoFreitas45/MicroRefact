package guru.springframework.controllers;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import guru.springframework.domain.Product;
import guru.springframework.domain.GateTransportDetails;
import guru.springframework.domain.PackageType;
import guru.springframework.repositories.GateTransportDetailsRepository;
import guru.springframework.services.GateTransportDetailsService;
import guru.springframework.services.PackageTypeService;
import guru.springframework.services.ProductService;
@Controller
public class ProductController {

 private  ProductService productService;

@Autowired
 private GateTransportDetailsRepository gtdRepo;

@Autowired
 private  PackageTypeService packagetypeService;

@Autowired
 private  GateTransportDetailsService gatetransportdetailsService;


@Autowired
public void setProductService(ProductService productService){
    this.productService = productService;
}


@ModelAttribute("allTransportnames")
public List<GateTransportDetails> allTransportnames(){
    List<GateTransportDetails> transpornamelist = gatetransportdetailsService.getAllTransportdetails();
    // List<PackageType> userList= PackageTypeService.
    return transpornamelist;
}


@RequestMapping("product/{id}")
public String showProduct(Integer id,Model model){
    model.addAttribute("product", productService.getProductById(id));
    return "productshow";
}


@ModelAttribute("allPackagetypes")
public List<PackageType> allPackagetypes(){
    List<PackageType> userList = packagetypeService.getAllPackagetypes();
    // List<PackageType> userList= PackageTypeService.
    return userList;
}


@RequestMapping("product/edit/{id}")
public String edit(Integer id,Model model){
    model.addAttribute("product", productService.getProductById(id));
    return "productform";
}


@RequestMapping(value = "product", method = RequestMethod.POST)
public String saveProduct(String transname,Product product){
    // product.setTrans_type(transname);
    productService.saveProduct(product);
    return "redirect:/products/" + product.getId();
}


@RequestMapping(value = "/products", method = RequestMethod.GET)
public String list(Model model,Pageable pageable){
    Page<Product> productPage = productService.findAll(pageable);
    PageWrapper<Product> page = new PageWrapper<Product>(productPage, "/products");
    model.addAttribute("products", page.getContent());
    model.addAttribute("page", page);
    return "products";
}


@RequestMapping("product/delete/{id}")
public String delete(Integer id){
    productService.deleteProduct(id);
    return "redirect:/products";
}


@RequestMapping("product/new")
public String newProduct(Model model){
    model.addAttribute("product", new Product());
    model.addAttribute("gtt", gtdRepo.findAll());
    return "productform";
}


}