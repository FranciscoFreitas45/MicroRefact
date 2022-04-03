package com.uec.imonitor.news.dao;
 import org.springframework.data.jpa.repository.JpaRepository;
import com.uec.imonitor.news.bean.NewsContentEntity;
public interface INewsContentJPARepository extends JpaRepository<NewsContentEntity, String>{


}