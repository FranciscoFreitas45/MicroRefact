package com.zis.DTO;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoAid;
import com.zis.bookinfo.bean.BookinfoDetail;
import com.zis.bookinfo.bean.BookinfoStatus;
import com.zis.bookinfo.bean.ShopItemInfo;
import com.zis.bookinfo.bean.YouluSales;
import com.zis.bookinfo.bo.RepeatIsbnAnalysisBO;
import com.zis.bookinfo.bo.SameBookAnalysisBO;
import com.zis.bookinfo.bo.SimilarityBookAnalysisBO;
import com.zis.bookinfo.bo.TaobaoCsvDataGenerateBO;
import com.zis.bookinfo.dto.BookInfoAndDetailDTO;
import com.zis.bookinfo.dto.BookInfoSearchResult;
import com.zis.bookinfo.dto.ShopItemInfoDTO;
import com.zis.bookinfo.repository.BookInfoAidDao;
import com.zis.bookinfo.repository.BookInfoDao;
import com.zis.bookinfo.repository.BookInfoDetailDao;
import com.zis.bookinfo.repository.ShopItemInfoDao;
import com.zis.bookinfo.repository.YouluSalesDao;
import com.zis.bookinfo.util.BookMetadata;
import com.zis.bookinfo.util.BookMetadataSource;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.capture.DefaultBookMetadataCaptureHandler;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.util.ZisUtils;
import com.zis.requirement.bean.BookAmount;
import com.zis.requirement.repository.BookAmountDao;
import com.zis.Interface.BookAmountDao;
import com.zis.Interface.DefaultBookMetadataCaptureHandler;
import com.zis.DTO.BookMetadata;
import com.zis.DTO.ShopItemInfoDTO;
import com.zis.DTO.QueryUtil;
import com.zis.DTO.BookAmountDao;
public class BookService {

 private  Logger logger;

 private  int MAX_CONTENT_LENGTH;

 private  BookInfoAidDao bookinfoAidDao;

 private  BookInfoDao bookinfoDao;

 private  BookInfoDetailDao bookinfoDetailDao;

 private  YouluSalesDao youluSalesDao;

 private  BookAmountDao bookAmountDao;

 private  ShopItemInfoDao shopItemInfoDao;

 private  DefaultBookMetadataCaptureHandler bookMetadataCapture;

 private  ThreadPoolTaskExecutor taskExecutor;

 private  SimilarityBookAnalysisBO similarityBookAnalysisBO;

 private  SameBookAnalysisBO sameBookAnalysisBO;

 private  RepeatIsbnAnalysisBO repeatIsbnAnalysisBO;

 private  TaobaoCsvDataGenerateBO taobaoCsvDataGenerateBO;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://3";


public Page<Bookinfo> getWaitCheckList(Pageable page){
    return this.bookinfoDao.findByBookStatus(BookinfoStatus.WAITCHECK, page);
// List<Bookinfo> list = bookinfoDao
// .findByBookStatus(ConstantString.WAITCHECK);
// return list;
}


public List<Bookinfo> getBooksByRelateId(String relateId){
    // DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
    // dc.add(Restrictions.eq("relateId", relateId));
    // dc.add(Restrictions.ne("bookStatus", ConstantString.ABANDON));
    // return bookinfoDao.findByCriteria(dc);
    return bookinfoDao.findByRelateId(relateId);
}


public String getBookInfo(String bookName,String bookAuthor,String ISBN){
    // DetachedCriteria criteria = this.buildBookInfoCriteria(bookName,
    // bookAuthor, ISBN);
    Specification<Bookinfo> spec = this.buildBookInfoSpec(bookName, bookAuthor, ISBN);
    Pageable page = new PageRequest(0, 20);
    List<Bookinfo> list = this.findBySpecification(spec, page).getContent();
    // List<Bookinfo> list = bookinfoDao
    // .findByCriteriaLimitCount(criteria, 20);
    return JSON.toJSONString(list);
}


public List<Bookinfo> getBooksByGroupId(String groupId){
    // DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
    // dc.add(Restrictions.eq("groupId", groupId));
    // dc.add(Restrictions.ne("bookStatus", ConstantString.ABANDON));
    return bookinfoDao.findByGroupId(groupId);
}


public Bookinfo findByIdAndIsbn(Integer id,String isbn){
    return this.bookinfoDao.findByIdAndIsbn(id, isbn);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByIdAndIsbn"))

.queryParam("id",id)
.queryParam("isbn",isbn)
;
Bookinfo aux = restTemplate.getForObject(builder.toUriString(),Bookinfo.class);
return aux;
}


public List<Bookinfo> findBookByISBN(String isbn){
    if (StringUtils.isBlank(isbn)) {
        throw new RuntimeException("isbn不能为空");
    }
    return bookinfoDao.findByIsbn(isbn);
// DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
// dc.add(Restrictions.eq("isbn", isbn));
// dc.add(Restrictions.ne("bookStatus", ConstantString.ABANDON));
// return bookinfoDao.findByCriteria(dc);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBookByISBN"))

.queryParam("isbn",isbn)
;
List<Bookinfo> aux = restTemplate.getForObject(builder.toUriString(),List<Bookinfo>.class);
return aux;
}


public Bookinfo findBookById(int id){
    return bookinfoDao.findOne(id);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBookById"))

.queryParam("id",id)
;
Bookinfo aux = restTemplate.getForObject(builder.toUriString(),Bookinfo.class);
return aux;
}


public List<Bookinfo> findBookInfoByBookNameLike(String bookName){
    return this.bookinfoDao.findByBookNameLike(bookName);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findBookInfoByBookNameLike"))

.queryParam("bookName",bookName)
;
List<Bookinfo> aux = restTemplate.getForObject(builder.toUriString(),List<Bookinfo>.class);
return aux;
}


}