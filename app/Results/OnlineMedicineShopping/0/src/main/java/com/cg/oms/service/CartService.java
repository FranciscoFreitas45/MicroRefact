package com.cg.oms.service;

import java.util.List;

import com.cg.oms.exception.CartNotFoundException;
import com.cg.oms.vo.CartVo;

public interface CartService
{
	public List<CartVo> getAllCart();

	public CartVo getCartById(Long id) throws CartNotFoundException;;

	public String saveCart(CartVo cartVo);

	public String updateCart(Long id, CartVo newCart) throws CartNotFoundException;;

	public String deleteCart(Long id) throws CartNotFoundException;

	public List<CartVo> getAllCarts();

}
