package guru.springframework.services;
 import guru.springframework.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface ProductService {


public Iterable<Product> listAllProducts()
;

public Product saveProduct(Product product)
;

public void deleteProduct(Integer id)
;

public Page<Product> findAll(Pageable pageable)
;

public Product getProductById(Integer id)
;

}