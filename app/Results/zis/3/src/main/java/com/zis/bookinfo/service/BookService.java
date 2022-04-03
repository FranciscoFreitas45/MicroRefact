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
        throw new IllegalArgumentException("参数非法，shopName不能为空");
    }
    if (bookId == null) {
        throw new IllegalArgumentException("参数非法，bookId不能为空");
    }
    List<ShopItemInfo> list = this.shopItemInfoDao.findByShopNameAndBookId(shopName, bookId);
    if (list == null || list.isEmpty()) {
        return null;
    }
    if (list.size() > 1) {
        throw new RuntimeException("数据非法，存在重复记录，bookId=" + bookId + "shopName=" + shopName);
    }
    return list.get(0);
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
        logger.error("BookService.addGroupRelation--id为空");
        return;
    }
    List<Bookinfo> booksToBeDeal = new ArrayList<Bookinfo>();
    // 所有相同图书都整理到一起
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
    // 检查系统中是否存在outId=id的记录，如果有，则直接返回
    List<Bookinfo> list = this.bookinfoDao.findByOutId(id);
    if (!list.isEmpty()) {
        return list.get(0);
    }
    // 从有路网抓取数据
    BookMetadata bi = bookMetadataCapture.captureDetailPage(id + "", BookMetadataSource.YOU_LU);
    if (bi == null) {
        return null;
    }
    // 构造图书基本信息
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
    // 构造图书附加信息
    BookinfoDetail detail = buildBookinfoDetail(bi);
    // XXX 保存阶段直接生成标题或许不太好，请考虑采用其他策略
    // detail.setTaobaoTitle(TextClearUtils.buildTaobaoTitle(book));
    // 保存到数据库
    addBook(book, detail);
    return book;
}


public void saveShopItem(ShopItemInfoDTO shopItemInfo){
    if (shopItemInfo == null) {
        throw new IllegalArgumentException("shopItemInfo不能为空");
    }
    Integer bookId = shopItemInfo.getBookId();
    if (bookId == null) {
        throw new IllegalArgumentException("图书ID不能为空");
    }
    Bookinfo book = this.findNormalBookById(bookId);
    if (book == null) {
        throw new IllegalArgumentException("无此图书, bookId=" + bookId);
    }
    ShopItemInfo existItem = this.findShopItemByBookIdAndShopName(shopItemInfo.getShopName(), bookId);
    if (existItem == null) {
        // 如果不存在记录，则新增
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
            // 如果存在且状态发生变化，则进行更新
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
            String msg = String.format("有路网添加图书信息失败, youluId=%s, errorDetail=%s", i, e.getMessage());
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
    // 不同版本
    if (ConstantString.PAGEGROUP.equals(pageType)) {
        boolean isNewEditionRemoved = book.getIsNewEdition();
        String groupId = book.getGroupId();
        // 从分组中移除该记录，并且标记为最新版
        book.setIsNewEdition(true);
        book.setGroupId(null);
        this.updateBook(book);
        // 如果移除的记录是最新版，那么所属分组需要重新标记最新版
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
    // 关联图书
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
        throw new IllegalArgumentException("采集数据失败，bookId不能为空");
    }
    Bookinfo book = this.findBookById(bookId);
    if (book == null) {
        throw new RuntimeException("无此图书，bookId=" + bookId);
    }
    BookMetadata meta = null;
    BookinfoDetail detail = this.bookinfoDetailDao.findOne(bookId);
    // 如果outId不为空，则去原网站采集
    if (book.getOutId() != null) {
        // 兼容早期没有bookinfoDetail的记录，基本上都是有路网采集的
        String source = (detail == null) ? BookMetadataSource.YOU_LU : detail.getSource();
        meta = bookMetadataCapture.captureDetailPage(book.getOutId() + "", source);
    // XXX 原站点采集，图片质量不一定是最优的
    } else // 没有outId，则从网上采集
    {
        meta = bookMetadataCapture.captureListPage(book.getIsbn());
    }
    // 如果没有采集到数据，则直接返回
    if (meta == null) {
        return null;
    }
    // 采集到数据，并且DB中没有对应的bookinfoDetail，则予以录入
    if (detail == null) {
        detail = buildBookinfoDetail(meta);
        detail.setBookid(book.getId());
        this.bookinfoDetailDao.save(detail);
        logger.info("[数据采集] 保存图书详情到数据库 bookId={}", bookId);
    }
    return detail;
}


public void checkForCreateBook(Bookinfo book){
    if (book == null) {
        throw new IllegalArgumentException("book is null");
    }
    if (StringUtils.isBlank(book.getBookName())) {
        throw new IllegalArgumentException("书名不能为空");
    }
    if (book.getBookName().length() > 128) {
        throw new IllegalArgumentException("书名长度不能超过128个字符");
    }
    if (StringUtils.isBlank(book.getBookAuthor())) {
        throw new IllegalArgumentException("作者不能为空");
    }
    if (book.getBookAuthor().length() > 50) {
        throw new IllegalArgumentException("作者长度不能超过50个字符");
    }
    if (StringUtils.isBlank(book.getBookPublisher())) {
        throw new IllegalArgumentException("出版社不能为空");
    }
    if (book.getBookPublisher().length() > 50) {
        throw new IllegalArgumentException("出版社长度不能超过50个字符");
    }
    if (book.getPublishDate() == null) {
        throw new IllegalArgumentException("出版社日期不能为空");
    }
    if (StringUtils.isBlank(book.getBookEdition())) {
        throw new IllegalArgumentException("版次不能为空");
    }
    if (book.getBookEdition().length() > 100) {
        throw new IllegalArgumentException("版次长度不能超过100个字符");
    }
    if (StringUtils.isBlank(book.getIsbn())) {
        throw new IllegalArgumentException("条形码不能为空");
    }
    if (book.getIsbn().length() > 15) {
        throw new IllegalArgumentException("条形码长度不能超过15个字符");
    }
    if (book.getBookPrice() == null || book.getBookPrice() <= 0) {
        throw new IllegalArgumentException("图书价格不能为空且必须大于0");
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
    // 所有关联图书都整理到一起
    for (Integer id : ids) {
        Bookinfo book = bookinfoDao.findOne(id);
        if (book == null) {
            // 跳过不存在的记录
            continue;
        }
        // 没有相关图书，该书ID直接加入到集合中等待处理
        if (book.getRelateId() == null)
            booksToBeDeal.put(book.getId(), book);
        else {
            // 如果有相关图书，则将所有相关图书的ID都加入到集合中等待处理
            relateId = book.getRelateId();
            List<Bookinfo> list = getBooksByRelateId(relateId);
            for (Bookinfo bookinfo : list) {
                booksToBeDeal.put(bookinfo.getId(), bookinfo);
            }
        }
    }
    // 所有相关图书都关联到一起
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
        // 没有detail记录，立刻生成一条
        if (detail == null) {
            detail = this.captureBookInfoDetailFromNet(info.getBookId());
        }
        if (detail == null) {
            continue;
        }
        // 更新标题或者类目ID
        if (StringUtils.isNotBlank(info.getTaobaoTitle())) {
            detail.setTaobaoTitle(info.getTaobaoTitle());
        }
        // 更新淘宝黑名单标记
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
        // 不同版本分组
        if (ConstantString.PAGEGROUP.equals(operateType)) {
            bookinfo.setGroupId(null);
            bookinfo.setIsNewEdition(true);
            this.updateBook(bookinfo, null);
        } else // 相关性分组
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
    // 检查图书是否存在
    if (ifBookInfoExist(book)) {
        throw new RuntimeException("图书已存在");
    }
    // 新增图书
    // 默认设置为最新版
    book.setIsNewEdition(true);
    // 一码多书设置为false
    book.setRepeatIsbn(false);
    book.setBookStatus(ConstantString.USEFUL);
    book.setGmtCreate(ZisUtils.getTS());
    book.setGmtModify(ZisUtils.getTS());
    book = bookinfoDao.save(book);
    if (detail != null) {
        detail.setBookid(book.getId());
        bookinfoDetailDao.save(detail);
    }
    logger.info("bookinfo.action.checkBook--添加图书成功");
    // 处理一码多书和不同版本图书
    try {
        repeatIsbnAnalysisBO.processOne(book);
        sameBookAnalysisBO.processOne(book);
    } catch (Exception e) {
        // 出错不影响主流程
        logger.error("处理一码多书过程出错,bookId=" + book.getId(), e);
    }
    return book.getId();
}


public void updateBookForDisable(Integer bookId){
    if (bookId == null) {
        return;
    }
    // 检查图书是否已被使用
    List<BookAmount> list = this.bookAmountDao.findByBookId(bookId);
    // select bookId from bookAmount;
    if (list != null && !list.isEmpty()) {
        throw new RuntimeException("该书已经被使用，请联系管理员");
    }
    Bookinfo book = this.bookinfoDao.findOne(bookId);
    if (book == null || ConstantString.ABANDON.equals(book.getBookStatus())) {
        // 图书不存在或者已废弃，不做任何处理
        return;
    }
    // 更新图书状态
    book.setBookStatus(ConstantString.ABANDON);
    this.updateBook(book);
    // 如果图书存在不同版次且被删除的记录是最新版，则需要重新调整分组策略
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
    // 检查系统中是否已经录入该记录
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
// throw new RuntimeException("数据错误，bookId="+id+"对应两条以上记录");
// }
// return list.get(0);
}


public BookInfoSearchResult findAndCaptureBookByISBN(String isbn){
    List<Bookinfo> list = this.findBookByISBN(isbn);
    // 系统中已经存在相关记录，直接保存
    if (list != null && !list.isEmpty()) {
        BookInfoSearchResult result = new BookInfoSearchResult();
        result.setSysData(true);
        for (Bookinfo book : list) {
            BookinfoDetail detail = this.findBookInfoDetailByBookId(book.getId());
            result.addBookExist(book, detail);
        }
        return result;
    } else // 系统中没有记录，从网络采集
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
                    String msg = String.format("有路网添加图书信息失败, youluId=%s, errorDetail=%s", i, e.getMessage());
                    logger.error(msg, e);
                }
            }
        }
    });
    taskExecutor.execute(task);
    return taskExecutor.getActiveCount();
}


}