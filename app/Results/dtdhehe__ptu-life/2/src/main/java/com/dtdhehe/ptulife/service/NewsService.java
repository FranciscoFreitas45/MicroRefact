package com.dtdhehe.ptulife.service;
 import com.dtdhehe.ptulife.entity.PtuNews;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
public interface NewsService {


public List<PtuNews> queryAllNews()
;

public void delNewsById(String newsId)
;

public PtuNews save(PtuNews ptuNews)
;

public Page<PtuNews> queryNewsByUserId(String userId,String newsTitle,Pageable pageable)
;

public Page<Map<String,Object>> findAllWithHead(Pageable pageable)
;

public PtuNews queryNewsById(String newsId)
;

}