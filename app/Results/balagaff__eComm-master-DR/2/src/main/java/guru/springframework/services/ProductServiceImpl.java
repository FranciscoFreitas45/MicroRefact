package guru.springframework.services;
 import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class ProductServiceImpl implements ProductService{

 private  ProductRepository productRepository;


@Override
public Iterable<Product> listAllProducts(){
    return productRepository.findAll();
}


@Autowired
public void setProductRepository(ProductRepository productRepository){
    this.productRepository = productRepository;
}


@Override
public Product saveProduct(Product product){
    // TODO Auto-generated method stub
    return productRepository.save(product);
}


@Override
public void deleteProduct(Integer id){
    productRepository.delete(id);
}


@Override
public Page<Product> findAll(Pageable pageable){
    return productRepository.findAll(pageable);
}


@Override
public Product getProductById(Integer id){
    return productRepository.findOne(id);
}


}