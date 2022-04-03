package com.zis.bookinfo.repository;
 import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.bean.BookinfoStatus;
public interface BookInfoDao extends PagingAndSortingRepository<Bookinfo, Integer>, JpaSpecificationExecutor<Bookinfo>, JpaRepository<Bookinfo, Integer>{


@Query("from Bookinfo where bookStatus = '" + BookinfoStatus.NORMAL + "' and id = :id")
public Bookinfo findNormalBook(Integer bookId)
;

@Query("select count(*) from Bookinfo where bookStatus = '" + BookinfoStatus.WAITCHECK + "'")
public Integer countWaitingBooks()
;

public Page<Bookinfo> findByBookStatus(String status,Pageable page)
;

public List<Bookinfo> findByBookNameLike(String bookName)
;

@Query("from Bookinfo where bookStatus = '" + BookinfoStatus.NORMAL + "' and groupId = :groupId")
public List<Bookinfo> findByGroupId(String groupId)
;

@Query("select max(id) from Bookinfo")
public Integer findMaxBookId()
;

@Query("from Bookinfo where bookStatus != '" + BookinfoStatus.DISCARD + "' and isbn = :isbn")
public List<Bookinfo> findByIsbn(String isbn)
;

@Query("from Bookinfo where bookStatus != '" + BookinfoStatus.DISCARD + "' and bookName = :bookName and bookAuthor = :author and bookPublisher = :publisher")
public List<Bookinfo> findByBookNameAuthorPublisher(String bookName,String author,String publisher)
;

@Query("from Bookinfo where bookStatus != '" + BookinfoStatus.DISCARD + "' and groupId = :groupId and bookPublisher = :publisher")
public List<Bookinfo> findByGroupIdAndPublisher(String groupId,String publisher)
;

public List<Bookinfo> findByOutId(Integer outId)
;

@Query("from Bookinfo where bookStatus = '" + BookinfoStatus.NORMAL + "' and relateId = :relateId")
public List<Bookinfo> findByRelateId(String relateId)
;

public Bookinfo findByIdAndIsbn(Integer id,String isbn)
;

public void setBookName(Integer id,String bookName);

public void setBookEdition(Integer id,String bookEdition);

}