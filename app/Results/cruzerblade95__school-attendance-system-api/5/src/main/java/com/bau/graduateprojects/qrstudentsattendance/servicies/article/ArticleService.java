package com.bau.graduateprojects.qrstudentsattendance.servicies.article;
 import com.bau.graduateprojects.qrstudentsattendance.entities.ArticleEntity;
import java.util.List;
public interface ArticleService {


public ArticleEntity getArticleById(Long id)
;

public ArticleEntity insert(ArticleEntity articleEntity)
;

public List<ArticleEntity> list()
;

public long getCount()
;

public void remove(Long id)
;

}