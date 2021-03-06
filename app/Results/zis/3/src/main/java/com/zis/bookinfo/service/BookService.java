package com.zis.bookinfo.service;
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
@Service
public class BookService {

 private  Logger logger;

 private  int MAX_CONTENT_LENGTH;

@Autowired
 private  BookInfoAidDao bookinfoAidDao;

@Autowired
 private  BookInfoDao bookinfoDao;

@Autowired
 private  BookInfoDetailDao bookinfoDetailDao;

@Autowired
 private  YouluSalesDao youluSalesDao;

@Autowired
 private  BookAmountDao bookAmountDao;

@Autowired
 private  ShopItemInfoDao shopItemInfoDao;

@Autowired
 private  DefaultBookMetadataCaptureHandler bookMetadataCapture;

@Autowired
 private  ThreadPoolTaskExecutor taskExecutor;

@Autowired
 private  SimilarityBookAnalysisBO similarityBookAnalysisBO;

@Autowired
 private  SameBookAnalysisBO sameBookAnalysisBO;

@Autowired
 private  RepeatIsbnAnalysisBO repeatIsbnAnalysisBO;

@Autowired
 private  TaobaoCsvDataGenerateBO taobaoCsvDataGenerateBO;


public List<Bookinfo> findBySpecificationAll(Specification<Bookinfo> spec){
    return this.bookinfoDao.findAll(spec);
}


public void saveShopItemForBatch(List<ShopItemInfoDTO> list){
    for (ShopItemInfoDTO shopItemInfoDTO : list) {
        saveShopItem(shopItemInfoDTO);
    }
}


public ShopItemInfo findShopItemByBookIdAndShopName(String shopName,Integer bookId){
    if (StringUtils.isBlank(shopName)) {
        throw new IllegalArgumentException("???????????????shopName????????????");
    }
    if (bookId == null) {
        throw new IllegalArgumentException("???????????????bookId????????????");
    }
    List<ShopItemInfo> list = this.shopItemInfoDao.findByShopNameAndBookId(shopName, bookId);
    if (list == null || list.isEmpty()) {
        return null;
    }
    if (list.size() > 1) {
        throw new RuntimeException("????????????????????????????????????bookId=" + bookId + "shopName=" + shopName);
    }
    return list.get(0);
}


public List<Bookinfo> findBookByISBN(String isbn){
    if (StringUtils.isBlank(isbn)) {
        throw new RuntimeException("isbn????????????");
    }
    return bookinfoDao.findByIsbn(isbn);
// DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
// dc.add(Restrictions.eq("isbn", isbn));
// dc.add(Restrictions.ne("bookStatus", ConstantString.ABANDON));
// return bookinfoDao.findByCriteria(dc);
}


public void updateBook(Bookinfo book,BookinfoDetail detail){
    book.setGmtModify(ZisUtils.getTS());
    bookinfoDao.save(book);
    if (detail != null) {
        detail.setBookid(book.getId());
        detail.setGmtModify(ZisUtils.getTS());
        bookinfoDetailDao.save(detail);
    }
}


public void updateSameBooksToOneGroup(Integer[] ids){
    if (ids == null || ids.length <= 1) {
        logger.error("BookService.addGroupRelation--id??????");
        return;
    }
    List<Bookinfo> booksToBeDeal = new ArrayList<Bookinfo>();
    // ????????????????????????????????????
    for (Integer id : ids) {
        Bookinfo book = bookinfoDao.findOne(id);
        if (book != null) {
            booksToBeDeal.add(book);
        }
    }
    sameBookAnalysisBO.relateToSameBooks(booksToBeDeal);
}


public Page<Bookinfo> getWaitCheckList(Pageable page){
    return this.bookinfoDao.findByBookStatus(BookinfoStatus.WAITCHECK, page);
// List<Bookinfo> list = bookinfoDao
// .findByBookStatus(ConstantString.WAITCHECK);
// return list;
}


public void analysisSimilarityBook(){
    similarityBookAnalysisBO.analysis();
    similarityBookAnalysisBO.afterAnalysis();
}


public Bookinfo saveBookinfoByCaptureFromYouluNet(int id){
    // ???????????????????????????outId=id???????????????????????????????????????
    List<Bookinfo> list = this.bookinfoDao.findByOutId(id);
    if (!list.isEmpty()) {
        return list.get(0);
    }
    // ????????????????????????
    BookMetadata bi = bookMetadataCapture.captureDetailPage(id + "", BookMetadataSource.YOU_LU);
    if (bi == null) {
        return null;
    }
    // ????????????????????????
    Bookinfo book = new Bookinfo();
    book.setOutId(id);
    book.setBookName(bi.getName());
    book.setBookAuthor(bi.getAuthor());
    book.setIsbn(bi.getIsbnCode());
    book.setBookPrice(bi.getPrice());
    book.setBookEdition(bi.getEdition());
    book.setBookStatus(ConstantString.USEFUL);
    book.setBookPublisher(bi.getPublisher());
    book.setPublishDate(bi.getPublishDate());
    // ????????????????????????
    BookinfoDetail detail = buildBookinfoDetail(bi);
    // XXX ???????????????????????????????????????????????????????????????????????????
    // detail.setTaobaoTitle(TextClearUtils.buildTaobaoTitle(book));
    // ??????????????????
    addBook(book, detail);
    return book;
}


public void saveShopItem(ShopItemInfoDTO shopItemInfo){
    if (shopItemInfo == null) {
        throw new IllegalArgumentException("shopItemInfo????????????");
    }
    Integer bookId = shopItemInfo.getBookId();
    if (bookId == null) {
        throw new IllegalArgumentException("??????ID????????????");
    }
    Bookinfo book = this.findNormalBookById(bookId);
    if (book == null) {
        throw new IllegalArgumentException("????????????, bookId=" + bookId);
    }
    ShopItemInfo existItem = this.findShopItemByBookIdAndShopName(shopItemInfo.getShopName(), bookId);
    if (existItem == null) {
        // ?????????????????????????????????
        ShopItemInfo item = new ShopItemInfo();
        item.setBookId(bookId);
        item.setIsbn(book.getIsbn());
        item.setShopName(shopItemInfo.getShopName());
        item.setShopStatus(shopItemInfo.getShopStatus());
        item.setGmtCreate(ZisUtils.getTS());
        item.setGmtModified(ZisUtils.getTS());
        this.shopItemInfoDao.save(item);
        logger.info("create new shopInfoItem, bookId=" + bookId);
    } else {
        if (!existItem.getShopStatus().equals(shopItemInfo.getShopStatus())) {
            // ???????????????????????????????????????????????????
            existItem.setShopStatus(shopItemInfo.getShopStatus());
            existItem.setGmtModified(ZisUtils.getTS());
            this.shopItemInfoDao.save(existItem);
            logger.info("update exist shopInfoItem, bookId=" + bookId);
        }
    }
}


public void run(){
    for (int i = idStart; i < idEnd; i++) {
        try {
            if (operateType.equals("addBookInfo")) {
                saveBookinfoByCaptureFromYouluNet(i);
            } else {
                saveYouluBookSales(i);
            }
        } catch (Exception e) {
            String msg = String.format("?????????????????????????????????, youluId=%s, errorDetail=%s", i, e.getMessage());
            logger.error(msg, e);
        }
    }
}


public void generateTaobaoCsvDataFile(List<BookInfoAndDetailDTO> list,String[] emails){
    taobaoCsvDataGenerateBO.generate(list, emails);
}


public Bookinfo findByIdAndIsbn(Integer id,String isbn){
    return this.bookinfoDao.findByIdAndIsbn(id, isbn);
}


public void updateBookForBatchDelete(Integer[] ids){
    if (ids == null || ids.length == 0) {
        return;
    }
    for (Integer bookId : ids) {
        this.updateBookForDisable(bookId);
    }
}


public Page<Bookinfo> findAll(Pageable pageable){
    return bookinfoDao.findAll(pageable);
}


public Integer countAllBooks(){
    return (int) bookinfoDao.count();
}


public void removeRelateId(int id,String pageType){
    Bookinfo book = bookinfoDao.findOne(id);
    // ????????????
    if (ConstantString.PAGEGROUP.equals(pageType)) {
        boolean isNewEditionRemoved = book.getIsNewEdition();
        String groupId = book.getGroupId();
        // ??????????????????????????????????????????????????????
        book.setIsNewEdition(true);
        book.setGroupId(null);
        this.updateBook(book);
        // ?????????????????????????????????????????????????????????????????????????????????
        if (isNewEditionRemoved) {
            // DetachedCriteria criteria =
            // DetachedCriteria.forClass(Bookinfo.class);
            // criteria.add(Restrictions.eq("groupId", groupId));
            // criteria.add(Restrictions.eq("bookPublisher",
            // book.getBookPublisher()));
            // criteria.add(Restrictions.ne("bookStatus",
            // BookinfoStatus.DISCARD));
            // List<Bookinfo> bookList =
            // bookinfoDao.findByCriteria(criteria);
            List<Bookinfo> bookList = bookinfoDao.findByGroupIdAndPublisher(groupId, book.getBookPublisher());
            this.sameBookAnalysisBO.batchUpdateBooksIsNewEdition(bookList, groupId);
        }
    }
    // ????????????
    if (ConstantString.PAGEGRELATE.equals(pageType)) {
        book.setRelateId(null);
        this.updateBook(book);
    }
}


public void updateBookForCheckOK(Bookinfo book){
    if (book == null) {
        return;
    }
    book.setBookStatus(ConstantString.USEFUL);
    this.updateBook(book);
}


public boolean ifBookInfoExist(Bookinfo book){
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    query.eq("bookName", book.getBookName());
    query.eq("bookAuthor", book.getBookAuthor());
    query.eq("bookEdition", book.getBookEdition());
    query.eq("bookPublisher", book.getBookPublisher());
    query.eq("isbn", book.getIsbn());
    query.eq("bookStatus", BookinfoStatus.NORMAL);
    Specification<Bookinfo> spec = query.getSpecification();
    List<Bookinfo> list = this.bookinfoDao.findAll(spec);
    return list != null && !list.isEmpty();
}


public BookinfoDetail captureBookInfoDetailFromNet(Integer bookId){
    if (bookId == null) {
        throw new IllegalArgumentException("?????????????????????bookId????????????");
    }
    Bookinfo book = this.findBookById(bookId);
    if (book == null) {
        throw new RuntimeException("???????????????bookId=" + bookId);
    }
    BookMetadata meta = null;
    BookinfoDetail detail = this.bookinfoDetailDao.findOne(bookId);
    // ??????outId?????????????????????????????????
    if (book.getOutId() != null) {
        // ??????????????????bookinfoDetail?????????????????????????????????????????????
        String source = (detail == null) ? BookMetadataSource.YOU_LU : detail.getSource();
        meta = bookMetadataCapture.captureDetailPage(book.getOutId() + "", source);
    // XXX ???????????????????????????????????????????????????
    } else // ??????outId?????????????????????
    {
        meta = bookMetadataCapture.captureListPage(book.getIsbn());
    }
    // ?????????????????????????????????????????????
    if (meta == null) {
        return null;
    }
    // ????????????????????????DB??????????????????bookinfoDetail??????????????????
    if (detail == null) {
        detail = buildBookinfoDetail(meta);
        detail.setBookid(book.getId());
        this.bookinfoDetailDao.save(detail);
        logger.info("[????????????] ?????????????????????????????? bookId={}", bookId);
    }
    return detail;
}


public void checkForCreateBook(Bookinfo book){
    if (book == null) {
        throw new IllegalArgumentException("book is null");
    }
    if (StringUtils.isBlank(book.getBookName())) {
        throw new IllegalArgumentException("??????????????????");
    }
    if (book.getBookName().length() > 128) {
        throw new IllegalArgumentException("????????????????????????128?????????");
    }
    if (StringUtils.isBlank(book.getBookAuthor())) {
        throw new IllegalArgumentException("??????????????????");
    }
    if (book.getBookAuthor().length() > 50) {
        throw new IllegalArgumentException("????????????????????????50?????????");
    }
    if (StringUtils.isBlank(book.getBookPublisher())) {
        throw new IllegalArgumentException("?????????????????????");
    }
    if (book.getBookPublisher().length() > 50) {
        throw new IllegalArgumentException("???????????????????????????50?????????");
    }
    if (book.getPublishDate() == null) {
        throw new IllegalArgumentException("???????????????????????????");
    }
    if (StringUtils.isBlank(book.getBookEdition())) {
        throw new IllegalArgumentException("??????????????????");
    }
    if (book.getBookEdition().length() > 100) {
        throw new IllegalArgumentException("????????????????????????100?????????");
    }
    if (StringUtils.isBlank(book.getIsbn())) {
        throw new IllegalArgumentException("?????????????????????");
    }
    if (book.getIsbn().length() > 15) {
        throw new IllegalArgumentException("???????????????????????????15?????????");
    }
    if (book.getBookPrice() == null || book.getBookPrice() <= 0) {
        throw new IllegalArgumentException("???????????????????????????????????????0");
    }
}


public void processSameBooksToOneGroup(){
    sameBookAnalysisBO.analysis();
}


public void updateRelatedBooksToOneGroup(Integer[] ids){
    if (ids == null || ids.length == 0) {
        return;
    }
    String relateId = "";
    Map<Integer, Bookinfo> booksToBeDeal = new HashMap<Integer, Bookinfo>();
    // ????????????????????????????????????
    for (Integer id : ids) {
        Bookinfo book = bookinfoDao.findOne(id);
        if (book == null) {
            // ????????????????????????
            continue;
        }
        // ???????????????????????????ID????????????????????????????????????
        if (book.getRelateId() == null)
            booksToBeDeal.put(book.getId(), book);
        else {
            // ???????????????????????????????????????????????????ID?????????????????????????????????
            relateId = book.getRelateId();
            List<Bookinfo> list = getBooksByRelateId(relateId);
            for (Bookinfo bookinfo : list) {
                booksToBeDeal.put(bookinfo.getId(), bookinfo);
            }
        }
    }
    // ????????????????????????????????????
    if (StringUtils.isBlank(relateId))
        relateId = "r" + ZisUtils.getDateString("yyyyMMddHHmmss") + (int) (Math.random() * 1000);
    for (Bookinfo record : booksToBeDeal.values()) {
        record.setRelateId(relateId);
        record.setGmtModify(ZisUtils.getTS());
        bookinfoDao.save(record);
        logger.info("successfully combined related books, bookId=" + record.getId());
    }
}


public Bookinfo findBookById(int id){
    return bookinfoDao.findOne(id);
}


public BookinfoDetail buildBookinfoDetail(BookMetadata meta){
    BookinfoDetail detail = new BookinfoDetail();
    String catalog = meta.getCatalog();
    if (StringUtils.isNotBlank(catalog) && catalog.length() > MAX_CONTENT_LENGTH) {
        catalog = catalog.substring(0, MAX_CONTENT_LENGTH);
    }
    detail.setCatalog(catalog);
    String summary = meta.getSummary();
    if (StringUtils.isNotBlank(summary) && summary.length() > MAX_CONTENT_LENGTH) {
        summary = summary.substring(0, MAX_CONTENT_LENGTH);
    }
    detail.setSummary(summary);
    detail.setImageUrl(meta.getImageUrl());
    detail.setSource(meta.getSource());
    detail.setOutId(Integer.valueOf(meta.getOutId()));
    detail.setGmtCreate(ZisUtils.getTS());
    detail.setGmtModify(ZisUtils.getTS());
    return detail;
}


public void updateTitleAndCategoryForBatch(List<ShopItemInfoDTO> list){
    if (list == null || list.isEmpty()) {
        return;
    }
    for (ShopItemInfoDTO info : list) {
        BookinfoDetail detail = this.bookinfoDetailDao.findOne(info.getBookId());
        // ??????detail???????????????????????????
        if (detail == null) {
            detail = this.captureBookInfoDetailFromNet(info.getBookId());
        }
        if (detail == null) {
            continue;
        }
        // ????????????????????????ID
        if (StringUtils.isNotBlank(info.getTaobaoTitle())) {
            detail.setTaobaoTitle(info.getTaobaoTitle());
        }
        // ???????????????????????????
        if (info.getTaobaoForbidden() != null) {
            detail.setTaobaoForbidden(info.getTaobaoForbidden());
        }
        if (info.getTaobaoCatagoryId() != null) {
            detail.setTaobaoCatagoryId(info.getTaobaoCatagoryId());
        }
        detail.setGmtModify(ZisUtils.getTS());
        this.bookinfoDetailDao.save(detail);
    }
}


public Integer countWaitingBooks(){
    return bookinfoDao.countWaitingBooks();
}


public void removeAllRelation(List<Bookinfo> list,String operateType){
    for (Bookinfo bookinfo : list) {
        // ??????????????????
        if (ConstantString.PAGEGROUP.equals(operateType)) {
            bookinfo.setGroupId(null);
            bookinfo.setIsNewEdition(true);
            this.updateBook(bookinfo, null);
        } else // ???????????????
        if (ConstantString.PAGEGRELATE.equals(operateType)) {
            bookinfo.setRelateId(null);
            this.updateBook(bookinfo, null);
        }
    }
}


public Page<BookinfoAid> findByCheckLevelAndTotalCountGtOne(Integer checkLevel,Pageable pageable){
    return this.bookinfoAidDao.findByCheckLevelAndTotalCountGtOne(checkLevel, pageable);
}


public int addBook(Bookinfo book,BookinfoDetail detail){
    checkForCreateBook(book);
    // ????????????????????????
    if (ifBookInfoExist(book)) {
        throw new RuntimeException("???????????????");
    }
    // ????????????
    // ????????????????????????
    book.setIsNewEdition(true);
    // ?????????????????????false
    book.setRepeatIsbn(false);
    book.setBookStatus(ConstantString.USEFUL);
    book.setGmtCreate(ZisUtils.getTS());
    book.setGmtModify(ZisUtils.getTS());
    book = bookinfoDao.save(book);
    if (detail != null) {
        detail.setBookid(book.getId());
        bookinfoDetailDao.save(detail);
    }
    logger.info("bookinfo.action.checkBook--??????????????????");
    // ???????????????????????????????????????
    try {
        repeatIsbnAnalysisBO.processOne(book);
        sameBookAnalysisBO.processOne(book);
    } catch (Exception e) {
        // ????????????????????????
        logger.error("??????????????????????????????,bookId=" + book.getId(), e);
    }
    return book.getId();
}


public void updateBookForDisable(Integer bookId){
    if (bookId == null) {
        return;
    }
    // ??????????????????????????????
    List<BookAmount> list = this.bookAmountDao.findByBookId(bookId);
    // select bookId from bookAmount;
    if (list != null && !list.isEmpty()) {
        throw new RuntimeException("??????????????????????????????????????????");
    }
    Bookinfo book = this.bookinfoDao.findOne(bookId);
    if (book == null || ConstantString.ABANDON.equals(book.getBookStatus())) {
        // ???????????????????????????????????????????????????
        return;
    }
    // ??????????????????
    book.setBookStatus(ConstantString.ABANDON);
    this.updateBook(book);
    // ???????????????????????????????????????????????????????????????????????????????????????????????????
    if (StringUtils.isNotBlank(book.getGroupId()) && book.getIsNewEdition()) {
        // List<Bookinfo> booksToBeDeal = new ArrayList<Bookinfo>();
        // booksToBeDeal.add(book);
        // sameBookAnalysisBO.relateToSameBooks(booksToBeDeal);
        // DetachedCriteria criteria =
        // DetachedCriteria.forClass(Bookinfo.class);
        // criteria.add(Restrictions.eq("groupId", book.getGroupId()));
        // criteria.add(Restrictions.eq("bookPublisher",
        // book.getBookPublisher()));
        // criteria.add(Restrictions.ne("bookStatus",
        // BookinfoStatus.DISCARD));
        // List<Bookinfo> bookList = bookinfoDao.findByCriteria(criteria);
        List<Bookinfo> bookList = bookinfoDao.findByGroupIdAndPublisher(book.getGroupId(), book.getBookPublisher());
        this.sameBookAnalysisBO.batchUpdateBooksIsNewEdition(bookList, book.getGroupId());
    }
}


public List<Bookinfo> findBySpecification(Specification<Bookinfo> spec){
    return this.bookinfoDao.findAll(spec);
}


public Specification<Bookinfo> buildBookInfoSpec(String bookName,String bookAuthor,String ISBN){
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    if (!StringUtils.isBlank(bookName)) {
        query.like("bookName", "%" + bookName + "%");
    }
    if (!StringUtils.isBlank(bookAuthor)) {
        query.like("bookAuthor", "%" + bookAuthor + "%");
    }
    if (!StringUtils.isBlank(ISBN)) {
        query.eq("isbn", ISBN);
    }
    query.eq("bookStatus", ConstantString.USEFUL);
    return query.getSpecification();
}


public List<Bookinfo> getBooksByRelateId(String relateId){
    // DetachedCriteria dc = DetachedCriteria.forClass(Bookinfo.class);
    // dc.add(Restrictions.eq("relateId", relateId));
    // dc.add(Restrictions.ne("bookStatus", ConstantString.ABANDON));
    // return bookinfoDao.findByCriteria(dc);
    return bookinfoDao.findByRelateId(relateId);
}


public void saveYouluBookSales(int id){
    BookMetadata bi = bookMetadataCapture.captureDetailPage(id + "", BookMetadataSource.YOU_LU);
    if (bi == null) {
        return;
    }
    // ??????????????????????????????????????????
    List<YouluSales> uSales = this.youluSalesDao.findByOutId(id);
    if (uSales != null && !uSales.isEmpty()) {
        return;
    }
    YouluSales sales = new YouluSales();
    sales.setOutId(id);
    List<Bookinfo> list = this.bookinfoDao.findByOutId(id);
    if (!list.isEmpty()) {
        sales.setBookId(list.get(0).getId());
    }
    sales.setBookPrice(bi.getSalesPrice());
    sales.setStockBalance(bi.getStock());
    sales.setGmtCreate(ZisUtils.getTS());
    sales.setGmtModify(ZisUtils.getTS());
    this.youluSalesDao.save(sales);
}


public Bookinfo findNormalBookById(int id){
    return this.bookinfoDao.findNormalBook(id);
// DetachedCriteria criteria =
// DetachedCriteria.forClass(Bookinfo.class);
// criteria.add(Restrictions.eq("id", id));
// criteria.add(Restrictions.eq("bookStatus", BookinfoStatus.NORMAL));
// List<Bookinfo> list = this.bookinfoDao.findByCriteria(criteria);
// if(list == null || list.isEmpty()) {
// return null;
// }
// if(list.size() > 1) {
// throw new RuntimeException("???????????????bookId="+id+"????????????????????????");
// }
// return list.get(0);
}


public BookInfoSearchResult findAndCaptureBookByISBN(String isbn){
    List<Bookinfo> list = this.findBookByISBN(isbn);
    // ????????????????????????????????????????????????
    if (list != null && !list.isEmpty()) {
        BookInfoSearchResult result = new BookInfoSearchResult();
        result.setSysData(true);
        for (Bookinfo book : list) {
            BookinfoDetail detail = this.findBookInfoDetailByBookId(book.getId());
            result.addBookExist(book, detail);
        }
        return result;
    } else // ???????????????????????????????????????
    {
        BookInfoSearchResult result = new BookInfoSearchResult();
        result.setSysData(false);
        BookMetadata meta = bookMetadataCapture.captureListPage(isbn);
        result.setBookCaptured(meta);
        return result;
    }
}


public BookinfoDetail findBookInfoDetailByBookId(Integer bookId){
    return this.bookinfoDetailDao.findOne(bookId);
}


public List<Bookinfo> findBookInfoByBookNameLike(String bookName){
    return this.bookinfoDao.findByBookNameLike(bookName);
}


public void processOneISBNToMultiBooks(){
    repeatIsbnAnalysisBO.analysis();
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


public int asynchronousCaptureBookInfoFromYouLuNet(int idStart,int idEnd,String operateType){
    Thread task = new Thread(new Runnable() {

        public void run() {
            for (int i = idStart; i < idEnd; i++) {
                try {
                    if (operateType.equals("addBookInfo")) {
                        saveBookinfoByCaptureFromYouluNet(i);
                    } else {
                        saveYouluBookSales(i);
                    }
                } catch (Exception e) {
                    String msg = String.format("?????????????????????????????????, youluId=%s, errorDetail=%s", i, e.getMessage());
                    logger.error(msg, e);
                }
            }
        }
    });
    taskExecutor.execute(task);
    return taskExecutor.getActiveCount();
}


}