package com.example.steam.dao;
 import com.example.steam.entity.ShoppingCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ShoppingCartDao {


@Insert("insert into shoppingcart(email,posterimage,gameprice,gamename,gameid) value(#{email},#{posterImage},#{gamePrice},#{gameName},#{gameId})")
public int addOneCart(ShoppingCart shoppingCart)
;

@Delete("delete from shoppingcart where id=#{id}")
public int deleteOneGameById(long id)
;

@Select("select * from shoppingcart")
public List<ShoppingCart> findAllCart()
;

@Select("select * from shoppingcart where email=#{email}")
public List<ShoppingCart> findCartByUserEmail(String email)
;

@Delete("delete from shoppingcart where email=#{email}")
public int deleteAllGameByUserEmail(String email)
;

}