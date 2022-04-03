package com.easyshopping.service.impl;
 import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import com.easyshopping.dao.GoodsDao;
import com.easyshopping.dao.ProductDao;
import com.easyshopping.entity.Goods;
import com.easyshopping.entity.Product;
import com.easyshopping.service.GoodsService;
import com.easyshopping.service.StaticService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import com.easyshopping.Interface.StaticService;
@Service("goodsServiceImpl")
public class GoodsServiceImpl extends BaseServiceImpl<Goods, Long>implements GoodsService{

@Resource(name = "goodsDaoImpl")
 private  GoodsDao goodsDao;

@Resource(name = "productDaoImpl")
 private  ProductDao productDao;

@Resource(name = "staticServiceImpl")
 private  StaticService staticService;


@Resource(name = "goodsDaoImpl")
public void setBaseDao(GoodsDao goodsDao){
    super.setBaseDao(goodsDao);
}


@Override
@Transactional
@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
public void save(Goods goods){
    Assert.notNull(goods);
    super.save(goods);
    goodsDao.flush();
    if (goods.getProducts() != null) {
        for (Product product : goods.getProducts()) {
            staticService.build(product);
        }
    }
}


@Override
@Transactional
@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
public Goods update(Goods goods,String ignoreProperties){
    return super.update(goods, ignoreProperties);
}


public boolean evaluate(Object object){
    Product product = (Product) object;
    return product != null && product.getId() != null;
}


@Override
@Transactional
@CacheEvict(value = { "product", "productCategory", "review", "consultation" }, allEntries = true)
public void delete(Goods goods){
    if (goods != null && goods.getProducts() != null) {
        for (Product product : goods.getProducts()) {
            staticService.delete(product);
        }
    }
    super.delete(goods);
}


}