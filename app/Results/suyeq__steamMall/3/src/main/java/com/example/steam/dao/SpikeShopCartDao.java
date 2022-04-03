package com.example.steam.dao;
 import com.example.steam.entity.SpikeShopCart;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
@Repository
public interface SpikeShopCartDao {


@Insert("insert into spikeshopcart(email,spikegameid) value(#{email},#{spikeGameId})")
public int addSpikeShopCart(SpikeShopCart spikeShopCart)
;

@Select("select * from spikeshopcart where email=#{email}")
public SpikeShopCart findSpikeShopCartByUserEmail(String email)
;

@Select("select * from spikeshopcart where email=#{email} and spikegameid=#{spikeGameId}")
public SpikeShopCart findSpikeShopCart(String email,long spikeGameId)
;

@Delete("delete from spikeshopcart where email=#{email}")
public int deleteSpikeShopCart(String email)
;

}