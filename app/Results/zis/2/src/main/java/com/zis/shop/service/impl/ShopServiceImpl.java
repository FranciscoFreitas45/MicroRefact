package com.zis.shop.service.impl;
 import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.mail.internet.AddressException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSON;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.excel.ExcelImporter;
import com.zis.common.excel.FileImporter;
import com.zis.common.mail.MailSenderFactory;
import com.zis.common.mail.SimpleMailSender;
import com.zis.common.util.TextClearUtils;
import com.zis.common.util.ZisUtils;
import com.zis.shop.api.ApiTransfer;
import com.zis.shop.api.impl.ApiTransferFactory;
import com.zis.shop.bean.Company;
import com.zis.shop.bean.DownloadItemLog;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.bean.ShopItemMapping.ShopItemMappingSystemStatus;
import com.zis.shop.bo.ShopBo;
import com.zis.shop.dto.ApplyRefundDTO;
import com.zis.shop.dto.CheckOutIdDTO;
import com.zis.shop.dto.CreateOrderFailDTO;
import com.zis.shop.dto.LogisticsOfflineSendDTO;
import com.zis.shop.dto.SaveOrUpdateCompanyDto;
import com.zis.shop.dto.SaveOrUpdateShopDto;
import com.zis.shop.dto.ShopDownLoadFailDTO;
import com.zis.shop.dto.TaoBaoImportExcelDto;
import com.zis.shop.repository.CompanyDao;
import com.zis.shop.repository.DownloadItemLogDao;
import com.zis.shop.repository.ShopInfoDao;
import com.zis.shop.repository.ShopItemMappingDao;
import com.zis.shop.service.ShopService;
import com.zis.shop.util.ShopUtil;
import com.zis.storage.entity.StorageRepoInfo;
import com.zis.storage.service.StorageService;
import com.zis.trade.dto.CreateTradeOrderDTO;
import com.zis.trade.dto.CreateTradeOrderDTO.SubOrder;
import com.zis.trade.dto.ExpressNumberDTO;
import com.zis.trade.entity.Order;
import com.zis.trade.entity.OrderOuter;
import com.zis.trade.repository.OrderDao;
import com.zis.trade.repository.OrderOuterDao;
import com.zis.trade.service.OrderService;
import com.zis.Interface.CompanyDao;
import com.zis.Interface.BookService;
import com.zis.Interface.OrderService;
import com.zis.Interface.OrderDao;
import com.zis.Interface.OrderOuterDao;
import com.zis.Interface.StorageService;
import com.zis.DTO.CheckOutIdDTO;
import com.zis.DTO.SaveOrUpdateCompanyDto;
import com.zis.DTO.CompanyDao;
import com.zis.DTO.OrderService;
import com.zis.DTO.SubOrder;
import com.zis.DTO.FileImporter;
import com.zis.DTO.ExpressNumberDTO;
import com.zis.DTO.Order;
import com.zis.DTO.OrderOuterDao;
import com.zis.DTO.StorageService;
import com.zis.DTO.OrderService;
import com.zis.DTO.CreateTradeOrderDTO;
import com.zis.DTO.OrderService;
import com.zis.DTO.OrderService;
@Service
public class ShopServiceImpl implements ShopService{

 private  String MAPPING_SUCCESS;

 private  String MAPPING_FAIL;

 private  String MAPPING_WAIT;

 private  String MAPPING_DELETE;

 private  String MAPPING_PROCESSING;

 private  String[] shopItemMappingStatus;

 final  String NORMAL;

 final  String DELETE;

 final  Long PAGE_SIZE;

 final  String FOR_SHELVED;

 final  String SOLD_OUT;

 private  Logger logger;

 private  String[] shaoweiEmail;

@Autowired
 private  ShopInfoDao shopInfoDao;

@Autowired
 private  CompanyDao companyDao;

@Autowired
 private  ShopBo ShopBo;

@Autowired
 private  ShopItemMappingDao shopItemMappingDao;

@Autowired
 private  ThreadPoolTaskExecutor taskExecutor;

@Autowired
 private  ApiTransferFactory factory;

@Autowired
 private  DownloadItemLogDao downloadItemLogDao;

@Autowired
 private  BookService bookService;

@Autowired
 private  OrderService orderService;

@Autowired
 private  OrderDao orderDao;

@Autowired
 private  OrderOuterDao orderOuterDao;

@Autowired
 private  StorageService storageService;

 private  SimpleMailSender mailSender;


@Override
public List<ShopDownLoadFailDTO> prcessDownLoadMappingData(List<ShopItemMapping> mappingList,ShopInfo shop){
    List<ShopDownLoadFailDTO> failList = new ArrayList<ShopDownLoadFailDTO>();
    for (ShopItemMapping s : mappingList) {
        // 检查
        CheckOutIdDTO dto = checkOutId(s);
        if (dto.getIsSuccess()) {
            // 保存
            saveDownloadMapping(s, dto.getBook());
        } else {
            ShopDownLoadFailDTO failDto = new ShopDownLoadFailDTO();
            failDto.setOuterId(s.getItemOutNum());
            failDto.setTitle(s.getTitle());
            failDto.setFailReason(dto.getFailMsg());
            failList.add(failDto);
        }
    }
    return failList;
}


@Override
@Transactional
public void updateShop(SaveOrUpdateShopDto dto){
    if (dto.getShopId() == null) {
        throw new RuntimeException("shopId为空");
    }
    ShopInfo shop = this.shopInfoDao.findByCompanyIdAndShopId(dto.getCompanyId(), dto.getShopId());
    if (shop == null) {
        throw new RuntimeException("错误的shopId " + dto.getShopId());
    }
    BeanUtils.copyProperties(dto, shop);
    Double double1 = Double.parseDouble(dto.getDiscount());
    shop.setDiscount(double1);
    shop.setUpdateTime(new Date());
    this.shopInfoDao.save(shop);
}


@Override
public List<Company> queryAllCompany(){
    return (List<Company>) this.companyDao.findAll();
}


@Override
public Page<ShopItemMapping> queryShopItemMapping(Integer shopId,String status,String isbn,Pageable page){
    List<String> statusList = getStatusList();
    if (!statusList.contains(status)) {
        status = MAPPING_WAIT;
    }
    Page<ShopItemMapping> pageList = null;
    if (StringUtils.isNotBlank(isbn)) {
        List<Bookinfo> list = this.bookService.findBookByISBN(isbn);
        List<Integer> bookIds = new ArrayList<Integer>();
        for (Bookinfo b : list) {
            bookIds.add(b.getId());
        }
        pageList = this.shopItemMappingDao.findByShopIdAndSystemStatusAndBookIdIn(shopId, status, bookIds, page);
    } else {
        pageList = this.shopItemMappingDao.findByShopIdAndSystemStatus(shopId, status, page);
    }
    return pageList;
}


@Override
public ShopInfo findShopByShopIdAndCompanyId(Integer companyId,Integer shopId){
    return this.shopInfoDao.findByCompanyIdAndShopId(companyId, shopId);
}


@Override
@Transactional
public void updateCompany(SaveOrUpdateCompanyDto dto){
    if (dto.getCompanyId() == null) {
        throw new RuntimeException("companyId为空");
    }
    Company company = this.companyDao.findByCompanyId(dto.getCompanyId());
    if (company != null) {
        BeanUtils.copyProperties(company, dto);
        company.setUpdateTime(new Date());
        this.companyDao.save(company);
        ShopUtil.clearAllCached();
    } else {
        String message = "修改用户出错，请联系管理员" + "修改的公司为:[" + company + "]";
        logger.error(message);
        throw new RuntimeException(message);
    }
}


public void run(){
    List<ShopDownLoadFailDTO> failList = prcessDownLoadMappingData(mappingList, shop);
    if (!CollectionUtils.isEmpty(failList)) {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>" + shop.getShopName() + "</b><p/>\n");
        for (ShopDownLoadFailDTO dto : failList) {
            String str = String.format("%s  %s  %s <p/>\n", dto.getTitle(), dto.getOuterId(), dto.getFailReason());
            sb.append(str);
        }
        String[] email = { shop.getEmails() };
        sendFailEmail(email, sb.toString(), shop);
    }
}


public String[] splitOuterId(String outerId){
    String[] isbnAndBookId = outerId.split("-");
    return isbnAndBookId;
}


@Override
public void queryApplyRefundForShopIdAndDate(Integer shopId,Date startTime,Date endTime){
    ShopInfo shop = this.shopInfoDao.findOne(shopId);
    if (shop == null) {
        throw new RuntimeException("店铺不存在");
    }
    ApiTransfer api = factory.getInstance(shop.getpName());
    List<ApplyRefundDTO> list = api.queryApplyRefundForDate(shop, startTime, endTime);
    if (CollectionUtils.isEmpty(list)) {
        return;
    }
    for (ApplyRefundDTO dto : list) {
        try {
            this.orderService.applyRefund(shop.getShopId(), dto.getOutOrderNumber(), 0, dto.getApplyTime(), dto.getRefundMemo());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage() + " 订单号 " + dto.getOutOrderNumber());
        }
    }
}


@Override
public void initShopMapping(Integer bookId,ShopInfo shop){
    ShopItemMapping mapping = this.shopItemMappingDao.findByShopIdAndBookId(shop.getShopId(), bookId);
    if (mapping == null) {
        Bookinfo book = this.bookService.findNormalBookById(bookId);
        saveShopMappingStatusWait(book, shop);
    }
}


@Override
public Company findCompanyOne(Integer companyId){
    return this.companyDao.findByCompanyId(companyId);
}


@Override
@Transactional
public void saveCompany(SaveOrUpdateCompanyDto dto){
    if (dto.getCompanyId() != null) {
        throw new RuntimeException("新增公司，companyId应为空");
    }
    Company verifyCompany = this.companyDao.findByCompanyName(dto.getCompanyName());
    // 如果存在记录，防止重复提交
    if (verifyCompany != null) {
        return;
    }
    Company company = new Company();
    company.setCreateTime(new Date());
    company.setStatus(NORMAL);
    BeanUtils.copyProperties(dto, company);
    company.setAddress(dto.getAddress());
    company.setUpdateTime(new Date());
    this.companyDao.save(company);
    ShopUtil.clearAllCached();
}


public DownloadItemLog addDownLoadLog(Integer shopId,String desc){
    DownloadItemLog log = new DownloadItemLog();
    log.setCreateTime(new Date());
    log.setUpdateTime(new Date());
    log.setDescription(desc);
    log.setShopId(shopId);
    return log;
}


@Override
@Transactional
public Integer failAddItem2Shop(Integer mappingId,ShopInfo shop){
    List<Integer> list = new ArrayList<Integer>();
    list.add(mappingId);
    List<ShopItemMapping> mapping = this.shopItemMappingDao.findByShopIdAndSystemStatusAndIdIn(shop.getShopId(), ShopItemMappingSystemStatus.FAIL.getValue(), list);
    for (ShopItemMapping s : mapping) {
        s.setSystemStatus(ShopItemMappingSystemStatus.PROCESSING.getValue());
    }
    this.shopItemMappingDao.save(mapping);
    addProcessingItems(list, shop);
    return 1;
}


public String subCheckFileFormat(List<String> factHeader){
    String NUM_IID = "宝贝ID";
    String TITLE = "商品标题";
    String OUTER_ID = "商家编码";
    if (!factHeader.get(0).equals(NUM_IID)) {
        return "格式错误，第一列必须是:" + NUM_IID;
    }
    if (!factHeader.get(1).equals(TITLE)) {
        return "格式错误，第二列必须是:" + TITLE;
    }
    if (!factHeader.get(2).equals(OUTER_ID)) {
        return "格式错误，第三列必须是:" + OUTER_ID;
    }
    return null;
}


public void addFailDownLoadLog(Integer shopId,String desc){
    DownloadItemLog log = addDownLoadLog(shopId, desc);
    log.setStatus(DownloadItemLog.DownloadItemLogStatus.FAIL.getValue());
    this.downloadItemLogDao.save(log);
}


public void addErrorDownLoadLog(Integer shopId,String desc){
    DownloadItemLog log = addDownLoadLog(shopId, desc);
    log.setStatus(DownloadItemLog.DownloadItemLogStatus.ERROR.getValue());
    this.downloadItemLogDao.save(log);
}


public CheckOutIdDTO checkOutId(SubOrder so){
    CheckOutIdDTO dto = new CheckOutIdDTO();
    String msg = "";
    String[] splitOuterId = splitOuterId(so.getItemOutNum());
    // 无商家编码
    if (splitOuterId.length == 0 || splitOuterId.length > 2) {
        msg = "商家编码格式有误或不存在";
        logger.error(msg);
        dto.setFailMsg(msg);
        dto.setIsSuccess(false);
        return dto;
    }
    // 商家编码符合规范，进行检查是否数据库中有对应数据，确保商家编码非乱填
    if (splitOuterId.length == 2) {
        String bookId = splitOuterId[1];
        String isbn = splitOuterId[0];
        // 如果bookId不为数字加入错误文件
        if (!StringUtils.isNumeric(bookId)) {
            msg = "bookId只能为数字" + bookId;
            logger.error(msg);
            dto.setFailMsg(msg);
            dto.setIsSuccess(false);
            return dto;
        }
        Bookinfo book = this.bookService.findByIdAndIsbn(Integer.parseInt(bookId), isbn);
        // 对应商家编码在数据库中无数据，证明商家编码为乱填，加入错误待生成邮件内容中
        if (book == null) {
            msg = "商家编码有误";
            logger.error(msg);
            dto.setFailMsg(msg);
            dto.setIsSuccess(false);
            return dto;
        }
        dto.setIsSuccess(true);
        dto.setBook(book);
        return dto;
    }
    // 只有外部编码只有isbn的情况
    String isbn = splitOuterId[0];
    List<Bookinfo> list1 = this.bookService.findBookByISBN(isbn);
    if (CollectionUtils.isEmpty(list1)) {
        msg = "无此图书";
        logger.error(msg);
        dto.setFailMsg(msg);
        dto.setIsSuccess(false);
        return dto;
    } else if (list1.size() > 1) {
        msg = "一码多书";
        logger.error(msg);
        dto.setFailMsg(msg);
        dto.setIsSuccess(false);
        return dto;
    } else {
        dto.setIsSuccess(true);
        dto.setBook(list1.get(0));
        return dto;
    }
}


public void saveDownloadMapping(ShopItemMapping mapping,Bookinfo book){
    mapping.setBookId(book.getId());
    // 查询是否有映射
    ShopItemMapping shopMapping = this.shopItemMappingDao.findByShopIdAndBookId(mapping.getShopId(), mapping.getBookId());
    if (shopMapping == null) {
        mapping.setSystemStatus(ShopItemMappingSystemStatus.WAIT.getValue());
        mapping.setUpdateTime(new Date());
        mapping.setCreateTime(new Date());
        mapping.setItemOutNum(mapping.getItemOutNum());
        this.shopItemMappingDao.save(mapping);
    } else {
        if (shopMapping.getpItemId() == null && ShopItemMappingSystemStatus.SUCCESS.getValue().equals(shopMapping.getSystemStatus())) {
            shopMapping.setpItemId(mapping.getpItemId());
            this.shopItemMappingDao.save(shopMapping);
        }
    // 
    // //商家编码更新
    // if
    // (ShopItemMappingSystemStatus.SUCCESS.getValue().equals(shopMapping.getSystemStatus())
    // && !shopMapping.getItemOutNum().equals(mapping.getItemOutNum()))
    // {
    // shopMapping.setItemOutNum(mapping.getItemOutNum());
    // this.shopItemMappingDao.save(shopMapping);
    // }
    }
}


@Override
public ShopInfo findShopById(Integer shopId){
    return shopInfoDao.findOne(shopId);
}


public void sendFailEmail(String[] mail,String msg,ShopInfo shop){
    try {
        mailSender.send(mail, "店铺错误数据" + ZisUtils.getDateString("yyyy年MM月dd天HH时mm分"), msg);
    } catch (AddressException e) {
        addErrorDownLoadLog(shop.getShopId(), e.getMessage());
        logger.error(e.getMessage(), e);
        try {
            mailSender.send(shaoweiEmail, shop.getShopId() + "店铺错误数据" + ZisUtils.getDateString("yyyy年MM月dd天HH时mm分"), msg);
        } catch (Exception e2) {
            addErrorDownLoadLog(shop.getShopId(), e2.getMessage());
            logger.error(e2.getMessage(), e2);
        }
    } catch (Exception e1) {
        addErrorDownLoadLog(shop.getShopId(), e1.getMessage());
        logger.error(e1.getMessage(), e1);
    }
}


@SuppressWarnings("unchecked")
public List<CreateTradeOrderDTO> buildCreateTradeOrderDTOList(ShopInfo shop,List<CreateTradeOrderDTO> dtoList){
    Map<String, Object> map = prcessCreateOrderData(dtoList, shop);
    List<CreateOrderFailDTO> failList = (List<CreateOrderFailDTO>) map.get("failList");
    if (!CollectionUtils.isEmpty(failList)) {
        StringBuilder sb = new StringBuilder();
        sb.append("<b>" + shop.getShopName() + "</b><p/>\n");
        for (CreateOrderFailDTO dto : failList) {
            String str = String.format("%s  %s  %s  %s  %s  %s %s  %s  %s  %s  %s  %s<p/>\n", "此订单未被创建到zis  网店订单号", dto.getOutOrderNumber(), "收件人姓名", dto.getReceiverName(), "收件人电话", dto.getReceiverPhone(), "宝贝Id", dto.getItemId(), "商家编码", dto.getItemOutNum(), "错误原因", dto.getFailReason());
            sb.append(str);
        }
        String[] email = { shop.getEmails() };
        sendFailEmail(email, sb.toString(), shop);
    }
    return (List<CreateTradeOrderDTO>) map.get("orderList");
}


@Override
public List<String> getStatusList(){
    List<String> list = Arrays.asList(shopItemMappingStatus);
    return list;
}


@Override
public List<ShopItemMapping> taobaoExeclToMapping(InputStream input,ShopInfo shop){
    // 设置模板文件，用于检验导入文件是否合法
    Integer headerRownums = 1;
    try {
        // 初始化导入器
        FileImporter<TaoBaoImportExcelDto> im = new ExcelImporter<TaoBaoImportExcelDto>(input, null);
        im.setHeaderRowNums(headerRownums);
        // 检验导入文件是否合法
        String errMsg = im.validate();
        if (StringUtils.isNotBlank(errMsg)) {
            logger.error(errMsg);
            addErrorDownLoadLog(shop.getShopId(), errMsg);
            throw new RuntimeException("导入文件非法");
        }
        String subCheck = subCheckFileFormat(im.loadFactHeader());
        if (StringUtils.isNotBlank(subCheck)) {
            logger.error(errMsg);
            addErrorDownLoadLog(shop.getShopId(), errMsg);
            throw new RuntimeException(subCheck);
        }
        // 解析文件并入库
        Map<String, Integer> propMapping = initPropMapping();
        TaoBaoImportExcelDto instance = new TaoBaoImportExcelDto();
        List<TaoBaoImportExcelDto> list = im.parse(instance, propMapping);
        if (list.isEmpty()) {
            logger.error(errMsg);
            addErrorDownLoadLog(shop.getShopId(), errMsg);
            throw new RuntimeException("导入失败，文件为空");
        }
        List<ShopItemMapping> mappingList = new ArrayList<ShopItemMapping>();
        StringBuilder sb = new StringBuilder();
        for (TaoBaoImportExcelDto t : list) {
            ShopItemMapping s = null;
            try {
                s = getShopItemMapping(t, shop);
                mappingList.add(s);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                addFailDownLoadLog(shop.getShopId(), e.getMessage());
                String msg = String.format("%s  宝贝ID：%s  %s <p/>\n", t.getTitle(), t.getNumIid(), "宝贝信息有误");
                sb.append(msg);
            }
        }
        String[] mail = { shop.getEmails() };
        if (StringUtils.isNotBlank(sb.toString())) {
            sendFailEmail(mail, sb.toString(), shop);
        }
        return mappingList;
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        addErrorDownLoadLog(shop.getShopId(), e.getMessage());
        throw new RuntimeException(e.getMessage(), e);
    }
}


@Override
public void logisticsOfflineSend(List<ExpressNumberDTO> list){
    for (ExpressNumberDTO dto : list) {
        Order order = this.orderDao.findOne(dto.getOrderId());
        List<OrderOuter> outerList = this.orderOuterDao.findByOrderGroupNumber(order.getOrderGroupNumber());
        if (!CollectionUtils.isEmpty(list)) {
            for (OrderOuter o : outerList) {
                if (StringUtils.isNumeric(o.getOutOrderNumber())) {
                    ApiTransfer api = factory.getInstance(o.getpName());
                    ShopInfo shop = this.shopInfoDao.findOne(o.getShopId());
                    LogisticsOfflineSendDTO lDTO = new LogisticsOfflineSendDTO();
                    lDTO.setTid(Long.parseLong(o.getOutOrderNumber()));
                    lDTO.setExpressCompany(dto.getExpressCompany());
                    lDTO.setExpressNumber(dto.getExpressNumber());
                    try {
                        boolean result = api.logisticsOfflineSend(shop, lDTO);
                        if (!result) {
                            String msg = String.format("%s %s", "调用失败", JSON.toJSON(lDTO));
                            throw new RuntimeException(msg);
                        }
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                }
            }
        }
    }
}


@Override
public Page<Company> queryCompany(String companyName,String contacts,Pageable page){
    if (!StringUtils.isBlank(companyName)) {
        return this.companyDao.findByLikeCompanyName(companyName.trim(), page);
    } else if (!StringUtils.isBlank(contacts)) {
        return this.companyDao.findByContacts(contacts.trim(), page);
    } else {
        return this.companyDao.findAllCompany(page);
    }
}


@Override
@Transactional
public void saveShop(SaveOrUpdateShopDto dto){
    if (dto.getShopId() != null) {
        throw new RuntimeException("新增店铺，shopId应为空");
    }
    ShopInfo verifyShop = this.shopInfoDao.findByShopNameAndPNameAndCompanyId(dto.getShopName(), dto.getpName(), dto.getCompanyId());
    // 如果存在记录，防止重复提交
    if (verifyShop != null) {
        return;
    }
    ShopInfo shop = new ShopInfo();
    BeanUtils.copyProperties(dto, shop);
    shop.setDiscount(Double.parseDouble(dto.getDiscount()));
    shop.setUpdateTime(new Date());
    shop.setStatus(NORMAL);
    shop.setCreateTime(new Date());
    this.shopInfoDao.save(shop);
}


@Override
public List<ShopInfo> queryAllShop(){
    return (List<ShopInfo>) this.shopInfoDao.findAll();
}


public void saveShopMappingStatusWait(Bookinfo book,ShopInfo shop){
    ShopItemMapping newMapping = new ShopItemMapping();
    newMapping.setBookId(book.getId());
    newMapping.setCreateTime(new Date());
    newMapping.setItemOutNum(String.format("%s-%s", book.getIsbn(), book.getId()));
    newMapping.setShopId(shop.getShopId());
    newMapping.setSystemStatus(ShopItemMappingSystemStatus.WAIT.getValue());
    newMapping.setTitle(TextClearUtils.buildTitleFormType(shop.getpName(), book));
    newMapping.setUpdateTime(new Date());
    this.shopItemMappingDao.save(newMapping);
}


@Override
public void asynchronousPrcessDownLoadMappingDataAndSendEmail(List<ShopItemMapping> mappingList,ShopInfo shop){
    Thread task = new Thread(new Runnable() {

        public void run() {
            List<ShopDownLoadFailDTO> failList = prcessDownLoadMappingData(mappingList, shop);
            if (!CollectionUtils.isEmpty(failList)) {
                StringBuilder sb = new StringBuilder();
                sb.append("<b>" + shop.getShopName() + "</b><p/>\n");
                for (ShopDownLoadFailDTO dto : failList) {
                    String str = String.format("%s  %s  %s <p/>\n", dto.getTitle(), dto.getOuterId(), dto.getFailReason());
                    sb.append(str);
                }
                String[] email = { shop.getEmails() };
                sendFailEmail(email, sb.toString(), shop);
            }
        }
    });
    taskExecutor.execute(task);
}


@Override
@Transactional
public Integer addItem2Shop(Integer mappingId,ShopInfo shop){
    List<Integer> list = new ArrayList<Integer>();
    list.add(mappingId);
    List<ShopItemMapping> mapping = this.shopItemMappingDao.findByShopIdAndSystemStatusAndIdIn(shop.getShopId(), ShopItemMappingSystemStatus.WAIT.getValue(), list);
    for (ShopItemMapping s : mapping) {
        s.setSystemStatus(ShopItemMappingSystemStatus.PROCESSING.getValue());
    }
    this.shopItemMappingDao.save(mapping);
    addProcessingItems(list, shop);
    return 1;
}


@Override
public List<ShopInfo> findCompanyShop(Integer companyId){
    List<ShopInfo> list = this.shopInfoDao.findByCompanyId(companyId);
    return list;
}


@Override
public String deleteShop(Integer companyId,Integer shopId){
    ShopInfo shop = this.shopInfoDao.findByCompanyIdAndShopId(companyId, shopId);
    if (shop != null) {
        shop.setStatus(DELETE);
        this.shopInfoDao.save(shop);
        return shop.getShopName();
    } else {
        throw new RuntimeException("您需要删除的店铺不存在，请联系管理员 店铺ID：" + shopId);
    }
}


@Override
public void stockChangeToShopUPdateItem(Integer companyId,Integer bookId,Integer amount){
    List<ShopInfo> shopList = this.shopInfoDao.findByCompanyId(companyId);
    for (ShopInfo shop : shopList) {
        try {
            ShopItemMapping mapping = this.shopItemMappingDao.findByShopIdAndBookId(shop.getShopId(), bookId);
            if (mapping != null) {
                this.ShopBo.stockChangeToUpdateOrAddItems(mapping, shop, amount);
            } else {
                Bookinfo book = this.bookService.findNormalBookById(bookId);
                saveShopMappingStatusWait(book, shop);
            }
        } catch (Exception e) {
            throw new RuntimeException(shop.getShopName() + e.getMessage());
        }
    }
}


public Map<String,Integer> initPropMapping(){
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("numIid", 0);
    map.put("title", 1);
    map.put("outerId", 2);
    return map;
}


@Override
public void createOrderForShopIdAndDate(Integer shopId,Date startTime,Date endTime){
    ShopInfo shop = this.shopInfoDao.findOne(shopId);
    if (shop == null) {
        throw new RuntimeException("店铺不存在");
    }
    List<StorageRepoInfo> storageList = this.storageService.findStorageRepoInfoByCompanyId(shop.getCompanyId());
    if (storageList.isEmpty()) {
        throw new RuntimeException("仓库不存在");
    }
    ApiTransfer api = factory.getInstance(shop.getpName());
    List<CreateTradeOrderDTO> list = api.queryTradeForDate(shop, startTime, endTime);
    if (CollectionUtils.isEmpty(list)) {
        return;
    }
    List<CreateTradeOrderDTO> newList = buildCreateTradeOrderDTOList(shop, list);
    for (CreateTradeOrderDTO dto : newList) {
        Order order = this.orderService.createOrder(dto);
        // 支付
        this.orderService.payOrder(order.getId(), dto.getOrderMoney(), 0);
        // 分配仓库
        this.orderService.arrangeOrderToRepo(order.getId(), 0, storageList.get(0).getRepoId());
    }
}


public void abstractAddAllProcessingItems(ShopInfo shop){
    this.ShopBo.abstractAddAllProcessingItems(shop);
}


@Override
public ShopInfo verifyShopId(Integer shopId){
    // 无店铺
    if (shopId == null) {
        List<ShopInfo> shopList = this.findCompanyShop(ShopUtil.getCompanyId());
        // 如果没有shopId 默认选择第一个店铺
        if (shopList.size() > 0) {
            return shopList.get(0);
        } else {
            throw new RuntimeException("您没有店铺,请新建店铺");
        }
    } else {
        // 有店铺
        ShopInfo shop = this.findShopByShopIdAndCompanyId(ShopUtil.getCompanyId(), shopId);
        if (shop == null) {
            throw new RuntimeException("店铺信息异常，请联系管理员");
        }
        return shop;
    }
}


public ShopItemMapping getShopItemMapping(TaoBaoImportExcelDto dto,ShopInfo shop){
    ShopItemMapping s = new ShopItemMapping();
    s.setItemOutNum(dto.getOuterId());
    s.setpItemId(Long.parseLong(dto.getNumIid().trim()));
    s.setTitle(dto.getTitle());
    s.setShopId(shop.getShopId());
    s.setCreateTime(new Date());
    s.setUpdateTime(new Date());
    return s;
}


public Map<String,Object> prcessCreateOrderData(List<CreateTradeOrderDTO> createList,ShopInfo shop){
    List<CreateTradeOrderDTO> orderList = new ArrayList<CreateTradeOrderDTO>();
    List<CreateOrderFailDTO> failList = new ArrayList<CreateOrderFailDTO>();
    // 订单错误下标
    Set<Integer> successIndex = new HashSet<>();
    for (int i = 0; i < createList.size(); i++) {
        // 如果有错误删除整个订单
        for (int j = 0; j < createList.get(i).getSubOrders().size(); j++) {
            // 检查
            CheckOutIdDTO dto = checkOutId(createList.get(i).getSubOrders().get(j));
            if (dto.getIsSuccess()) {
                // 回填信息
                createList.get(i).getSubOrders().get(j).setSkuId(dto.getBook().getId());
                createList.get(i).getSubOrders().get(j).setItemName(dto.getBook().getBookName());
                successIndex.add(i);
            } else {
                // 生成失败
                CreateOrderFailDTO failDto = new CreateOrderFailDTO();
                BeanUtils.copyProperties(createList.get(i), failDto);
                failDto.setItemId(createList.get(i).getSubOrders().get(j).getItemId().toString());
                failDto.setItemOutNum(createList.get(i).getSubOrders().get(j).getItemOutNum());
                failDto.setFailReason(dto.getFailMsg());
                failList.add(failDto);
            }
        }
    }
    // 移除错误订单数据
    if (!successIndex.isEmpty()) {
        for (Integer i : successIndex) {
            int j = i;
            orderList.add(createList.get(j));
        }
    }
    Map<String, Object> map = new HashMap<>();
    map.put("failList", failList);
    map.put("orderList", orderList);
    return map;
}


public void addProcessingItems(List<Integer> mappingIds,ShopInfo shop){
    this.ShopBo.addProcessingItems(mappingIds, shop);
}


}