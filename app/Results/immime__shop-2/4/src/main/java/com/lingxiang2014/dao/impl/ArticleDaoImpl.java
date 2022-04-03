package com.lingxiang2014.dao.impl;
 import java.util.Date;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import org.springframework.stereotype.Repository;
import com.lingxiang2014.Filter;
import com.lingxiang2014.Order;
import com.lingxiang2014.Page;
import com.lingxiang2014.Pageable;
import com.lingxiang2014.dao.ArticleDao;
import com.lingxiang2014.entity.Article;
import com.lingxiang2014.entity.ArticleCategory;
import com.lingxiang2014.entity.Tag;
@Repository("articleDaoImpl")
public class ArticleDaoImpl extends BaseDaoImpl<Article, Long>implements ArticleDao{


public List<Article> findList(ArticleCategory articleCategory,Date beginDate,Date endDate,Integer first,Integer count){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> root = criteriaQuery.from(Article.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isPublication"), true));
    if (articleCategory != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"), articleCategory), criteriaBuilder.like(root.get("articleCategory").<String>get("treePath"), "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
    }
    if (beginDate != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.greaterThanOrEqualTo(root.<Date>get("createDate"), beginDate));
    }
    if (endDate != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.lessThanOrEqualTo(root.<Date>get("createDate"), endDate));
    }
    criteriaQuery.where(restrictions);
    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
    return super.findList(criteriaQuery, first, count, null, null);
}


public Page<Article> findPage(ArticleCategory articleCategory,List<Tag> tags,Pageable pageable){
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Article> criteriaQuery = criteriaBuilder.createQuery(Article.class);
    Root<Article> root = criteriaQuery.from(Article.class);
    criteriaQuery.select(root);
    Predicate restrictions = criteriaBuilder.conjunction();
    restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isPublication"), true));
    if (articleCategory != null) {
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("articleCategory"), articleCategory), criteriaBuilder.like(root.get("articleCategory").<String>get("treePath"), "%" + ArticleCategory.TREE_PATH_SEPARATOR + articleCategory.getId() + ArticleCategory.TREE_PATH_SEPARATOR + "%")));
    }
    if (tags != null && !tags.isEmpty()) {
        Subquery<Article> subquery = criteriaQuery.subquery(Article.class);
        Root<Article> subqueryRoot = subquery.from(Article.class);
        subquery.select(subqueryRoot);
        subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("tags").in(tags));
        restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
    }
    criteriaQuery.where(restrictions);
    criteriaQuery.orderBy(criteriaBuilder.desc(root.get("isTop")));
    return super.findPage(criteriaQuery, pageable);
}


}