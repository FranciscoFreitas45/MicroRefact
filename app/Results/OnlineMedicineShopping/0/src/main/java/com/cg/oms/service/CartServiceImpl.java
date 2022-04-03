package com.cg.oms.service;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.oms.converter.CartConverter;
import com.cg.oms.exception.CartNotFoundException;
import com.cg.oms.model.Cart;
import com.cg.oms.repository.CartRepository;
import com.cg.oms.vo.CartVo;
@Service
public class CartServiceImpl implements CartService{

 public  String EXCEPTION_MESSAGE;

@Autowired
 private  CartRepository cartRepository;

@Autowired
 private  CartConverter cartConverter;


@Override
public String saveCart(CartVo cartVo){
    Cart cart = cartConverter.voToModel(cartVo);
    cart = cartRepository.save(cart);
    CartVo savedVo = cartConverter.modelToVo(cart);
    return "Cart Added SuccessFully!!! " + savedVo.toString();
}


@Override
public List<CartVo> getAllCart(){
    List<Cart> cart = cartRepository.findAll();
    return cartConverter.modelToVo(cart);
}


@Override
public CartVo getCartById(Long id) throws CartNotFoundException{
    Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(EXCEPTION_MESSAGE + id));
    return cartConverter.modelToVo(cart);
}


@Override
public String updateCart(Long id,CartVo newCart) throws CartNotFoundException{
    Cart oldCart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(EXCEPTION_MESSAGE + id));
    oldCart.setCartId(newCart.getCartId());
    oldCart.setCostPerPiece(newCart.getCostPerPiece());
    oldCart.setMedicineList(newCart.getMedicineList());
    oldCart.setQuantity(newCart.getQuantity());
    oldCart.setUser(newCart.getUser());
    Cart updatedCart = cartRepository.save(oldCart);
    CartVo savedVo = cartConverter.modelToVo(updatedCart);
    return "Updated successfully" + savedVo.toString();
}


@Override
public String deleteCart(Long id)  throws CartNotFoundException {
    Cart cart = cartRepository.findById(id).orElseThrow(() -> new CartNotFoundException(EXCEPTION_MESSAGE + id));
    cartRepository.delete(cart);
    return "Record Deleted Successfully!!";
}


@Override
public List<CartVo> getAllCarts(){
    List<Cart> carts = cartRepository.findAll();
    return cartConverter.modelToVo(carts);
}


}