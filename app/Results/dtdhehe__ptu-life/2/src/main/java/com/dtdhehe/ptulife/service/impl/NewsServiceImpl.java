package com.dtdhehe.ptulife.service.impl;
 import com.dtdhehe.ptulife.entity.PtuNews;
import com.dtdhehe.ptulife.repository.PtuNewsRepository;
import com.dtdhehe.ptulife.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
@Service
public class NewsServiceImpl implements NewsService{

@Autowired
 private  PtuNewsRepository ptuNewsRepository;


@Override
public List<PtuNews> queryAllNews(){
    return ptuNewsRepository.findAll();
}


@Override
public void delNewsById(String newsId){
    ptuNewsRepository.deleteById(newsId);
}


@Override
public PtuNews save(PtuNews ptuNews){
    return ptuNewsRepository.save(ptuNews);
}


@Override
public Page<PtuNews> queryNewsByUserId(String userId,String newsTitle,Pageable pageable){
    return ptuNewsRepository.findByUserId(userId, newsTitle, pageable);
}


@Override
public Page<Map<String,Object>> findAllWithHead(Pageable pageable){
    return ptuNewsRepository.findAllWithHead(pageable);
}


@Override
public PtuNews queryNewsById(String newsId){
    return ptuNewsRepository.findById(newsId).get();
}


}