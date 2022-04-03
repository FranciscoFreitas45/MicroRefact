package com.zis.storage.controller;
 import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.alibaba.fastjson.JSONObject;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.bookinfo.util.ConstantString;
import com.zis.common.controllertemplate.ViewTips;
import com.zis.common.mvc.ext.QueryUtil;
import com.zis.common.mvc.ext.Token;
import com.zis.common.mvc.ext.WebHelper;
import com.zis.shiro.bean.User;
import com.zis.shiro.service.SysService;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.service.impl.ShopServiceImpl;
import com.zis.shop.util.ShopUtil;
import com.zis.storage.dto.CreateOrderDTO.CreateOrderDetail;
import com.zis.storage.dto.FastTakeGoodsDTO;
import com.zis.storage.dto.OrderDetailDto;
import com.zis.storage.dto.StockDTO;
import com.zis.storage.dto.StorageIoBatchDTO;
import com.zis.storage.dto.StorageIoDetailView;
import com.zis.storage.dto.StorageIoDetailViewDTO;
import com.zis.storage.dto.StorageOrderDto;
import com.zis.storage.dto.StorageOrderViewDTO;
import com.zis.storage.dto.StoragePositionView;
import com.zis.storage.dto.StorageProductDTO;
import com.zis.storage.dto.StorageProductViewDTO;
import com.zis.storage.entity.StorageIoBatch;
import com.zis.storage.entity.StorageIoBatch.BizType;
import com.zis.storage.entity.StorageIoBatch.Status;
import com.zis.storage.entity.StorageIoDetail;
import com.zis.storage.entity.StorageIoDetail.DetailStatus;
import com.zis.storage.entity.StorageIoDetail.IoType;
import com.zis.storage.entity.StorageOrder;
import com.zis.storage.entity.StorageOrder.TradeStatus;
import com.zis.storage.entity.StoragePosStock;
import com.zis.storage.entity.StoragePosition;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.entity.StorageRepoInfo;
import com.zis.storage.repository.StorageIoBatchDao;
import com.zis.storage.repository.StorageIoDetailDao;
import com.zis.storage.repository.StorageOrderDao;
import com.zis.storage.repository.StoragePosStockDao;
import com.zis.storage.repository.StoragePositionDao;
import com.zis.storage.repository.StorageRepoInfoDao;
import com.zis.storage.service.StorageService;
import com.zis.storage.util.StorageUtil;
import com.zis.trade.service.OrderService;
import com.zis.Interface.BookService;
import com.zis.Interface.SysService;
import com.zis.Interface.ShopServiceImpl;
import com.zis.Interface.OrderService;
import com.zis.DTO.CreateOrderDetail;
import com.zis.DTO.OrderService;
import com.zis.DTO.QueryUtil;
import com.zis.DTO.OrderService;
import com.zis.DTO.BookService;
import com.zis.DTO.OrderService;
import com.zis.DTO.SysService;
import com.zis.DTO.User;
import com.zis.DTO.ShopServiceImpl;
@Controller
@RequestMapping(value = "/storage")
public class StorageController implements ViewTips{

@Autowired
 private  StorageRepoInfoDao storageRepoInfoDao;

@Autowired
 private  StorageOrderDao storageOrderDao;

@Autowired
 private  StorageService storageService;

@Autowired
 private  StorageIoBatchDao storageIoBatchDao;

@Autowired
 private  StorageIoDetailDao storageIoDetailDao;

@Autowired
 private  StoragePositionDao storagePositionDao;

@Autowired
 private  StoragePosStockDao storagePosStockDao;

@Autowired
 private  BookService bookService;

@Autowired
 private  SysService sysService;

@Autowired
 private  ShopServiceImpl shopService;

@Autowired
 private  OrderService orderService;

 private  Integer DEFAULT_SIZE;

 private  String[] allStatus;

 private  String[] INPUT;

 private  String[] OUTPUT;

 private  String VIEW_URL_PRODUCT_LIST;

 private  String VIEW_URL_STOCK_DIST;

 private  String VIEW_URL_STOCK_ALTER;


@RequestMapping(value = "/gotoFastInwarehouse")
public String gotoFastInwarehouse(ModelMap map){
    return "storage/inwarehouse/fast-inwarehouse";
}


public List<OrderDetailDto> getOrderDetailDtoList(String json){
    List<OrderDetailDto> oList = new ArrayList<OrderDetailDto>();
    List<CreateOrderDetail> detailList = JSONObject.parseArray(json, CreateOrderDetail.class);
    for (CreateOrderDetail c : detailList) {
        OrderDetailDto dto = new OrderDetailDto();
        dto.setBookAmount(c.getAmount());
        dto.setBookTitle(buildTitle(c.getSkuId()));
        oList.add(dto);
    }
    return oList;
}


public void setQueryConditionQueryPositionSkus(ModelMap context,Integer posId){
    StringBuilder condition = new StringBuilder();
    if (posId != null) {
        condition.append("posId=" + posId + "&");
    }
    context.put("queryCondition", condition.toString());
}


@RequestMapping(value = "/cancelOrder")
public String cancelOrder(Integer[] orderId,ModelMap map){
    try {
        for (Integer i : orderId) {
            this.storageService.cancelOrder(i);
        }
        map.put("actionMessage", "订单取消成功");
        return "forward:/storage/queryStorageOrder";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/lackPart")
public String lackPart(ModelMap map,Integer ioDetailId,Integer actualAmt){
    try {
        StorageIoDetail io = this.orderService.lackPart(ioDetailId, StorageUtil.getUserId(), actualAmt);
        if (io == null) {
            map.put("actionMessage", "本批次取货完成，请点击对应批次进行完成");
            return "forward:/storage/querytTakeGoods";
        }
        map.put("ioDetail", io);
        map.put("title", buildTakeGoodsTitle(io.getSkuId()));
        return "storage/send/take-goods";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/nextTakeGoods")
public String nextTakeGoods(ModelMap map,Integer ioDetailId){
    try {
        StorageIoDetail io = this.storageService.pickupDoneAndLockNext(ioDetailId, StorageUtil.getUserId());
        if (io == null) {
            map.put("actionMessage", "本批次取货完成，请点击对应批次进行完成");
            return "forward:/storage/querytTakeGoods";
        }
        map.put("ioDetail", io);
        map.put("title", buildTakeGoodsTitle(io.getSkuId()));
        return "storage/send/take-goods";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/takeGoods")
public String takeGoods(ModelMap map,Integer batchId){
    try {
        StorageIoDetail io = this.storageService.pickupLock(batchId, StorageUtil.getUserId());
        if (io == null) {
            map.put("actionMessage", "本批次取货完成，请点击对应批次进行完成");
            return "forward:/storage/querytTakeGoods";
        }
        map.put("ioDetail", io);
        map.put("title", buildTakeGoodsTitle(io.getSkuId()));
        return "storage/send/take-goods";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/findPosition")
public String findPosition(String label,Boolean ifLike,ModelMap map,HttpServletRequest request){
    try {
        Pageable page = WebHelper.buildPageRequest(request);
        QueryUtil<StoragePosition> query = new QueryUtil<StoragePosition>();
        if (StringUtils.isNotBlank(label)) {
            if (ifLike != null && ifLike == true) {
                query.eq("label", label);
            } else {
                query.like("label", "%" + label + "%");
            }
        }
        query.eq("posStatus", StoragePosition.PosStatus.AVAILABLE.getValue());
        query.eq("repoId", StorageUtil.getRepoId());
        Page<StoragePosition> pList = this.storagePositionDao.findAll(query.getSpecification(), page);
        if (!pList.getContent().isEmpty()) {
            List<StoragePosition> list = pList.getContent();
            List<StoragePositionView> vList = getStoragePositionViewList(list);
            map.put("pList", vList);
            setQueryConditionQueryPosition(map, label, ifLike);
            map.put("page", page.getPageNumber() + 1);
            if (pList.hasPrevious()) {
                map.put("prePage", page.previousOrFirst().getPageNumber());
            }
            if (pList.hasNext()) {
                map.put("nextPage", page.next().getPageNumber());
            }
        } else {
            map.put("notResult", "未找到需要的结果");
        }
        return "storage/send/position";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/lackAll")
public String lackAll(ModelMap map,Integer ioDetailId){
    try {
        StorageIoDetail io = this.orderService.lackAll(ioDetailId, StorageUtil.getUserId());
        if (io == null) {
            map.put("actionMessage", "本批次取货完成，请点击对应批次进行完成");
            return "forward:/storage/querytTakeGoods";
        }
        map.put("ioDetail", io);
        map.put("title", buildTakeGoodsTitle(io.getSkuId()));
        return "storage/send/take-goods";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "error";
    }
}


public void setQueryConditionQueryPosition(ModelMap context,String label,Boolean ifLike){
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(label)) {
        condition.append("label=" + label + "&");
    }
    if (ifLike != null && ifLike == true) {
        condition.append("ifLike=true&");
    }
    context.put("queryCondition", condition.toString());
}


public void setQueryConditionToPage(String outTradeNo,String buyerName,String status,ModelMap map){
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(outTradeNo)) {
        condition.append("outTradeNo=" + outTradeNo + "&");
    }
    if (StringUtils.isNotBlank(buyerName)) {
        condition.append("buyerName=" + buyerName + "&");
    }
    if (StringUtils.isNotBlank(status)) {
        condition.append("status=" + status + "&");
    }
    condition.append("size=" + DEFAULT_SIZE + "&");
    map.put("queryCondition", condition.toString());
}


@RequestMapping(value = "/gotoInwarehouse")
public String gotoInwarehouse(ModelMap map){
    return "storage/inwarehouse/inwarehouse";
}


@RequestMapping(value = "/querytTakeGoods")
public String querytTakeGoods(HttpServletRequest request,ModelMap map){
    try {
        Pageable page = WebHelper.buildPageRequest(request);
        QueryUtil<StorageIoBatch> query = new QueryUtil<StorageIoBatch>();
        // 获取配货状态
        query.eq("bizType", BizType.OUT_BATCH.getValue());
        query.eq("status", Status.CREATED.getValue());
        query.eq("repoId", StorageUtil.getRepoId());
        Page<StorageIoBatch> orderList = this.storageIoBatchDao.findAll(query.getSpecification(), page);
        if (!orderList.getContent().isEmpty()) {
            List<StorageIoBatch> list = orderList.getContent();
            List<StorageIoBatchDTO> listShow = new ArrayList<StorageIoBatchDTO>();
            // 将状态转换为中文
            for (StorageIoBatch s : list) {
                StorageIoBatchDTO io = new StorageIoBatchDTO();
                BeanUtils.copyProperties(s, io);
                io.setZhCnStatus(Status.getStatus(s.getStatus()).getDisplay());
                listShow.add(io);
            }
            map.put("batchList", listShow);
            map.put("page", page.getPageNumber() + 1);
            if (orderList.hasPrevious()) {
                map.put("prePage", page.previousOrFirst().getPageNumber());
            }
            if (orderList.hasNext()) {
                map.put("nextPage", page.next().getPageNumber());
            }
        } else {
            map.put("notResult", "未找到需要的结果");
        }
        return "storage/send/take-goods-list";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/gotoUpdatePosition")
@Token(generate = true)
public String gotoUpdatePosition(Integer posId,ModelMap map){
    try {
        StoragePosition position = this.storageService.findByPosIdAndRepoId(posId, StorageUtil.getRepoId());
        if (position == null) {
            map.put(ACTION_ERROR, "请勿非法操作");
            return "error";
        }
        map.put("pos", position);
        return "storage/save/update-position";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


public List<StoragePositionView> getStoragePositionViewList(List<StoragePosition> list){
    List<StoragePositionView> plist = new ArrayList<StoragePositionView>();
    for (StoragePosition s : list) {
        StoragePositionView v = new StoragePositionView();
        BeanUtils.copyProperties(s, v);
        List<StoragePosStock> sList = this.storagePosStockDao.findByPosId(s.getPosId());
        Integer i = 0;
        for (StoragePosStock sps : sList) {
            i += (sps.getTotalAmt() - sps.getOccupyAmt());
        }
        v.setLastAmount(i);
        plist.add(v);
    }
    return plist;
}


@RequestMapping("stock/listStockAlter")
public String queryStorageIoDetails(Integer productId,Integer posId,ModelMap model,HttpServletRequest request){
    // 参数检验
    if (productId == null) {
        model.put(ACTION_ERROR, "未选择任何图书");
        return VIEW_URL_STOCK_ALTER;
    }
    // 查询库存变动
    Pageable page = WebHelper.buildPageRequest(request);
    Page<StorageIoDetail> rs = storageService.findStorageIoDetailByProductIdAndPosId(productId, posId, page);
    if (CollectionUtils.isEmpty(rs.getContent())) {
        model.put(ACTION_MESSAGE, "没有相关记录");
        return VIEW_URL_STOCK_ALTER;
    }
    // 查询图书基本信息
    Integer bookId = rs.getContent().get(0).getSkuId();
    Bookinfo book = this.bookService.findBookById(bookId);
    if (book == null) {
        model.put(ACTION_MESSAGE, "无此图书");
        return VIEW_URL_STOCK_ALTER;
    }
    // 数据写入页面
    List<StorageIoDetailViewDTO> list = new ArrayList<StorageIoDetailViewDTO>();
    for (StorageIoDetail detail : rs.getContent()) {
        StorageIoDetailViewDTO v = new StorageIoDetailViewDTO();
        BeanUtils.copyProperties(detail, v);
        v.setOperatorName(getRealName(detail.getOperator()));
        v.init();
        list.add(v);
    }
    // TODO 分页查询未实现
    model.put("list", list);
    model.put("book", book);
    return VIEW_URL_STOCK_ALTER;
}


public String buildTitle(Integer bookId){
    Bookinfo book = this.bookService.findBookById(bookId);
    String isbn = book.getIsbn();
    String last4Isbn = isbn.substring(isbn.length() - 4, isbn.length());
    String bookName = book.getBookName();
    if (bookName.length() > 20) {
        bookName = book.getBookName().substring(0, 20);
    }
    String title = String.format("%s %s", bookName, last4Isbn);
    return title;
}


public List<StorageOrderDto> buildOList(List<StorageOrder> list){
    List<StorageOrderDto> dList = new ArrayList<StorageOrderDto>();
    for (StorageOrder s1 : list) {
        StorageOrderDto dto = new StorageOrderDto();
        // 将内容转换为中文
        StorageOrderViewDTO s = new StorageOrderViewDTO();
        BeanUtils.copyProperties(s1, s);
        if (StorageOrder.TradeStatus.SENT.getValue().equals(s.getTradeStatus())) {
            s.setZhCnStatus(StorageOrder.TradeStatus.SENT.getDisplay());
        } else if (StorageOrder.TradeStatus.CANCEL.getValue().equals(s.getTradeStatus())) {
            s.setZhCnStatus(StorageOrder.TradeStatus.CANCEL.getDisplay());
        } else if (StorageOrder.TradeStatus.CREATED.getValue().equals(s.getTradeStatus())) {
            s.setZhCnStatus(StorageOrder.TradeStatus.CREATED.getDisplay());
        } else if (StorageOrder.TradeStatus.PROCESSING.getValue().equals(s.getTradeStatus())) {
            s.setZhCnStatus(StorageOrder.TradeStatus.PROCESSING.getDisplay());
        }
        dto.setStorageOrder(s);
        dto.setoList(getOrderDetailDtoList(s.getOrderDetail()));
        dList.add(dto);
    }
    return dList;
}


@RequestMapping(value = "/gotoSavePosition")
@Token(generate = true)
public String gotoSavePosition(ModelMap map){
    return "storage/save/save-position";
}


@RequestMapping(value = "/gotoManualTakeGoods")
@Token(generate = true)
public String gotoManualTakeGoods(ModelMap map){
    return "storage/send/manual-take-goods";
}


public void saveStorageRepoInfo(String stockName,Integer companyId){
    StorageRepoInfo info = new StorageRepoInfo();
    info.setName(stockName);
    info.setOwnerId(companyId);
    info.setStatus(StorageRepoInfo.Status.AVAILABLE.getValue());
    info.setGmtCreate(new Date());
    info.setGmtModify(new Date());
    this.storageRepoInfoDao.save(info);
    ShopUtil.clearAllCached();
}


@RequestMapping(value = "/updatePosition")
@Token(checking = true)
public String updatePosition(Integer posId,String label,ModelMap map){
    try {
        if (StringUtils.isBlank(label)) {
            map.put(ACTION_ERROR, "label不能为空");
            return "forward:/storage/gotoUpdatePosition";
        }
        if (posId == null) {
            map.put(ACTION_ERROR, "posId不能为空");
            return "forward:/storage/gotoUpdatePosition";
        }
        StoragePosition storagePosition = this.storageService.findByLabelAndRepoId(label, StorageUtil.getRepoId());
        if (storagePosition != null) {
            map.put(ACTION_ERROR, "此库位已存在");
            return "forward:/storage/gotoUpdatePosition";
        } else {
            this.storageService.updatePosition(posId, label, StorageUtil.getRepoId());
            map.put(ACTION_MESSAGE, label + "库位修改成功");
            return "forward:/storage/findPosition";
        }
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


public Bookinfo getBook(Integer bookId){
    return this.bookService.findBookById(bookId);
}


public void setQueryCondition(String type,String status,ModelMap map){
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(type)) {
        condition.append("bizType=" + type + "&");
    }
    if (StringUtils.isNotBlank(status)) {
        condition.append("status=" + status + "&");
    }
    map.put("queryCondition", condition.toString());
}


@RequestMapping(value = "/manualTakeGoods")
@Token(checking = true)
public String manualTakeGoods(FastTakeGoodsDTO dto,BindingResult br,ModelMap map,RedirectAttributes attributes){
    if (br.hasErrors()) {
        return "forward:/storage/gotoManualTakeGoods";
    }
    try {
        this.storageService.directSend(StorageUtil.getRepoId(), dto.getSkuId(), dto.getAmount(), dto.getPosLabel(), StorageUtil.getUserId());
        String message = String.format("%s库位 %s 出库%s本", dto.getPosLabel(), dto.getBookinfoStr(), dto.getAmount());
        attributes.addFlashAttribute(ACTION_MESSAGE, message);
        return "redirect:/storage/gotoManualTakeGoods";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/storage/gotoManualTakeGoods";
    }
}


@RequiresPermissions(value = "shiro:shiro")
@RequestMapping(value = "/gotoSaveStorageRepoInfo")
@Token(generate = true)
public String gotoSaveStorageRepoInfo(ModelMap map,Integer companyId){
    if (companyId == null) {
        map.put("actionError", "公司Id有误");
        return "error";
    }
    map.put("companyId", companyId);
    return "storage/save/save-stock";
}


public void pageInfo(HttpServletRequest request,ModelMap map,String outTradeNo,String buyerName,String status){
    Pageable page = WebHelper.buildPageRequest(request);
    Specification<StorageOrder> spec = buildSpec(status, outTradeNo, buyerName);
    Page<StorageOrder> orderList = this.storageOrderDao.findAll(spec, page);
    if (!orderList.getContent().isEmpty()) {
        List<StorageOrder> list = orderList.getContent();
        List<StorageOrderDto> oList = buildOList(list);
        map.put("orderList", oList);
        map.put("page", page.getPageNumber() + 1);
        setQueryConditionToPage(outTradeNo, buyerName, status, map);
        if (orderList.hasPrevious()) {
            map.put("prePage", page.previousOrFirst().getPageNumber());
        }
        if (orderList.hasNext()) {
            map.put("nextPage", page.next().getPageNumber());
        }
    } else {
        map.put("notResult", "未找到需要的结果");
    }
}


@RequestMapping(value = "/finishTakeGoods")
public String finishTakeGoods(ModelMap map,Integer batchId){
    try {
        this.orderService.finishSend(StorageUtil.getRepoId(), batchId, StorageUtil.getUserId());
        map.put("actionMessage", "批次" + batchId + "操作成功");
        return "forward:/storage/querytTakeGoods";
    } catch (Exception e) {
        map.put("actionError", e.getMessage());
        return "forward:/storage/querytTakeGoods";
    }
}


@RequestMapping(value = "/fastTakeGoods")
@Token(checking = true)
public String fastTakeGoods(FastTakeGoodsDTO dto,BindingResult br,ModelMap map,RedirectAttributes attributes){
    if (br.hasErrors()) {
        return "forward:/storage/gotoFastTakeGoods";
    }
    try {
        this.storageService.directSend(StorageUtil.getRepoId(), dto.getSkuId(), dto.getAmount(), dto.getPosLabel(), StorageUtil.getUserId());
        String message = String.format("%s库位 %s 出库%s本", dto.getPosLabel(), dto.getBookinfoStr(), dto.getAmount());
        // map.put(ACTION_MESSAGE, message);
        attributes.addFlashAttribute(ACTION_MESSAGE, message);
        return "redirect:/storage/gotoFastTakeGoods";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/storage/gotoFastTakeGoods";
    }
}


public String getRealName(Integer userId){
    if (userId != null) {
        User user = this.sysService.findtUserById(userId);
        if (user != null) {
            return user.getRealName();
        } else {
            return "";
        }
    } else {
        return "";
    }
}


@RequestMapping(value = "/findPositionSkus")
public String findPositionSkus(Integer posId,ModelMap map,HttpServletRequest request){
    try {
        if (posId == null) {
            map.put(ACTION_ERROR, "posId不能为空");
            return "error";
        }
        StoragePosition pos = this.storageService.findByPosIdAndRepoId(posId, StorageUtil.getRepoId());
        if (pos == null) {
            map.put(ACTION_ERROR, "posId非法");
            return "error";
        }
        Pageable page = WebHelper.buildPageRequest(request);
        Page<StoragePosStock> pList = this.storageService.findByPosId(pos.getPosId(), page);
        if (!pList.getContent().isEmpty()) {
            List<StoragePosStock> list = pList.getContent();
            List<StorageProduct> proList = this.storageService.findByPosStockList(list);
            List<StorageProductDTO> vList = getStorageProductDTO(proList);
            map.put("vList", vList);
            map.put("label", pos.getLabel());
            setQueryConditionQueryPositionSkus(map, posId);
            map.put("page", page.getPageNumber() + 1);
            if (pList.hasPrevious()) {
                map.put("prePage", page.previousOrFirst().getPageNumber());
            }
            if (pList.hasNext()) {
                map.put("nextPage", page.next().getPageNumber());
            }
        } else {
            map.put("notResult", "未找到需要的结果");
        }
        return "storage/send/position-skus";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "stock/listProducts")
public String queryStorageProduct(String isbn,String bookName,String bookAuthor,String bookPublisher,ModelMap model){
    try {
        List<Bookinfo> books = null;
        boolean allEmpty = StringUtils.isBlank(isbn) && StringUtils.isBlank(bookName) && StringUtils.isBlank(bookAuthor) && StringUtils.isBlank(bookPublisher);
        if (allEmpty) {
            // 如果条件都为空只查200条
            Pageable page = new PageRequest(0, 200);
            books = this.bookService.findAll(page).getContent();
        } else {
            Specification<Bookinfo> spec = buildSpec(bookName, isbn, bookAuthor, bookPublisher);
            // 查询图书
            books = this.bookService.findBySpecification(spec);
        }
        if (CollectionUtils.isEmpty(books)) {
            model.put(NO_RESULT, "没有找到相关记录");
            return VIEW_URL_PRODUCT_LIST;
        }
        Map<Integer, StorageProductViewDTO> bookMap = new HashMap<Integer, StorageProductViewDTO>();
        for (Bookinfo book : books) {
            StorageProductViewDTO view = new StorageProductViewDTO();
            BeanUtils.copyProperties(book, view);
            bookMap.put(book.getId(), view);
        }
        // 查询库存
        List<Integer> bookIds = new ArrayList<Integer>(bookMap.keySet());
        List<StorageProduct> storageProds = this.storageService.findStorageProductBySkuIdsAndRepoId(bookIds, StorageUtil.getRepoId());
        if (CollectionUtils.isEmpty(storageProds)) {
            model.put(NO_RESULT, "没有找到相关记录");
            return VIEW_URL_PRODUCT_LIST;
        }
        // 转换结果
        for (StorageProduct prod : storageProds) {
            StorageProductViewDTO v = bookMap.get(prod.getSkuId());
            BeanUtils.copyProperties(prod, v);
        }
        List<StorageProductViewDTO> spList = null;
        // 如果结果大于100条只取前100条
        if (bookMap.values().size() > 100) {
            spList = new ArrayList<StorageProductViewDTO>();
            int i = 0;
            Iterator<StorageProductViewDTO> it = bookMap.values().iterator();
            while (it.hasNext()) {
                if (i == 100) {
                    break;
                }
                spList.add(it.next());
                i++;
            }
            model.put("storageProducts", spList);
        } else {
            model.put("storageProducts", bookMap.values());
        }
        model.put("isbn", isbn);
        return VIEW_URL_PRODUCT_LIST;
    } catch (Exception e) {
        model.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/queryStorageOrder")
public String queryStorageOrder(HttpServletRequest request,ModelMap map,String outTradeNo,String buyerName,String systemStatus){
    try {
        // 如果状态为空默认选择新创建状态
        if (StringUtils.isBlank(systemStatus)) {
            systemStatus = StorageOrder.TradeStatus.CREATED.getValue();
        }
        List<String> sList = Arrays.asList(allStatus);
        if (!sList.contains(systemStatus)) {
            map.put("actionError", "错误的订单状态");
            return "error";
        }
        // 创建分页查询
        pageInfo(request, map, outTradeNo, buyerName, systemStatus);
        map.put("sendStatus", systemStatus);
        return "storage/send/send-order-list";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/findIoDetailByBatchId")
public String findIoDetailByBatchId(Integer batchId,ModelMap map,HttpServletRequest request){
    try {
        Pageable page = WebHelper.buildPageRequest(request);
        QueryUtil<StorageIoDetail> query = new QueryUtil<StorageIoDetail>();
        query.eq("batchId", batchId);
        query.eq("repoId", StorageUtil.getRepoId());
        Page<StorageIoDetail> orderList = this.storageIoDetailDao.findAll(query.getSpecification(), page);
        if (!orderList.getContent().isEmpty()) {
            List<StorageIoDetail> list = orderList.getContent();
            List<StorageIoDetailView> listShow = new ArrayList<StorageIoDetailView>();
            // 将状态转换为中文
            for (StorageIoDetail s : list) {
                StorageIoDetailView io = new StorageIoDetailView();
                BeanUtils.copyProperties(s, io);
                io.setZhCnStatus(DetailStatus.getDetailStatus(s.getDetailStatus()).getDisplay());
                io.setRealName(getRealName(io.getOperator()));
                io.setZhCnType(IoType.getIoType(s.getIoDetailType()).getDisplay());
                Bookinfo book = getBook(s.getSkuId());
                io.setBook(book);
                listShow.add(io);
            }
            map.put("detailList", listShow);
            map.put("batchId", batchId);
            map.put("page", page.getPageNumber() + 1);
            if (orderList.hasPrevious()) {
                map.put("prePage", page.previousOrFirst().getPageNumber());
            }
            if (orderList.hasNext()) {
                map.put("nextPage", page.next().getPageNumber());
            }
        } else {
            map.put("notResult", "未找到需要的结果");
        }
        return "storage/send/io-detail-list";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


public String buildTakeGoodsTitle(Integer skuId){
    Bookinfo book = this.bookService.findBookById(skuId);
    String bookName = book.getBookName();
    String bookIsbn = book.getIsbn().substring(9);
    return String.format("%s  %s", bookName, bookIsbn);
}


@RequestMapping(value = "/gotoUpdateStorageRepoInfo")
@Token(generate = true)
public String gotoUpdateStorageRepoInfo(ModelMap map,Integer repoId){
    StorageRepoInfo info = this.storageRepoInfoDao.findOne(repoId);
    if (info == null) {
        map.put("actionError", "请勿修改页面源代码");
        return "error";
    }
    map.put("repoId", info.getRepoId());
    map.put("stockName", info.getName());
    return "storage/update/update-stock";
}


public boolean updateStorageRepoInfo(String stockName,Integer repoId){
    List<StorageRepoInfo> list = this.storageRepoInfoDao.findByOwnerIdAndRepoIdOrderByGmtCreateAsc(ShopUtil.getCompanyId(), repoId);
    if (list.isEmpty()) {
        return false;
    }
    StorageRepoInfo info = list.get(0);
    info.setName(stockName);
    info.setStatus(StorageRepoInfo.Status.AVAILABLE.getValue());
    info.setGmtModify(new Date());
    this.storageRepoInfoDao.save(info);
    ShopUtil.clearAllCached();
    return true;
}


public Specification<Bookinfo> buildSpec(String bookName,String bookISBN,String bookAuthor,String bookPublisher){
    QueryUtil<Bookinfo> query = new QueryUtil<Bookinfo>();
    if (!StringUtils.isBlank(bookName)) {
        query.like("bookName", "%" + bookName + "%");
    }
    // ISBN不为空则添加条件
    if (!StringUtils.isBlank(bookISBN)) {
        String[] isbns = bookISBN.split(",");
        for (int i = 0; i < isbns.length; i++) {
            isbns[i] = isbns[i].trim();
            if (!StringUtils.isNumeric(isbns[i])) {
                throw new RuntimeException("isbn输入了非法字符");
            }
        }
        if (isbns.length == 1) {
            query.eq("isbn", isbns[0]);
        } else {
            query.in("isbn", (Object[]) isbns);
        }
    }
    // 模糊查询作者
    if (!StringUtils.isBlank(bookAuthor))
        query.like("bookAuthor", "%" + bookAuthor + "%");
    if (!StringUtils.isBlank(bookPublisher)) {
        query.eq("bookPublisher", bookPublisher);
    }
    // 状态为废弃的不查询
    query.ne("bookStatus", ConstantString.ABANDON);
    return query.getSpecification();
}


@RequestMapping("stock/listStockDist")
public String queryStoragePosStock(Integer productId,ModelMap model){
    // 参数检验
    if (productId == null) {
        model.put(ACTION_ERROR, "未选择任何图书");
        return VIEW_URL_STOCK_DIST;
    }
    // 查询库存分布
    List<StockDTO> list = this.storageService.findAllStockByProductId(productId);
    if (CollectionUtils.isEmpty(list)) {
        model.put(ACTION_MESSAGE, "无库存记录");
        return VIEW_URL_STOCK_DIST;
    }
    Integer bookId = list.get(0).getSkuId();
    // 查询图书基本信息
    Bookinfo book = this.bookService.findBookById(bookId);
    if (book == null) {
        model.put(ACTION_MESSAGE, "无此图书");
        return VIEW_URL_STOCK_DIST;
    }
    // 数据写入页面
    model.put("list", list);
    model.put("book", book);
    return VIEW_URL_STOCK_DIST;
}


@RequestMapping(value = "/gotoFastTakeGoods")
@Token(generate = true)
public String gotoFastTakeGoods(ModelMap map){
    return "storage/send/fast-take-goods";
}


@RequestMapping(value = "/queryIoBatch")
public String queryIoBatch(HttpServletRequest request,ModelMap map,String bizType,String status){
    try {
        Pageable page = WebHelper.buildPageRequest(request);
        QueryUtil<StorageIoBatch> query = new QueryUtil<StorageIoBatch>();
        if (StringUtils.isNotBlank(bizType)) {
            if (bizType.equals(BizType.IN_BATCH.getValue())) {
                query.in("bizType", (Object[]) INPUT);
            } else if (bizType.equals(BizType.OUT_BATCH.getValue())) {
                query.in("bizType", (Object[]) OUTPUT);
            }
        }
        if (StringUtils.isNotBlank(status)) {
            query.eq("status", status);
        }
        query.eq("repoId", StorageUtil.getRepoId());
        query.desc("gmtModify");
        Page<StorageIoBatch> orderList = this.storageIoBatchDao.findAll(query.getSpecification(), page);
        if (!orderList.getContent().isEmpty()) {
            List<StorageIoBatch> list = orderList.getContent();
            List<StorageIoBatchDTO> listShow = new ArrayList<StorageIoBatchDTO>();
            // 将状态转换为中文
            for (StorageIoBatch s : list) {
                StorageIoBatchDTO io = new StorageIoBatchDTO();
                BeanUtils.copyProperties(s, io);
                io.setZhCnStatus(Status.getStatus(s.getStatus()).getDisplay());
                io.setRealName(getRealName(io.getOperator()));
                io.setZhCnType(BizType.getBizType(s.getBizType()).getDisplay());
                listShow.add(io);
            }
            map.put("batchList", listShow);
            map.put("checkType", bizType);
            map.put("checkStatus", status);
            map.put("page", page.getPageNumber() + 1);
            setQueryCondition(bizType, status, map);
            if (orderList.hasPrevious()) {
                map.put("prePage", page.previousOrFirst().getPageNumber());
            }
            if (orderList.hasNext()) {
                map.put("nextPage", page.next().getPageNumber());
            }
        } else {
            map.put("notResult", "未找到需要的结果");
        }
        return "storage/send/io-batch-list";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "/savePosition")
@Token(checking = true)
public String savePosition(String label,ModelMap map){
    try {
        if (StringUtils.isBlank(label)) {
            map.put(ACTION_ERROR, "label不能为空");
            return "forward:/storage/gotoSavePosition";
        }
        StoragePosition storagePosition = this.storageService.findByLabelAndRepoId(label, StorageUtil.getRepoId());
        if (storagePosition != null) {
            map.put(ACTION_ERROR, "此库位已存在");
            return "forward:/storage/gotoSavePosition";
        } else {
            this.storageService.savePosition(label, StorageUtil.getRepoId());
            map.put(ACTION_MESSAGE, label + "库位新增成功");
            return "forward:/storage/gotoSavePosition";
        }
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


@RequestMapping(value = "gotoCreateOrder")
public String goToCreateOrder(ModelMap map){
    try {
        List<ShopInfo> shopList = this.shopService.findCompanyShop(StorageUtil.getCompanyId());
        if (shopList.isEmpty()) {
            map.put(ACTION_ERROR, "您没有店铺请新建店铺");
            return "error";
        }
        map.put("shopList", shopList);
        return "storage/send/create-order";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "error";
    }
}


public List<StorageProductDTO> getStorageProductDTO(List<StorageProduct> proList){
    List<StorageProductDTO> list = new ArrayList<StorageProductDTO>();
    for (StorageProduct s : proList) {
        StorageProductDTO dto = new StorageProductDTO();
        BeanUtils.copyProperties(s, dto);
        Bookinfo book = this.bookService.findBookById(s.getSkuId());
        dto.setBook(book);
        list.add(dto);
    }
    return list;
}


}