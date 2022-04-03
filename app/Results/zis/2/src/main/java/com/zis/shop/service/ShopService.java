package com.zis.shop.service;
 import java.io.InputStream;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.zis.shop.bean.Company;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.dto.SaveOrUpdateCompanyDto;
import com.zis.shop.dto.SaveOrUpdateShopDto;
import com.zis.shop.dto.ShopDownLoadFailDTO;
import com.zis.trade.dto.ExpressNumberDTO;
public interface ShopService {


public List<ShopDownLoadFailDTO> prcessDownLoadMappingData(List<ShopItemMapping> mappingList,ShopInfo shop)
;

public void updateShop(SaveOrUpdateShopDto dto)
;

public List<Company> queryAllCompany()
;

public Page<ShopItemMapping> queryShopItemMapping(Integer shopId,String status,String isbn,Pageable page)
;

public ShopInfo findShopByShopIdAndCompanyId(Integer companyId,Integer shopId)
;

public void updateCompany(SaveOrUpdateCompanyDto dto)
;

public void queryApplyRefundForShopIdAndDate(Integer shopId,Date startTime,Date endTime)
;

public void initShopMapping(Integer bookId,ShopInfo shop)
;

public Company findCompanyOne(Integer companyId)
;

public void saveCompany(SaveOrUpdateCompanyDto dto)
;

public Integer failAddItem2Shop(Integer mappingId,ShopInfo shop)
;

public ShopInfo findShopById(Integer shopId)
;

public List<String> getStatusList()
;

public List<ShopItemMapping> taobaoExeclToMapping(InputStream input,ShopInfo shop)
;

public void logisticsOfflineSend(List<ExpressNumberDTO> list)
;

public Page<Company> queryCompany(String companyName,String contacts,Pageable page)
;

public void saveShop(SaveOrUpdateShopDto dto)
;

public List<ShopInfo> queryAllShop()
;

public void asynchronousPrcessDownLoadMappingDataAndSendEmail(List<ShopItemMapping> mappingList,ShopInfo shop)
;

public Integer addItem2Shop(Integer mappingId,ShopInfo shop)
;

public List<ShopInfo> findCompanyShop(Integer companyId)
;

public String deleteShop(Integer companyId,Integer shopId)
;

public void stockChangeToShopUPdateItem(Integer companyId,Integer bookId,Integer amount)
;

public void createOrderForShopIdAndDate(Integer shopId,Date startTime,Date endTime)
;

public ShopInfo verifyShopId(Integer shopId)
;

}