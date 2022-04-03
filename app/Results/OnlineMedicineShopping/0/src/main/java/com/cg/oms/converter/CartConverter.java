package com.cg.oms.converter;
 import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.cg.oms.model.Cart;
import com.cg.oms.vo.CartVo;
@Component
public class CartConverter {


	public CartVo modelToVo(Cart cart)
	{
		CartVo cartVo = new CartVo();
		cartVo.setCartId(cart.getCartId());
		cartVo.setCostPerPiece(cart.getCostPerPiece());
		cartVo.setMedicineList(cart.getMedicineList());
		cartVo.setQuantity(cart.getQuantity());
		cartVo.setUser(cart.getUser());
		return cartVo;
	}

    public Cart voToModel(CartVo cartVo)
	{
		Cart cart = new Cart();
		cart.setCartId(cartVo.getCartId());
		cart.setCostPerPiece(cartVo.getCostPerPiece());
		cart.setMedicineList(cartVo.getMedicineList());
		cart.setQuantity(cartVo.getQuantity());
		cart.setUser(cartVo.getUser());
		return cart;
	}



public List<Cart> voToModel(List<CartVo> cartVo){
    return cartVo.stream().map(x -> voToModel(x)).collect(Collectors.toList());
}


public List<CartVo> modelToVo(List<Cart> cart){
    return cart.stream().map(x -> modelToVo(x)).collect(Collectors.toList());
}


}