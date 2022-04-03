package com.easyshopping.dao.impl;
 import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.FlushModeType;
import javax.persistence.TypedQuery;
import com.easyshopping.dao.ProductCategoryDao;
import com.easyshopping.entity.Product;
import com.easyshopping.entity.ProductCategory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
@Repository("productCategoryDaoImpl")
public class ProductCategoryDaoImpl extends BaseDaoImpl<ProductCategory, Long>implements ProductCategoryDao{


public List<ProductCategory> findRoots(Integer count){
    String jpql = "select productCategory from ProductCategory productCategory where productCategory.parent is null order by productCategory.order asc";
    TypedQuery<ProductCategory> query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT);
    if (count != null) {
        query.setMaxResults(count);
    }
    return query.getResultList();
}


@Override
public ProductCategory merge(ProductCategory productCategory){
    Assert.notNull(productCategory);
    setValue(productCategory);
    for (ProductCategory category : findChildren(productCategory, null)) {
        setValue(category);
    }
    return super.merge(productCategory);
}


public void setValue(ProductCategory productCategory){
    if (productCategory == null) {
        return;
    }
    ProductCategory parent = productCategory.getParent();
    if (parent != null) {
        productCategory.setTreePath(parent.getTreePath() + parent.getId() + ProductCategory.TREE_PATH_SEPARATOR);
    } else {
        productCategory.setTreePath(ProductCategory.TREE_PATH_SEPARATOR);
    }
    productCategory.setGrade(productCategory.getTreePaths().size());
}


public List<ProductCategory> findParents(ProductCategory productCategory,Integer count){
    if (productCategory == null || productCategory.getParent() == null) {
        return Collections.<ProductCategory>emptyList();
    }
    String jpql = "select productCategory from ProductCategory productCategory where productCategory.id in (:ids) order by productCategory.grade asc";
    TypedQuery<ProductCategory> query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("ids", productCategory.getTreePaths());
    if (count != null) {
        query.setMaxResults(count);
    }
    return query.getResultList();
}


public List<ProductCategory> findChildren(ProductCategory productCategory,Integer count){
    TypedQuery<ProductCategory> query;
    if (productCategory != null) {
        String jpql = "select productCategory from ProductCategory productCategory where productCategory.treePath like :treePath order by productCategory.order asc";
        query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT).setParameter("treePath", "%" + ProductCategory.TREE_PATH_SEPARATOR + productCategory.getId() + ProductCategory.TREE_PATH_SEPARATOR + "%");
    } else {
        String jpql = "select productCategory from ProductCategory productCategory order by productCategory.order asc";
        query = entityManager.createQuery(jpql, ProductCategory.class).setFlushMode(FlushModeType.COMMIT);
    }
    if (count != null) {
        query.setMaxResults(count);
    }
    return sort(query.getResultList(), productCategory);
}


@Override
public void persist(ProductCategory productCategory){
    Assert.notNull(productCategory);
    setValue(productCategory);
    super.persist(productCategory);
}


public List<ProductCategory> sort(List<ProductCategory> productCategories,ProductCategory parent){
    List<ProductCategory> result = new ArrayList<ProductCategory>();
    if (productCategories != null) {
        for (ProductCategory productCategory : productCategories) {
            if ((productCategory.getParent() != null && productCategory.getParent().equals(parent)) || (productCategory.getParent() == null && parent == null)) {
                result.add(productCategory);
                result.addAll(sort(productCategories, productCategory));
            }
        }
    }
    return result;
}


@Override
public void remove(ProductCategory productCategory){
    if (productCategory != null) {
        StringBuffer jpql = new StringBuffer("update Product product set ");
        for (int i = 0; i < Product.ATTRIBUTE_VALUE_PROPERTY_COUNT; i++) {
            String propertyName = Product.ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + i;
            if (i == 0) {
                jpql.append("product." + propertyName + " = null");
            } else {
                jpql.append(", product." + propertyName + " = null");
            }
        }
        jpql.append(" where product.productCategory = :productCategory");
        entityManager.createQuery(jpql.toString()).setFlushMode(FlushModeType.COMMIT).setParameter("productCategory", productCategory).executeUpdate();
        super.remove(productCategory);
    }
}


}