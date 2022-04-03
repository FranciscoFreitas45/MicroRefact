package com.easyshopping.dao.impl;
 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import com.easyshopping.Filter;
import com.easyshopping.Order;
import com.easyshopping.Page;
import com.easyshopping.Pageable;
import com.easyshopping.Setting;
import com.easyshopping.dao.GoodsDao;
import com.easyshopping.dao.ProductDao;
import com.easyshopping.dao.SnDao;
import com.easyshopping.entity.Attribute;
import com.easyshopping.entity.Brand;
import com.easyshopping.entity.Goods;
import com.easyshopping.entity.Member;
import com.easyshopping.entity.Order.OrderStatus;
import com.easyshopping.entity.Order.PaymentStatus;
import com.easyshopping.entity.OrderItem;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.Product.OrderType;
import com.easyshopping.entity.ProductCategory;
import com.easyshopping.entity.Promotion;
import com.easyshopping.entity.Sn.Type;
import com.easyshopping.entity.SpecificationValue;
import com.easyshopping.entity.Tag;
import com.easyshopping.util.SettingUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import com.easyshopping.Interface.SnDao;
import com.easyshopping.DTO.Setting;
import com.easyshopping.DTO.SpecificationValue;
@Repository("productDaoImpl")
public class ProductDaoImpl extends BaseDaoImpl<Product, Long>implements ProductDao{

 private  Pattern pattern;

@Resource(name = "goodsDaoImpl")
 private  GoodsDao goodsDao;

@Resource(name = "snDaoImpl")
 private  SnDao snDao;


public int compare(SpecificationValue a1,SpecificationValue a2){
    return new CompareToBuilder().append(a1.getSpecification(), a2.getSpecification()).toComparison();
}


public boolean snExists(String sn){
    if (sn == null) {
        return false;
    }
    String jpql = "select count(*) from Product product where lower(product.sn) = lower(:sn)";
    Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
    return count > 0;
}


public Long count(Member favoriteMember,Boolean isMarketable,Boolean isList,Boolean isTop,Boolean isGift,Boolean isOutOfStock,Boolean isStockAlert){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
    Root<Product> root = criteriaQuery.from(Product.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (favoriteMember != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.join("favoriteMembers"), favoriteMember));
    }
    if (isMarketable != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isMarketable"), isMarketable));
    }
    if (isList != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isList"), isList));
    }
    if (isTop != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isTop"), isTop));
    }
    if (isGift != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isGift"), isGift));
    }
    Path<Integer> stock = root.get("stock");
    Path<Integer> allocatedStock = root.get("allocatedStock");
    if (isOutOfStock != null) {
        if (isOutOfStock) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNotNull(stock), criteriaBuilder.lessThanOrEqualTo(stock, allocatedStock));
        } else {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.isNull(stock), criteriaBuilder.greaterThan(stock, allocatedStock)));
        }
    }
    if (isStockAlert != null) {
        Setting setting = SettingUtils.get();
        if (isStockAlert) {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNotNull(stock), criteriaBuilder.lessThanOrEqualTo(stock, criteriaBuilder.sum(allocatedStock, setting.getStockAlertCount())));
        } else {
            restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.isNull(stock), criteriaBuilder.greaterThan(stock, criteriaBuilder.sum(allocatedStock, setting.getStockAlertCount()))));
        }
    }
    criteriaQuery.where(restrictions);
    return super.count(criteriaQuery, null);
}


public List<Object[]> findSalesList(Date beginDate,Date endDate,Integer count){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
    Root<Product> product = criteriaQuery.from(Product.class);
    Join<Product, OrderItem> orderItems = product.join("orderItems");
    Join<Product, com.easyshopping.entity.Order> order = orderItems.join("order");
    criteriaQuery.multiselect(product.get("id"), product.get("sn"), product.get("name"), product.get("fullName"), product.get("price"), criteriaBuilder.sum(orderItems.<Integer>get("quantity")), criteriaBuilder.sum(criteriaBuilder.prod(orderItems.<Integer>get("quantity"), orderItems.<BigDecimal>get("price"))));
    Predicate restrictions = criteriaBuilder.conjunction();
    if (beginDate != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(order.<Date>get("createDate"), beginDate));
    }
    if (endDate != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(order.<Date>get("createDate"), endDate));
    }
    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(order.get("orderStatus"), OrderStatus.completed), criteriaBuilder.equal(order.get("paymentStatus"), PaymentStatus.paid));
    criteriaQuery.where(restrictions);
    criteriaQuery.groupBy(product.get("id"), product.get("sn"), product.get("name"), product.get("fullName"), product.get("price"));
    criteriaQuery.orderBy(criteriaBuilder.desc(criteriaBuilder.sum(criteriaBuilder.prod(orderItems.<Integer>get("quantity"), orderItems.<BigDecimal>get("price")))));
    TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT);
    if (count != null && count >= 0) {
        query.setMaxResults(count);
    }
    return query.getResultList();
}


public Page<Product> findPage(Member member,Pageable pageable){
    if (member == null) {
        return new Page<Product>(Collections.<Product>emptyList(), 0, pageable);
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
    Root<Product> root = criteriaQuery.from(Product.class);
    criteriaQuery.select(root);
    criteriaQuery.where(criteriaBuilder.equal(root.join("favoriteMembers"), member));
    return super.findPage(criteriaQuery, pageable);
}


public boolean isPurchased(Member member,Product product){
    if (member == null || product == null) {
        return false;
    }
    String jqpl = "select count(*) from OrderItem orderItem where orderItem.product = :product and orderItem.order.member = :member and orderItem.order.orderStatus = :orderStatus";
    Long count = entityManager.createQuery(jqpl, Long.class).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).setParameter("member", member).setParameter("orderStatus", OrderStatus.completed).getSingleResult();
    return count > 0;
}


@Override
public void remove(Product product){
    if (product != null) {
        Goods goods = product.getGoods();
        if (goods != null && goods.getProducts() != null) {
            goods.getProducts().remove(product);
            if (goods.getProducts().isEmpty()) {
                goodsDao.remove(goods);
            }
        }
    }
    super.remove(product);
}


public List<Product> search(String keyword,Boolean isGift,Integer count){
    if (StringUtils.isEmpty(keyword)) {
        return Collections.<Product>emptyList();
    }
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
    Root<Product> root = criteriaQuery.from(Product.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (pattern.matcher(keyword).matches()) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("id"), Long.valueOf(keyword)), criteriaBuilder.like(root.<String>get("sn"), "%" + keyword + "%"), criteriaBuilder.like(root.<String>get("fullName"), "%" + keyword + "%")));
    } else {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.like(root.<String>get("sn"), "%" + keyword + "%"), criteriaBuilder.like(root.<String>get("fullName"), "%" + keyword + "%")));
    }
    if (isGift != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isGift"), isGift));
    }
    criteriaQuery.where(restrictions);
    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
    return super.findList(criteriaQuery, null, count, null, null);
}


public Product findBySn(String sn){
    if (sn == null) {
        return null;
    }
    String jpql = "select product from Product product where lower(product.sn) = lower(:sn)";
    try {
        return entityManager.createQuery(jpql, Product.class).setFlushMode(FlushModeType.COMMIT).setParameter("sn", sn).getSingleResult();
    } catch (NoResultException e) {
        return null;
    }
}


public List<Product> findList(Goods goods,Set<Product> excludes){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
    Root<Product> root = criteriaQuery.from(Product.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    if (goods != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("goods"), goods));
    }
    if (excludes != null && !excludes.isEmpty()) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.not(root.in(excludes)));
    }
    criteriaQuery.where(restrictions);
    return entityManager.createQuery(criteriaQuery).setFlushMode(FlushModeType.COMMIT).getResultList();
}


@Override
public Product merge(Product product){
    Assert.notNull(product);
    if (!product.getIsGift()) {
        String jpql = "delete from GiftItem giftItem where giftItem.gift = :product";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).executeUpdate();
    }
    if (!product.getIsMarketable() || product.getIsGift()) {
        String jpql = "delete from CartItem cartItem where cartItem.product = :product";
        entityManager.createQuery(jpql).setFlushMode(FlushModeType.COMMIT).setParameter("product", product).executeUpdate();
    }
    setValue(product);
    return super.merge(product);
}


public void setValue(Product product){
    if (product == null) {
        return;
    }
    if (StringUtils.isEmpty(product.getSn())) {
        String sn;
        do {
            sn = snDao.generate(Type.product);
        } while (snExists(sn));
        product.setSn(sn);
    }
    StringBuffer fullName = new StringBuffer(product.getName());
    if (product.getSpecificationValues() != null && !product.getSpecificationValues().isEmpty()) {
        List<SpecificationValue> specificationValues = new ArrayList<SpecificationValue>(product.getSpecificationValues());
        Collections.sort(specificationValues, new Comparator<SpecificationValue>() {

            public int compare(SpecificationValue a1, SpecificationValue a2) {
                return new CompareToBuilder().append(a1.getSpecification(), a2.getSpecification()).toComparison();
            }
        });
        fullName.append(Product.FULL_NAME_SPECIFICATION_PREFIX);
        int i = 0;
        for (Iterator<SpecificationValue> iterator = specificationValues.iterator(); iterator.hasNext(); i++) {
            if (i != 0) {
                fullName.append(Product.FULL_NAME_SPECIFICATION_SEPARATOR);
            }
            fullName.append(iterator.next().getName());
        }
        fullName.append(Product.FULL_NAME_SPECIFICATION_SUFFIX);
    }
    product.setFullName(fullName.toString());
}


@Override
public void persist(Product product){
    Assert.notNull(product);
    setValue(product);
    super.persist(product);
}


}