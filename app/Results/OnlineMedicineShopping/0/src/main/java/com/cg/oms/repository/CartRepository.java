package com.cg.oms.repository;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.oms.model.Cart;
public interface CartRepository extends JpaRepository<Cart, Long>{


public void setCart(Long cartId,Cart cart);

public Cart getCart(Long cartId);

}