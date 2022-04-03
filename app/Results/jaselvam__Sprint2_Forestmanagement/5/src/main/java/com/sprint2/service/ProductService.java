package com.sprint2.service;
 import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.sprint2.Exceptions.InvalidOperation;
import com.sprint2.model.Product;
import com.sprint2.repository.ProductRepository;
import com.sprint2.Interface.ProductRepository;
@Service
public class ProductService implements IProductService{

@Autowired
 private  ProductRepository productRepository;


public Product getProductByproductId(Integer productId){
    try {
        return productRepository.findById(productId).get();
    } catch (NoSuchElementException | IllegalArgumentException ie) {
        throw new InvalidOperation("Enter the correct Product Id");
    }
}


public List<Product> getAllProducts(){
    List<Product> Productlist = new ArrayList<Product>();
    productRepository.findAll().forEach(product -> Productlist.add(product));
    return Productlist;
}


public Product addProduct(Product Product){
    try {
        return productRepository.save(Product);
    } catch (InvalidOperation ie) {
        throw new InvalidOperation("Enter correct product details");
    }
}


public Product updateProduct(Product Product){
    try {
        productRepository.save(Product);
    } catch (InvalidOperation ie) {
        throw new InvalidOperation("Product is not found with given id");
    }
    return Product;
}


public boolean deleteProductbyproductId(Integer productId){
    try {
        System.out.println("Please enter a Integer value : ");
        productRepository.deleteById(productId);
        System.out.println("product details are successfully deleted");
        return true;
    } catch (IllegalArgumentException | EmptyResultDataAccessException ie) {
        throw new InvalidOperation("Product not deleted \n enter the correct id");
    }
}


}