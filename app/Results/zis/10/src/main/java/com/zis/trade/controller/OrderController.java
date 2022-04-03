package com.zis.trade.controller;
 import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.bookinfo.service.BookService;
import com.zis.common.controllertemplate.ViewTips;
import com.zis.common.excel.ExcelImporter;
import com.zis.common.excel.FileImporter;
import com.zis.common.mvc.ext.Token;
import com.zis.common.mvc.ext.WebHelperForOrder;
import com.zis.common.util.ZisUtils;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.service.ShopService;
import com.zis.shop.util.ShopUtil;
import com.zis.storage.util.StorageUtil;
import com.zis.trade.dto.CreateOrderViewDTO;
import com.zis.trade.dto.CreateOrderViewDTO.SkuViewInfo;
import com.zis.trade.dto.CreateTradeOrderDTO;
import com.zis.trade.dto.CreateTradeOrderDTO.SubOrder;
import com.zis.trade.dto.ExpressNumberDTO;
import com.zis.trade.dto.FillExpressNumberUploadDTO;
import com.zis.trade.dto.OrderAddressImportDTO;
import com.zis.trade.dto.OrderInfoDTO;
import com.zis.trade.dto.OrderInfoDTO.SkuInfo;
import com.zis.trade.dto.OrderQueryCondition;
import com.zis.trade.dto.OrderVO;
import com.zis.trade.dto.OrderVO.OrderDetailVO;
import com.zis.trade.entity.Order;
import com.zis.trade.entity.Order.ExpressStatus;
import com.zis.trade.entity.Order.OrderType;
import com.zis.trade.entity.Order.PayStatus;
import com.zis.trade.entity.Order.StorageStatus;
import com.zis.trade.service.OrderService;
import com.zis.Interface.ShopService;
import com.zis.Interface.BookService;
import com.zis.DTO.ShopService;
import com.zis.DTO.FileImporter;
import com.zis.DTO.BookService;
import com.zis.DTO.ShopService;
import com.zis.DTO.ShopService;
import com.zis.DTO.ShopService;
import com.zis.DTO.ShopService;
import com.zis.DTO.ShopService;
@Controller
@RequestMapping(value = "/order")
public class OrderController extends ExcelExportController<OrderVO>implements ViewTips{

 private  Logger logger;

@Autowired
 private  OrderService orderService;

@Autowired
 private  ShopService shopService;

@Autowired
 private  BookService bookService;

 private  String CREATE_OREDER_VIEW_MAP;

 private  String Apply_PAID;

 private  String FOR_WARD_SORT;


@Token(checking = true)
@RequestMapping(value = "/createOrder")
public String createOrder(CreateOrderViewDTO dto,BindingResult br,ModelMap map,HttpSession session,String token){
    List<ShopInfo> shopList = this.shopService.findCompanyShop(StorageUtil.getCompanyId());
    map.put("dto", dto);
    map.put("shopList", shopList);
    if (br.hasErrors()) {
        map.put("errors", br.getAllErrors());
        return "forward:/order/gotoCreateOrder";
    }
    try {
        verifyShopId(dto.getShopId());
        // 设置为自发
        dto.setOrderType(OrderType.SELF.getValue());
        CreateTradeOrderDTO orderDTO = buildCreateTradeOrderDTO(dto);
        Order order = this.orderService.createOrder(orderDTO);
        this.orderService.arrangeOrderToRepo(order.getId(), StorageUtil.getUserId(), StorageUtil.getRepoId());
        map.put(ACTION_MESSAGE, "操作成功");
        // 创建成功移除标签
        session.setAttribute(CREATE_OREDER_VIEW_MAP, null);
        map.put(ACTION_MESSAGE, "订单号 " + orderDTO.getOutOrderNumber() + " 创建成功");
        return "forward:/order/getUnpaidList" + FOR_WARD_SORT;
    } catch (Exception e) {
        e.printStackTrace();
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/gotoCreateOrder";
    }
}


@RequestMapping(value = "/cancelOrder")
public String cancelOrder(Integer[] orderId,String forwardUrl,ModelMap map){
    if (orderId == null) {
        map.put(ACTION_ERROR, "请选择订单");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        List<Integer> oIds = Arrays.asList(orderId);
        for (Integer i : oIds) {
            this.orderService.cancelOrder(i, StorageUtil.getUserId());
        }
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


@RequestMapping(value = "/getPickupList")
public String getPickupList(HttpServletRequest request,ModelMap map){
    try {
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = this.orderService.findOrdersByStatus(StorageUtil.getCompanyId(), null, null, StorageStatus.PICKUP, page);
        pageInfo(orderList, page, map);
        return "trade/storage_show/pickup-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/storage_show/pickup-header";
    }
}


public Map<String,Integer> initPropOrderInfo(){
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("outTradeNumber", 0);
    map.put("orderMoney", 3);
    map.put("status", 10);
    map.put("buyerMessage", 11);
    map.put("receiverName", 12);
    map.put("receiverAddr", 13);
    map.put("receiverPhone", 16);
    map.put("salerRemark", 23);
    return map;
}


@RequestMapping(value = "/cancelRefund")
public String cancelRefund(Integer[] orderId,String forwardUrl,String memo,ModelMap map){
    if (orderId == null) {
        map.put(ACTION_ERROR, "请选择订单");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        List<Integer> list = Arrays.asList(orderId);
        for (Integer i : list) {
            this.orderService.cancelRefund(i, StorageUtil.getUserId(), memo);
        }
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


public List<SkuInfo> buildSkuInfoDTOList(InputStream input){
    // 设置模板文件，用于检验导入文件是否合法
    Integer headerRownums = 1;
    try {
        // 初始化导入器
        FileImporter<SkuInfo> im = new ExcelImporter<SkuInfo>(input, null);
        im.setHeaderRowNums(headerRownums);
        // 检验导入文件是否合法
        String errMsg = im.validate();
        if (StringUtils.isNotBlank(errMsg)) {
            throw new RuntimeException("导入文件非法");
        }
        String subCheck = subCheckSkuInfoFileFormat(im.loadFactHeader());
        if (StringUtils.isNotBlank(subCheck)) {
            throw new RuntimeException(subCheck);
        }
        // 解析文件并入库
        Map<String, Integer> propMapping = initPropSkuInfo();
        SkuInfo instance = new SkuInfo();
        List<SkuInfo> list = im.parse(instance, propMapping);
        if (list.isEmpty()) {
            throw new RuntimeException("导入失败，文件为空");
        }
        buildSkuListSkuIdAndItemName(list);
        return list;
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage(), e);
    }
}


public void setQueryConditionToPage(ModelMap map,OrderQueryCondition cond){
    if (cond == null) {
        return;
    }
    StringBuilder condition = new StringBuilder();
    if (StringUtils.isNotBlank(cond.getOutOrderNumber())) {
        condition.append("outOrderNumber=" + cond.getOutOrderNumber() + "&");
    }
    if (StringUtils.isNotBlank(cond.getReceiverName())) {
        condition.append("receiverName=" + cond.getReceiverName() + "&");
    }
    if (StringUtils.isNotBlank(cond.getReceiverPhone())) {
        condition.append("receiverPhone=" + cond.getReceiverPhone() + "&");
    }
    if (StringUtils.isNotBlank(cond.getExpressNumber())) {
        condition.append("expressNumber=" + cond.getExpressNumber() + "&");
    }
    map.put("queryCondition", condition.toString());
}


public String subCheckFileFormat(List<String> factHeader){
    String outOrderNumber = "订单编号";
    String receiverName = "收件人";
    String expressCompany = "快递名称";
    String expressNumber = "快递单号";
    if (!factHeader.get(1).equals(outOrderNumber)) {
        return "格式错误，B列必须是:" + outOrderNumber;
    }
    if (!factHeader.get(2).equals(receiverName)) {
        return "格式错误，C列必须是:" + receiverName;
    }
    if (!factHeader.get(13).equals(expressCompany)) {
        return "格式错误，N列必须是:" + expressCompany;
    }
    if (!factHeader.get(14).equals(expressNumber)) {
        return "格式错误，O列必须是:" + expressNumber;
    }
    return null;
}


public List<OrderAddressImportDTO> buildOrderAddressImportDTO(List<OrderInfoDTO> list){
    List<OrderAddressImportDTO> dtoList = new ArrayList<OrderAddressImportDTO>();
    for (OrderInfoDTO dto : list) {
        OrderAddressImportDTO d = new OrderAddressImportDTO();
        BeanUtils.copyProperties(dto, d);
        d.setOutOrderNumber(dto.getOutTradeNumber());
        dtoList.add(d);
    }
    return dtoList;
}


@RequestMapping(value = "/sendOut")
public String sendOut(ModelMap map){
    return "trade/send_out/send-out";
}


@RequestMapping(value = "/getWaitPickUpList")
public String getWaitPickUpList(HttpServletRequest request,ModelMap map){
    try {
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = this.orderService.findOrdersByStatus(StorageUtil.getCompanyId(), null, null, StorageStatus.ARRANGED, page);
        pageInfo(orderList, page, map);
        return "trade/storage_show/wait-pickup-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/storage_show/wait-pickup-header";
    }
}


@RequestMapping(value = "/getPrintedList")
public String getPrintedList(HttpServletRequest request,ModelMap map){
    try {
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = this.orderService.findOrdersByStatus(StorageUtil.getCompanyId(), null, ExpressStatus.PRINTED, null, page);
        pageInfo(orderList, page, map);
        return "trade/storage_show/printed-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/storage_show/printed-header";
    }
}


public void buildSkuListSkuIdAndItemName(List<SkuInfo> skuList){
    for (SkuInfo s : skuList) {
        Bookinfo book = buildBookInfoByItemOutNum(s);
        s.setSkuId(book.getId());
        s.setItemName(book.getBookName());
    }
}


@RequestMapping(value = "/arrangeOrderToPos")
public String arrangeOrderToPos(Integer[] orderId,String forwardUrl,Integer repoId,ModelMap map){
    if (orderId == null) {
        map.put(ACTION_ERROR, "请选择订单");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        List<Integer> orderIds = Arrays.asList(orderId);
        for (Integer i : orderIds) {
            this.orderService.arrangeOrderToRepo(i, StorageUtil.getUserId(), repoId);
        }
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


public Bookinfo buildBookInfoByItemOutNum(SkuInfo skuInfo){
    if (StringUtils.isBlank(skuInfo.getItemOutNum())) {
        throw new RuntimeException("订单编码:" + skuInfo.getOutOrderNumber() + " 商家编码为空");
    }
    String[] itemOutNum = skuInfo.getItemOutNum().split("-");
    if (itemOutNum.length == 1) {
        List<Bookinfo> list = this.bookService.findBookByISBN(itemOutNum[0]);
        if (list.isEmpty()) {
            throw new RuntimeException("订单编码:" + skuInfo.getOutOrderNumber() + "商家编码:" + itemOutNum[0] + "在zis中无商品");
        }
        if (list.size() > 1) {
            throw new RuntimeException("订单编码:" + skuInfo.getOutOrderNumber() + "商家编码:" + itemOutNum[0] + "一码多书，请修改商家编码");
        }
        return list.get(0);
    } else if (itemOutNum.length == 2) {
        Integer bookId;
        try {
            bookId = Integer.parseInt(itemOutNum[1]);
        } catch (NumberFormatException e) {
            throw new RuntimeException("订单编码:" + skuInfo.getOutOrderNumber() + "商家编码有误");
        }
        Bookinfo book = this.bookService.findByIdAndIsbn(bookId, itemOutNum[0]);
        if (book == null) {
            throw new RuntimeException("订单编码:" + skuInfo.getOutOrderNumber() + "商家编码:" + itemOutNum[0] + "在zis中无商品");
        }
        return book;
    } else {
        throw new RuntimeException("订单编码:" + skuInfo.getOutOrderNumber() + "商家编码有误");
    }
}


public Map<String,Integer> initPropMapping(){
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("orderId", 1);
    map.put("receiverName", 2);
    map.put("expressCompany", 13);
    map.put("expressNumber", 14);
    return map;
}


@Override
public String[] getRowDatas(OrderVO vo){
    String[] rowDatas = new String[this.getTableHeaders().length];
    rowDatas[0] = vo.getId().toString();
    rowDatas[1] = vo.getReceiverName();
    rowDatas[3] = vo.getReceiverPhone();
    rowDatas[4] = vo.getReceiverAddr();
    rowDatas[5] = getOrderDetailVOToString(vo.getOrderDetailVOs());
    rowDatas[6] = vo.getBuyerMessage();
    return rowDatas;
}


public List<CreateTradeOrderDTO> buildCreateTradeOrderDTO(List<OrderInfoDTO> orderList,List<SkuInfo> skuList){
    List<CreateTradeOrderDTO> list = new ArrayList<CreateTradeOrderDTO>();
    for (OrderInfoDTO order : orderList) {
        CreateTradeOrderDTO dto = new CreateTradeOrderDTO();
        BeanUtils.copyProperties(order, dto);
        List<SubOrder> subList = new ArrayList<CreateTradeOrderDTO.SubOrder>();
        for (SkuInfo sku : skuList) {
            if (dto.getOutOrderNumber().equals(sku.getOutOrderNumber())) {
                SubOrder so = new SubOrder();
                BeanUtils.copyProperties(sku, so);
                subList.add(so);
            }
        }
        dto.setSubOrders(subList);
        list.add(dto);
    }
    return list;
}


@Override
public List<OrderVO> queryExportData(HttpServletRequest request){
    String[] orderIdStr = request.getParameterValues("orderId");
    List<Integer> orderIds = new ArrayList<Integer>();
    for (String s : orderIdStr) {
        Integer orderId = Integer.parseInt(s);
        orderIds.add(orderId);
    }
    List<OrderVO> list = this.orderService.printExpressList(orderIds, StorageUtil.getUserId());
    return list;
}


public List<FillExpressNumberUploadDTO> buildDTOList(InputStream input){
    // 设置模板文件，用于检验导入文件是否合法
    try {
        // 初始化导入器
        FileImporter<FillExpressNumberUploadDTO> im = new ExcelImporter<FillExpressNumberUploadDTO>(input, null);
        // 检验导入文件是否合法
        String errMsg = im.validate();
        if (StringUtils.isNotBlank(errMsg)) {
            throw new RuntimeException("导入文件非法");
        }
        String subCheck = subCheckFileFormat(im.loadFactHeader());
        if (StringUtils.isNotBlank(subCheck)) {
            throw new RuntimeException(subCheck);
        }
        // 解析文件并入库
        Map<String, Integer> propMapping = initPropMapping();
        FillExpressNumberUploadDTO instance = new FillExpressNumberUploadDTO();
        List<FillExpressNumberUploadDTO> list = im.parse(instance, propMapping);
        if (list.isEmpty()) {
            throw new RuntimeException("导入失败，文件为空");
        }
        for (FillExpressNumberUploadDTO dto : list) {
            dto.setExpressCompany(dto.getExpressCompany());
        }
        return list;
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage(), e);
    }
}


@Token(generate = true)
@RequestMapping(value = "/gotoCreateOrder")
public String gotoCreateOrder(ModelMap map,HttpSession session){
    List<ShopInfo> shopList = this.shopService.findCompanyShop(StorageUtil.getCompanyId());
    CreateOrderViewDTO dtos = (CreateOrderViewDTO) session.getAttribute(CREATE_OREDER_VIEW_MAP);
    map.put("dto", dtos);
    map.put("shopList", shopList);
    return "trade/create_order/create-order";
}


public String subCheckOrderInfoFileFormat(List<String> factHeader){
    String outOrderNumber = "订单编号";
    String orderMoney = "买家应付货款";
    String status = "订单状态";
    String buyerMessage = "买家留言";
    String receiverName = "收货人姓名";
    String receiverAddr = "收货地址";
    String receiverPhone = "联系手机";
    String salerRemark = "订单备注";
    if (!factHeader.get(0).equals(outOrderNumber)) {
        return "格式错误，A列必须是:" + outOrderNumber;
    }
    if (!factHeader.get(3).equals(orderMoney)) {
        return "格式错误，D列必须是:" + orderMoney;
    }
    if (!factHeader.get(10).equals(status)) {
        return "格式错误，K列必须是:" + status;
    }
    if (!factHeader.get(11).equals(buyerMessage)) {
        return "格式错误，L列必须是:" + buyerMessage;
    }
    if (!factHeader.get(12).equals(receiverName)) {
        return "格式错误，M列必须是:" + receiverName;
    }
    if (!factHeader.get(13).equals(receiverAddr)) {
        return "格式错误，N列必须是:" + receiverAddr;
    }
    if (!factHeader.get(16).equals(receiverPhone)) {
        return "格式错误，Q列必须是:" + receiverPhone;
    }
    if (!factHeader.get(23).equals(salerRemark)) {
        return "格式错误，X列必须是:" + salerRemark;
    }
    return null;
}


@RequestMapping(value = "/getRefundingList")
public String getRefundingList(HttpServletRequest request,ModelMap map){
    try {
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = this.orderService.findOrdersByStatus(StorageUtil.getCompanyId(), PayStatus.REFUNDING, null, null, page);
        pageInfo(orderList, page, map);
        return "trade/shop_show/refunding-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/shop_show/refunding-header";
    }
}


@Token(checking = true)
@RequestMapping(value = "/excelAddrToOrderUpload")
public String excelAddrToOrderUpload(MultipartFile orderFile,Integer shopId,ModelMap map){
    try {
        verifyShopId(shopId);
        List<OrderInfoDTO> list = buildOrderInfoDTOList(orderFile.getInputStream(), shopId);
        List<OrderAddressImportDTO> dtoList = buildOrderAddressImportDTO(list);
        List<String> failList = this.orderService.importReceiverAddr(dtoList);
        if (!failList.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (String s : failList) {
                sb.append(s + "<br/>");
            }
            throw new RuntimeException(sb.toString());
        }
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/gotoExcelAddrToOrderUpload";
    } catch (Exception e) {
        logger.error(e.getMessage(), e);
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/gotoExcelAddrToOrderUpload";
    }
}


@RequestMapping(value = "/agreeRefund")
public String agreeRefund(Integer[] orderId,String forwardUrl,String memo,ModelMap map){
    if (orderId == null) {
        map.put(ACTION_ERROR, "请选择订单");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        List<Integer> list = Arrays.asList(orderId);
        for (Integer i : list) {
            this.orderService.agreeRefund(i, StorageUtil.getUserId(), memo);
        }
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    } catch (Exception e) {
        e.printStackTrace();
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


@Override
public String[] getTableHeaders(){
    return new String[] { "订单编号", "收件人", "固话", "手机", "地址", "发货信息", "备注", "代收金额", "保价金额", "业务类型" };
}


@RequestMapping(value = "/getAllShopOrderList")
public String getAllShopOrderList(OrderQueryCondition cond,HttpServletRequest request,ModelMap map){
    try {
        boolean condIsEmpty = cond == null || StringUtils.isBlank(cond.getExpressNumber()) && StringUtils.isBlank(cond.getOutOrderNumber()) && StringUtils.isBlank(cond.getReceiverName()) && StringUtils.isBlank(cond.getReceiverPhone());
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = null;
        if (condIsEmpty) {
            orderList = this.orderService.findOrdersByCondition(StorageUtil.getCompanyId(), page);
        } else {
            orderList = this.orderService.findOrdersByCondition(StorageUtil.getCompanyId(), cond, page);
        }
        pageInfo(orderList, page, map);
        setQueryConditionToPage(map, cond);
        return "trade/shop_show/all-shop-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/shop_show/all-shop-header";
    }
}


@RequestMapping(value = "/fillExpressNumberUpload")
public String fillExpressNumberUpload(MultipartFile excelFile,ModelMap map){
    try {
        List<FillExpressNumberUploadDTO> dtoList = buildDTOList(excelFile.getInputStream());
        List<ExpressNumberDTO> list = new ArrayList<ExpressNumberDTO>();
        for (FillExpressNumberUploadDTO d : dtoList) {
            ExpressNumberDTO dto = new ExpressNumberDTO();
            BeanUtils.copyProperties(d, dto);
            list.add(dto);
        }
        this.orderService.fillExpressNumbers(list, StorageUtil.getUserId());
        this.shopService.logisticsOfflineSend(list);
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/getPrintedList" + FOR_WARD_SORT;
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/getPrintedList" + FOR_WARD_SORT;
    }
}


@Override
public String getSuccessPage(String forwardUrl){
    return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
}


@RequestMapping(value = "/getWaitForPrintList")
public String getWaitForPrintList(HttpServletRequest request,ModelMap map){
    try {
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = this.orderService.findOrdersByStatus(StorageUtil.getCompanyId(), null, ExpressStatus.WAIT_FOR_PRINT, null, page);
        pageInfo(orderList, page, map);
        return "trade/storage_show/wait-print-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/storage_show/wait-print-header";
    }
}


@Token(checking = true)
@RequestMapping(value = "/excelCreateOrderUpload")
public String excelCreateOrderUpload(MultipartFile orderFile,MultipartFile skusFile,Integer shopId,ModelMap map){
    try {
        verifyShopId(shopId);
        List<OrderInfoDTO> orderList = buildOrderInfoDTOList(orderFile.getInputStream(), shopId);
        List<SkuInfo> skuList = buildSkuInfoDTOList(skusFile.getInputStream());
        // 将两个list合并
        List<CreateTradeOrderDTO> list = buildCreateTradeOrderDTO(orderList, skuList);
        for (CreateTradeOrderDTO orderDTO : list) {
            this.orderService.createOrder(orderDTO);
        }
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/gotoExcelCreateOrderUpload";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/gotoExcelCreateOrderUpload";
    }
}


@RequestMapping(value = "/getAllStorageOrderList")
public String getAllStorageOrderList(OrderQueryCondition cond,HttpServletRequest request,ModelMap map){
    try {
        boolean condIsEmpty = cond == null || (StringUtils.isBlank(cond.getExpressNumber()) && StringUtils.isBlank(cond.getOutOrderNumber()) && StringUtils.isBlank(cond.getReceiverName()) && StringUtils.isBlank(cond.getReceiverPhone()));
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = null;
        if (condIsEmpty) {
            orderList = this.orderService.findOrdersByCondition(StorageUtil.getCompanyId(), page);
        } else {
            orderList = this.orderService.findOrdersByCondition(StorageUtil.getCompanyId(), cond, page);
        }
        pageInfo(orderList, page, map);
        setQueryConditionToPage(map, cond);
        return "trade/storage_show/all-storage-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/storage_show/all-storage-header";
    }
}


@RequestMapping(value = "/payOrder")
public String payOrder(Integer orderId,String forwardUrl,Double paymentAmount,ModelMap map){
    if (orderId == null) {
        map.put(ACTION_ERROR, "请选择订单");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        this.orderService.payOrder(orderId, paymentAmount, StorageUtil.getUserId());
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


@RequestMapping(value = "/printExpress")
public String printExpress(HttpServletRequest request,HttpServletResponse response,String forwardUrl,ModelMap map,Integer[] orderId){
    if (orderId == null) {
        map.put(ACTION_ERROR, "订单Id为空");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        return super.export(request, response, forwardUrl, map);
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


@RequestMapping(value = "/getUnpaidList")
public String getUnpaidList(HttpServletRequest request,ModelMap map){
    try {
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = this.orderService.findOrdersByStatus(StorageUtil.getCompanyId(), PayStatus.UNPAID, null, null, page);
        pageInfo(orderList, page, map);
        return "trade/shop_show/unpaid-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/shop_show/unpaid-header";
    }
}


public String getOrderDetailVOToString(List<OrderDetailVO> list){
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++) {
        String isbn = list.get(i).getIsbn();
        if (StringUtils.isBlank(isbn) && isbn.length() > 3) {
            isbn = isbn.substring(isbn.length() - 4, isbn.length());
        }
        String bookName = list.get(i).getBookName();
        Integer bookAmount = list.get(i).getItemCount();
        String fmt = String.format("%s %s * %s", bookName, isbn, bookAmount);
        sb.append(fmt);
        if (i != 0) {
            sb.append("\n");
        }
    }
    return sb.toString();
}


@RequestMapping(value = "/unblockOrder")
public String unblockOrder(Integer orderId,String forwardUrl,String memo,ModelMap map){
    if (orderId == null) {
        map.put(ACTION_ERROR, "请选择订单");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        this.orderService.unblockOrder(orderId, StorageUtil.getUserId(), memo);
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


public String subCheckSkuInfoFileFormat(List<String> factHeader){
    String outOrderNumber = "订单编号";
    String itemPrice = "价格";
    String itemCount = "购买数量";
    String itemOutNum = "商家编码";
    if (!factHeader.get(0).equals(outOrderNumber)) {
        return "格式错误，A列必须是:" + outOrderNumber;
    }
    if (!factHeader.get(2).equals(itemPrice)) {
        return "格式错误，C列必须是:" + itemPrice;
    }
    if (!factHeader.get(3).equals(itemCount)) {
        return "格式错误，D列必须是:" + itemCount;
    }
    if (!factHeader.get(9).equals(itemOutNum)) {
        return "格式错误，J列必须是:" + itemOutNum;
    }
    return null;
}


@RequestMapping(value = "/pickingUpOrder")
public String pickingUpOrder(Integer[] orderId,String forwardUrl,ModelMap map){
    if (orderId == null) {
        map.put(ACTION_ERROR, "请选择订单");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        List<Integer> orderIds = Arrays.asList(orderId);
        this.orderService.arrangeOrderToPos(StorageUtil.getRepoId(), orderIds, StorageUtil.getUserId());
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        e.printStackTrace();
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


@Token(generate = true)
@RequestMapping(value = "/gotoExcelAddrToOrderUpload")
public String gotoExcelAddrToOrderUpload(ModelMap map){
    List<ShopInfo> shopList = this.shopService.findCompanyShop(StorageUtil.getCompanyId());
    map.put("shopList", shopList);
    return "trade/create_order/xls-address-in-order";
}


public void pageInfo(Page<OrderVO> orderList,Pageable page,ModelMap map){
    if (!orderList.getContent().isEmpty()) {
        List<OrderVO> list = orderList.getContent();
        map.put("orderList", list);
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
}


@RequestMapping(value = "/cancelArrangeOrder")
public String cancelArrangeOrder(Integer[] orderId,String forwardUrl,String memo,ModelMap map){
    if (orderId == null) {
        map.put(ACTION_ERROR, "请选择订单");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
    try {
        List<Integer> orderIds = Arrays.asList(orderId);
        for (Integer oId : orderIds) {
            this.orderService.cancelArrangeOrder(oId, StorageUtil.getUserId(), memo);
        }
        map.put(ACTION_MESSAGE, "操作成功");
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "forward:/order/" + forwardUrl + FOR_WARD_SORT;
    }
}


public Map<String,Integer> initPropSkuInfo(){
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("outOrderNumber", 0);
    map.put("itemPrice", 2);
    map.put("itemCount", 3);
    map.put("itemOutNum", 9);
    return map;
}


@Token(generate = true)
@RequestMapping(value = "/gotoExcelCreateOrderUpload")
public String gotoExcelCreateOrderUpload(ModelMap map){
    List<ShopInfo> shopList = this.shopService.findCompanyShop(StorageUtil.getCompanyId());
    map.put("shopList", shopList);
    return "trade/create_order/xls-create-order";
}


public String setExportFileName(){
    return "快递单打印" + ZisUtils.getDateString("yyyy-MM-dd") + ".xls";
}


public List<OrderInfoDTO> buildOrderInfoDTOList(InputStream input,Integer shopId){
    // 设置模板文件，用于检验导入文件是否合法
    Integer headerRownums = 1;
    try {
        // 初始化导入器
        FileImporter<OrderInfoDTO> im = new ExcelImporter<OrderInfoDTO>(input, null);
        im.setHeaderRowNums(headerRownums);
        // 检验导入文件是否合法
        String errMsg = im.validate();
        if (StringUtils.isNotBlank(errMsg)) {
            throw new RuntimeException("导入文件非法");
        }
        String subCheck = subCheckOrderInfoFileFormat(im.loadFactHeader());
        if (StringUtils.isNotBlank(subCheck)) {
            throw new RuntimeException(subCheck);
        }
        // 解析文件并入库
        Map<String, Integer> propMapping = initPropOrderInfo();
        OrderInfoDTO instance = new OrderInfoDTO();
        List<OrderInfoDTO> list = im.parse(instance, propMapping);
        if (list.isEmpty()) {
            throw new RuntimeException("导入失败，文件为空");
        }
        for (OrderInfoDTO dto : list) {
            // 只拿买家已付款数据
            if (!dto.getStatus().equals(Apply_PAID)) {
                continue;
            }
            dto.setShopId(shopId);
            dto.setOperator(StorageUtil.getUserId());
        }
        return list;
    } catch (Exception e) {
        throw new RuntimeException(e.getMessage(), e);
    }
}


public void verifyShopId(Integer shopId){
    ShopInfo shop = this.shopService.findShopByShopIdAndCompanyId(ShopUtil.getCompanyId(), shopId);
    if (shop == null) {
        throw new RuntimeException("店铺信息异常，请联系管理员");
    }
}


public boolean subOrderAllNull(SkuViewInfo so){
    if (StringUtils.isBlank(so.getIsbn()) && StringUtils.isBlank(so.getItemName()) && so.getSkuId() == null && so.getItemCount() == null && so.getItemPrice() == null) {
        return true;
    }
    return false;
}


@RequestMapping(value = "/getWaitArrangeHeaderList")
public String getWaitArrangeHeaderList(HttpServletRequest request,ModelMap map){
    try {
        Pageable page = WebHelperForOrder.buildPageRequest(request);
        Page<OrderVO> orderList = this.orderService.findOrdersByStatus(StorageUtil.getCompanyId(), null, null, StorageStatus.WAIT_ARRANGE, page);
        pageInfo(orderList, page, map);
        return "trade/shop_show/wait-arrange-header";
    } catch (Exception e) {
        map.put(ACTION_ERROR, e.getMessage());
        return "trade/shop_show/wait-arrange-header";
    }
}


}