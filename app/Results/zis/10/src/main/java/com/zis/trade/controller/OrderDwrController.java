package com.zis.trade.controller;
 import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.util.SequenceCreator;
import com.zis.common.util.ZisUtils;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.service.ShopService;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.entity.StorageRepoInfo;
import com.zis.storage.service.StorageService;
import com.zis.storage.util.StorageUtil;
import com.zis.trade.dto.ArrangeOrderToRepoDTO;
import com.zis.trade.dto.BlockOrderDTO;
import com.zis.trade.dto.ChangeAddressDTO;
import com.zis.trade.dto.ChangeOrderAddressDTO;
import com.zis.trade.dto.CreateOrderQuerySkuInfoViewDTO;
import com.zis.trade.dto.CreateOrderQuerySkuInfoViewDTO.SkuInfo;
import com.zis.trade.dto.CreateOrderViewDTO;
import com.zis.trade.dto.CreateOrderViewDTO.SkuViewInfo;
import com.zis.trade.dto.ExpressNumberDTO;
import com.zis.trade.dto.FillExpressNumberDTO;
import com.zis.trade.dto.OrderVO;
import com.zis.trade.dto.RefundMemoDTO;
import com.zis.trade.dto.RemarkDTO;
import com.zis.trade.dto.SendOutViewDTO;
import com.zis.trade.dto.SkuInfoViewDTO;
import com.zis.trade.entity.Order.OrderType;
import com.zis.trade.service.OrderService;
import com.zis.Interface.StorageService;
import com.zis.Interface.BookService;
import com.zis.Interface.ShopService;
import com.zis.DTO.StorageService;
import com.zis.DTO.StorageService;
import com.zis.DTO.StorageProduct;
import com.zis.DTO.ShopService;
import com.zis.DTO.StorageService;
import com.zis.DTO.ShopService;
import com.zis.DTO.StorageService;
import com.zis.DTO.ShopService;
@Controller
public class OrderDwrController {

@Autowired
 private  OrderService orderService;

@Autowired
 private  StorageService storageService;

@Autowired
 private  BookService bookService;

@Autowired
 private  ShopService shopsService;

 private  String CREATE_OREDER_VIEW_MAP;


public ChangeOrderAddressDTO changeOrderAddress(Integer orderId,String receiverName,String receiverPhone,String receiverAddr){
    ChangeOrderAddressDTO dto = new ChangeOrderAddressDTO();
    if (StringUtils.isBlank(receiverName)) {
        dto.setSuccess(false);
        dto.setFailReason("?????????????????????");
        return dto;
    }
    if (StringUtils.isBlank(receiverPhone)) {
        dto.setSuccess(false);
        dto.setFailReason("???????????????????????????");
        return dto;
    }
    if (StringUtils.isBlank(receiverAddr)) {
        dto.setSuccess(false);
        dto.setFailReason("???????????????????????????");
        return dto;
    }
    if (orderId == null) {
        dto.setSuccess(false);
        dto.setFailReason("orderId????????????");
        return dto;
    }
    try {
        ChangeAddressDTO newAddress = new ChangeAddressDTO();
        newAddress.setReceiverAddr(receiverAddr);
        newAddress.setReceiverName(receiverName);
        newAddress.setReceiverPhone(receiverPhone);
        OrderVO vo = this.orderService.changeOrderAddress(orderId, StorageUtil.getUserId(), newAddress);
        BeanUtils.copyProperties(vo, dto);
        dto.setSuccess(true);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason(e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO updateSkuItemPrice(Integer skuId,Double updateItemPrice,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    try {
        if (skuId == null) {
            throw new RuntimeException("skuId????????????");
        }
        if (updateItemPrice == null) {
            throw new RuntimeException("????????????????????????");
        }
        List<SkuViewInfo> list = dto.getSkus();
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("session?????????????????????");
        }
        for (SkuViewInfo s : list) {
            if (s.getSkuId().equals(skuId)) {
                s.setItemPrice(updateItemPrice);
                SkuInfoViewDTO sbd = new SkuInfoViewDTO();
                BeanUtils.copyProperties(s, sbd);
                dto.setSkuOld(sbd);
            }
        }
        Double orderMoney = getOrderMoney(dto);
        dto.setSuccess(true);
        dto.setOrderMoney(orderMoney);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????" + e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO updateOrderPostage(Double postage,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    try {
        if (postage == null) {
            throw new RuntimeException("postage????????????");
        }
        dto.setPostage(postage);
        Double orderMoney = getOrderMoney(dto);
        dto.setSuccess(true);
        dto.setOrderMoney(orderMoney);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("??????????????????" + e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO updateOrderMoney(Double orderMoney,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    try {
        if (orderMoney == null) {
            throw new RuntimeException("orderMoney????????????");
        }
        Double money = getOrderMoney(dto);
        if (orderMoney < money) {
            throw new RuntimeException("????????????????????????????????????????????????");
        }
        dto.setSuccess(true);
        dto.setOrderMoney(orderMoney);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("??????????????????" + e.getMessage());
        return dto;
    }
}


public SendOutViewDTO toSendOut(String expressNumber){
    SendOutViewDTO dto = new SendOutViewDTO();
    try {
        if (StringUtils.isBlank(expressNumber)) {
            throw new RuntimeException("????????????????????????");
        }
        String expressNumberTrim = expressNumber.trim();
        OrderVO vo = this.orderService.sendOut(StorageUtil.getRepoId(), expressNumberTrim, StorageUtil.getUserId());
        dto.setOrderVO(vo);
        dto.setSuccess(true);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("????????????" + e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO setOrderType(String orderType,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    if (!OrderType.isDefined(orderType)) {
        dto.setSuccess(false);
        dto.setFailReason("?????????????????????");
    }
    try {
        dto.setSuccess(true);
        dto.setOrderType(orderType);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????" + e.getMessage());
        return dto;
    }
}


public BlockOrderDTO blockOrder(Integer orderId,String blockReason){
    BlockOrderDTO dto = new BlockOrderDTO();
    if (StringUtils.isBlank(blockReason)) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????");
        return dto;
    }
    if (orderId == null) {
        dto.setSuccess(false);
        dto.setFailReason("orderId????????????");
        return dto;
    }
    try {
        OrderVO vo = this.orderService.blockOrder(orderId, StorageUtil.getUserId(), blockReason);
        BeanUtils.copyProperties(vo, dto);
        dto.setSuccess(true);
        dto.setBlockReason(blockReason);
        dto.setId(orderId);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason(e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO sessionHasDTO(HttpSession session){
    CreateOrderViewDTO dto = (CreateOrderViewDTO) session.getAttribute(CREATE_OREDER_VIEW_MAP);
    if (dto == null) {
        dto = new CreateOrderViewDTO();
    }
    return dto;
}


public ArrangeOrderToRepoDTO queryStorageRepoInfoByOnlyOrderId(Integer orderId,String forwardUrl){
    ArrangeOrderToRepoDTO dto = new ArrangeOrderToRepoDTO();
    if (orderId == null) {
        dto.setSuccess(false);
        dto.setFailReason("orderId????????????");
        return dto;
    }
    if (StringUtils.isBlank(forwardUrl)) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????");
        return dto;
    }
    List<StorageRepoInfo> list = this.storageService.findStorageRepoInfoByCompanyId(StorageUtil.getCompanyId());
    if (list.isEmpty()) {
        dto.setSuccess(false);
        dto.setFailReason("???????????????");
        return dto;
    }
    dto.setForwardUrl(forwardUrl);
    dto.setOrderId(orderId);
    dto.setRepoList(list);
    dto.setSuccess(true);
    return dto;
}


public CreateOrderQuerySkuInfoViewDTO findSkuInfoByBookNameOrIsbn(String bookNameOrIsbn,Double discount,HttpSession session){
    CreateOrderQuerySkuInfoViewDTO dto = new CreateOrderQuerySkuInfoViewDTO();
    try {
        if (StringUtils.isBlank(bookNameOrIsbn)) {
            throw new RuntimeException("?????????????????????");
        }
        if (discount == null) {
            throw new RuntimeException("???????????????");
        }
        String condition = bookNameOrIsbn.trim();
        if (StringUtils.isNumeric(condition) && condition.length() != 13) {
            throw new RuntimeException("isbn??????13???");
        }
        List<Bookinfo> bookList;
        if (StringUtils.isNumeric(condition) && condition.length() == 13) {
            bookList = this.bookService.findBookByISBN(condition);
        } else {
            // ????????????????????????20???
            bookList = this.bookService.findBookInfoByBookNameLike("%" + condition + "%");
        }
        // ????????????
        List<SkuInfo> list = new ArrayList<CreateOrderQuerySkuInfoViewDTO.SkuInfo>();
        for (Bookinfo book : bookList) {
            StorageProduct sp = this.storageService.findBySkuIdAndRepoId(book.getId(), StorageUtil.getRepoId());
            // ?????????????????????
            if (sp == null) {
                continue;
            }
            if (sp.getStockAvailable() < 0) {
                continue;
            }
            SkuInfo sku = new SkuInfo();
            BeanUtils.copyProperties(book, sku);
            sku.setZisPrice(book.getBookPrice());
            sku.setBookPrice((book.getBookPrice() * discount));
            sku.setBookAmount(sp.getStockAvailable());
            list.add(sku);
        }
        dto.setSuccess(true);
        dto.setSkuList(list);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("??????????????????:" + e.getMessage());
        return dto;
    }
}


public RefundMemoDTO applyRefund(Integer orderId,String refundMemo){
    RefundMemoDTO dto = new RefundMemoDTO();
    if (StringUtils.isBlank(refundMemo)) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????");
        return dto;
    }
    if (orderId == null) {
        dto.setSuccess(false);
        dto.setFailReason("orderId????????????");
        return dto;
    }
    try {
        OrderVO vo = this.orderService.applyRefund(orderId, StorageUtil.getUserId(), new Date(), refundMemo);
        BeanUtils.copyProperties(vo, dto);
        dto.setSuccess(true);
        dto.setBuyerMessage(refundMemo);
        dto.setId(orderId);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason(e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO deleteSubOrder(Integer skuId,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    try {
        if (skuId == null) {
            throw new RuntimeException("skuId????????????");
        }
        List<SkuViewInfo> list = dto.getSkus();
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("session?????????????????????");
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getSkuId().equals(skuId)) {
                SkuInfoViewDTO sbd = new SkuInfoViewDTO();
                BeanUtils.copyProperties(list.get(i), sbd);
                dto.setSkuOld(sbd);
                list.remove(i);
            }
        }
        try {
            Double orderMoney = getOrderMoney(dto);
            dto.setOrderMoney(orderMoney);
        } catch (Exception e) {
            dto.setOrderMoney(0d);
        }
        dto.setSuccess(true);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("??????????????????" + e.getMessage());
        return dto;
    }
}


public RemarkDTO appendSellerRemark(Integer orderId,String remark){
    RemarkDTO dto = new RemarkDTO();
    if (StringUtils.isBlank(remark)) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????");
        return dto;
    }
    if (orderId == null) {
        dto.setSuccess(false);
        dto.setFailReason("orderId????????????");
        return dto;
    }
    try {
        String salerRemark = this.orderService.appendSellerRemark(orderId, StorageUtil.getUserId(), remark);
        dto.setSuccess(true);
        dto.setId(orderId);
        dto.setSalerRemark(salerRemark);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason(e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO updateReceiverInfo(String receiverName,String receiverPhone,String receiverAddr,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    try {
        // TODO ?????????????????????????????????
        // if (StringUtils.isBlank(receiverName)) {
        // throw new RuntimeException("?????????????????????");
        // }
        // if (StringUtils.isBlank(receiverPhone)) {
        // throw new RuntimeException("???????????????????????????");
        // }
        // if (StringUtils.isBlank(receiverAddr)) {
        // throw new RuntimeException("???????????????????????????");
        // }
        dto.setReceiverName(receiverName);
        dto.setReceiverPhone(receiverPhone);
        dto.setReceiverAddr(receiverAddr);
        dto.setSuccess(true);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("???????????????????????????" + e.getMessage());
        return dto;
    }
}


public boolean subOrderAllNull(SkuViewInfo so){
    if (StringUtils.isBlank(so.getIsbn()) && StringUtils.isBlank(so.getItemName()) && so.getSkuId() == null && so.getItemCount() == null && so.getItemPrice() == null) {
        return true;
    }
    if (so.getSkuId() == null || so.getItemCount() == null || so.getItemPrice() == null) {
        return true;
    }
    return false;
}


public FillExpressNumberDTO fillExpressNumber(Integer orderId,String expressNumber,String expressCompany){
    FillExpressNumberDTO dto = new FillExpressNumberDTO();
    if (StringUtils.isBlank(expressNumber)) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????");
        return dto;
    }
    if (StringUtils.isBlank(expressCompany)) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????");
        return dto;
    }
    if (orderId == null) {
        dto.setSuccess(false);
        dto.setFailReason("orderId????????????");
        return dto;
    }
    try {
        this.orderService.fillExpressNumber(orderId, expressNumber, expressCompany, StorageUtil.getUserId());
        dto.setExpressNumber(expressNumber);
        dto.setExpressCompany(expressCompany);
        dto.setId(orderId);
        dto.setSuccess(true);
        // ????????????????????????
        List<ExpressNumberDTO> dtoList = new ArrayList<ExpressNumberDTO>();
        ExpressNumberDTO exDto = new ExpressNumberDTO();
        BeanUtils.copyProperties(dto, exDto);
        exDto.setOrderId(dto.getId());
        dtoList.add(exDto);
        this.shopsService.logisticsOfflineSend(dtoList);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason(e.getMessage());
        return dto;
    }
}


public List<StorageRepoInfo> queryStorageRepoInfo(){
    return this.storageService.findStorageRepoInfoByCompanyId(StorageUtil.getCompanyId());
}


public CreateOrderViewDTO setShopIdToSession(Integer shopId,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    ShopInfo shop = this.shopsService.findShopByShopIdAndCompanyId(StorageUtil.getCompanyId(), shopId);
    if (shop == null) {
        dto.setSuccess(false);
        dto.setFailReason("??????????????????");
        return dto;
    }
    try {
        dto.setSuccess(true);
        dto.setShopId(shop.getShopId());
        dto.setDiscount(shop.getDiscount());
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("??????????????????" + e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO updateSkuItemCount(Integer skuId,Integer updateItemCount,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    try {
        if (skuId == null) {
            throw new RuntimeException("skuId????????????");
        }
        if (updateItemCount == null) {
            throw new RuntimeException("????????????????????????");
        }
        StorageProduct sp = this.storageService.findBySkuIdAndRepoId(skuId, StorageUtil.getRepoId());
        if (updateItemCount > sp.getStockAvailable()) {
            throw new RuntimeException("?????????????????????");
        }
        List<SkuViewInfo> list = dto.getSkus();
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("session?????????????????????");
        }
        for (SkuViewInfo s : list) {
            if (s.getSkuId().equals(skuId)) {
                s.setItemCount(updateItemCount);
                SkuInfoViewDTO sbd = new SkuInfoViewDTO();
                BeanUtils.copyProperties(s, sbd);
                dto.setSkuOld(sbd);
            }
        }
        Double orderMoney = getOrderMoney(dto);
        dto.setSuccess(true);
        dto.setOrderMoney(orderMoney);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("????????????????????????" + e.getMessage());
        return dto;
    }
}


public String generateManualOrderNumber(){
    Integer seq = SequenceCreator.getSequence(SequenceCreator.SEQ_ORDER_MANUAL_NUM);
    return "manual" + ZisUtils.getDateString("yyyyMMddHHmmss") + seq;
}


public CreateOrderViewDTO getManualTaobaoOrderOutOrderNumber(String manualOrderType,Integer shopId,String outOrderNumber,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    if (StringUtils.isBlank(outOrderNumber)) {
        dto.setSuccess(false);
        dto.setFailReason("?????????????????????");
        return dto;
    }
    ShopInfo shop = this.shopsService.findShopByShopIdAndCompanyId(StorageUtil.getCompanyId(), shopId);
    if (shop == null) {
        dto.setSuccess(false);
        dto.setFailReason("??????????????????");
        return dto;
    }
    boolean result = this.orderService.existByOutOrderNumber(shopId, outOrderNumber);
    if (result) {
        dto.setSuccess(false);
        dto.setFailReason("???????????????");
        return dto;
    }
    try {
        dto.setSuccess(true);
        dto.setOutOrderNumber(outOrderNumber);
        dto.setManualOrderType(manualOrderType);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("?????????????????????" + e.getMessage());
        return dto;
    }
}


public CreateOrderViewDTO addSkuInfoToSession(Integer skuId,String isbn,String itemName,Integer itemCount,Double itemPrice,Double zisPrice,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    try {
        if (skuId == null) {
            throw new RuntimeException("skuId????????????");
        }
        if (StringUtils.isBlank(isbn)) {
            throw new RuntimeException("isbn????????????");
        }
        if (StringUtils.isBlank(itemName)) {
            throw new RuntimeException("????????????????????????");
        }
        if (itemCount == null) {
            throw new RuntimeException("????????????????????????");
        }
        if (itemPrice == null) {
            throw new RuntimeException("????????????????????????");
        }
        if (dto.getSkuNumber() == null) {
            dto.setSkuNumber(0);
        } else {
            Integer i = dto.getSkuNumber();
            dto.setSkuNumber(++i);
        }
        List<SkuViewInfo> list = dto.getSkus();
        SkuViewInfo sb = new SkuViewInfo();
        sb.setSkuId(skuId);
        sb.setIsbn(isbn);
        sb.setItemCount(itemCount);
        sb.setItemName(itemName);
        sb.setResultInt(dto.getSkuNumber());
        sb.setItemPrice(itemPrice);
        sb.setZisPrice(zisPrice);
        if (list == null || list.isEmpty()) {
            list = new ArrayList<SkuViewInfo>();
            dto.setSkus(list);
        } else {
            for (SkuViewInfo s : list) {
                if (sb.getSkuId().equals(s.getSkuId())) {
                    throw new RuntimeException("????????????????????????,??????????????????");
                }
            }
        }
        list.add(sb);
        SkuInfoViewDTO sbd = new SkuInfoViewDTO();
        BeanUtils.copyProperties(sb, sbd);
        dto.setSkuOld(sbd);
        Double orderMoney = getOrderMoney(dto);
        dto.setSuccess(true);
        dto.setOrderMoney(orderMoney);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("??????????????????" + e.getMessage());
        return dto;
    }
}


public Double getOrderMoney(CreateOrderViewDTO dto){
    Double orderMoney = 0d;
    if (dto.getSkus() == null || dto.getSkus().isEmpty()) {
        throw new RuntimeException("??????????????????");
    }
    for (SkuViewInfo so : dto.getSkus()) {
        if (so == null || subOrderAllNull(so)) {
            continue;
        }
        orderMoney += (so.getItemPrice() * so.getItemCount());
    }
    if (dto.getPostage() != null) {
        orderMoney += dto.getPostage();
    }
    return orderMoney;
}


public CreateOrderViewDTO getManualOutOrderNumber(String manualOrderType,HttpSession session){
    CreateOrderViewDTO dto = sessionHasDTO(session);
    try {
        String orderNumber = dto.getOutOrderNumber();
        if (StringUtils.isBlank(orderNumber)) {
            orderNumber = generateManualOrderNumber();
        }
        dto.setSuccess(true);
        dto.setOutOrderNumber(orderNumber);
        dto.setManualOrderType(manualOrderType);
        session.setAttribute(CREATE_OREDER_VIEW_MAP, dto);
        return dto;
    } catch (Exception e) {
        dto.setSuccess(false);
        dto.setFailReason("?????????????????????" + e.getMessage());
        return dto;
    }
}


}