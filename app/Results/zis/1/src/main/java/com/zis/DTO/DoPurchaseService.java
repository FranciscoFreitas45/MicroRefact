package com.zis.DTO;
 import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.zis.bookinfo.bean.Bookinfo;
import com.zis.purchase.bean.Inwarehouse;
import com.zis.purchase.bean.InwarehouseBizType;
import com.zis.purchase.bean.InwarehouseDetail;
import com.zis.purchase.bean.InwarehousePosition;
import com.zis.purchase.bean.InwarehouseStatus;
import com.zis.purchase.bean.PurchaseDetail;
import com.zis.purchase.bean.PurchasePlan;
import com.zis.purchase.bean.TempImportDetail;
import com.zis.purchase.bean.TempImportTask;
import com.zis.purchase.bean.TempImportTaskBizTypeEnum;
import com.zis.purchase.dto.InwarehouseCreateDTO;
import com.zis.purchase.dto.InwarehouseCreateResult;
import com.zis.purchase.dto.InwarehouseDealtResult;
import com.zis.purchase.dto.InwarehouseView;
import com.zis.purchase.dto.StockDTO;
import com.zis.purchase.dto.TempImportDTO;
import com.zis.purchase.dto.TempImportDetailView;
import com.zis.purchase.repository.InwarehouseDao;
import com.zis.purchase.repository.InwarehouseDetailDao;
import com.zis.purchase.repository.InwarehousePositionDao;
import com.zis.purchase.repository.PurchaseDetailDao;
import com.zis.purchase.repository.PurchasePlanDao;
import com.zis.purchase.repository.TempImportDetailDao;
import com.zis.Interface.TempImportBO;
import com.zis.Interface.TempImportDetailDao;
public class DoPurchaseService {

 private  InwarehouseDao inwarehouseDao;

 private  InwarehousePositionDao inwarehousePositionDao;

 private  InwarehouseDetailDao inwarehouseDetailDao;

 private  PurchasePlanDao purchasePlanDao;

 private  PurchaseBO purchaseBO;

 private  TempImportBO tempImportBO;

 private  InwarehouseBO inwarehouseBO;

 private  PurchaseInwarehouseBOV2 purchaseInwarehouseBO;

 private  PurchaseDetailDao purchaseDetailDao;

 private  TempImportDetailDao tempImportDetailDao;

 private  Logger logger;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://6";


public List<TempImportDetailView> findTempImportDetail(Integer taskId,String tempImportDetailStatus){
    return tempImportBO.findTempImportDetail(taskId, tempImportDetailStatus);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTempImportDetail"))

.queryParam("taskId",taskId)
.queryParam("tempImportDetailStatus",tempImportDetailStatus)
;
List<TempImportDetailView> aux = restTemplate.getForObject(builder.toUriString(),List<TempImportDetailView>.class);
return aux;
}


public List<InwarehouseDetail> findInwarehouseDetailByInwarehouseIds(Integer[] inwarehouseIds){
    return this.inwarehouseDetailDao.findByInwarehouseIds(inwarehouseIds);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findInwarehouseDetailByInwarehouseIds"))

.queryParam("inwarehouseIds",inwarehouseIds)
;
List<InwarehouseDetail> aux = restTemplate.getForObject(builder.toUriString(),List<InwarehouseDetail>.class);
return aux;
}


public List<PurchaseDetail> findPurchaseDetailByBatchId(Integer batchId){
    return this.purchaseDetailDao.findByBatchId(batchId);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findPurchaseDetailByBatchId"))

.queryParam("batchId",batchId)
;
List<PurchaseDetail> aux = restTemplate.getForObject(builder.toUriString(),List<PurchaseDetail>.class);
return aux;
}


}