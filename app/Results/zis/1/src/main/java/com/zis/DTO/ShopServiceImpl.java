package com.zis.DTO;
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

 private  ShopInfoDao shopInfoDao;

 private  CompanyDao companyDao;

 private  ShopBo ShopBo;

 private  ShopItemMappingDao shopItemMappingDao;

 private  ThreadPoolTaskExecutor taskExecutor;

 private  ApiTransferFactory factory;

 private  DownloadItemLogDao downloadItemLogDao;

 private  BookService bookService;

 private  OrderService orderService;

 private  OrderDao orderDao;

 private  OrderOuterDao orderOuterDao;

 private  StorageService storageService;

 private  SimpleMailSender mailSender;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://2";


@Override
public List<String> getStatusList(){
    List<String> list = Arrays.asList(shopItemMappingStatus);
    return list;
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


@Override
public List<ShopInfo> findCompanyShop(Integer companyId){
    List<ShopInfo> list = this.shopInfoDao.findByCompanyId(companyId);
    return list;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findCompanyShop"))

.queryParam("companyId",companyId)
;
List<ShopInfo> aux = restTemplate.getForObject(builder.toUriString(),List<ShopInfo>.class);
return aux;
}


}