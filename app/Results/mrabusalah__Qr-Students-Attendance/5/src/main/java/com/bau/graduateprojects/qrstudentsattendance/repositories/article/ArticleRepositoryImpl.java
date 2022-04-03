package com.bau.graduateprojects.qrstudentsattendance.repositories.article;
 import com.bau.graduateprojects.qrstudentsattendance.entities.ArticleEntity;
import com.bau.graduateprojects.qrstudentsattendance.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects.isNull;
@Repository
public class ArticleRepositoryImpl implements ArticleRepository{

 private  SpringJpaArticleRepository jpaRepository;

public ArticleRepositoryImpl(SpringJpaArticleRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
}
@Override
public ArticleEntity getArticleById(Long id){
    return jpaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("article not found with id = " + id));
}


public boolean isEmpty(String url){
    return isNull(url) || url.trim().isEmpty();
}


@Override
public ArticleEntity insert(ArticleEntity articleEntity){
    if (isEmpty(articleEntity.getImageUrl())) {
        articleEntity.setImageUrl("https://i.ibb.co/sWRyJHp/article.png");
    }
    return jpaRepository.save(articleEntity);
}


@Override
public List<ArticleEntity> list(){
    return jpaRepository.findAll();
}


@Override
public long getCount(){
    return jpaRepository.count();
}


@Override
public void remove(Long id){
    jpaRepository.deleteById(id);
}


}