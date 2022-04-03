package com.dtdhehe.ptulife.Interface;
public interface NewsService {

   public Page<PtuNews> queryNewsByUserId(String userId,String newsTitle,Pageable pageable);
   public void delNewsById(String newsId);
}