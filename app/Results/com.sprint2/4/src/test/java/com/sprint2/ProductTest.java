package com.sprint2;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sprint2.Exceptions.InvalidOperation;
import com.sprint2.controller.ProductController;
import com.sprint2.model.Product;
import com.sprint2.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductTest {
	@Mock
	ProductService productservice;
	
	@InjectMocks
	ProductController productController;

	@Test
	public void update()throws InvalidOperation
	{
		Product product=new Product("wood","5","wood is used for construction");
		Mockito.when(productController.updateProduct( product)).thenReturn(product);
		assertEquals(product,productController.updateProduct(product));

	}
	@Test
    public void getall() {
        List<Product> product=new ArrayList<Product>();
        Mockito.when(productController.getAllProduct()).thenReturn(product);
        assertEquals(product,productController.getAllProduct());

    }
	@Test
	public void addProducts() 
	{
		Product p1=new Product("wood","5","wood is used for construction");
		Mockito.when(productController.addProduct(p1)).thenReturn(p1);
	    assertEquals(p1,productController.addProduct(p1));
	}
	@Test
    public void get() {
		Product product=new Product();

        Mockito.when(productController.getProductByproductId(26)).thenReturn(product);
        assertEquals(product,productController.getProductByproductId(26));

    }
	@Test
	 public void testdelete() {
		 Boolean str1=true;
		
		 Mockito.when(productController.deleteProductbyproductId(26)).thenReturn(str1);
		    assertEquals(str1,productController.deleteProductbyproductId(26));
	 }


}
