package com.zammc.domain.goods;
 import lombok.Data;
import java.util.List;
@Data
public class GoodsDTO {

 private  long cateId;

 private  String cateName;

 private  List<GoodsEntity> goodsEntities;


}