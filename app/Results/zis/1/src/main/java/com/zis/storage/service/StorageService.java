package com.zis.storage.service;
 import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.zis.storage.dto.CreateOrderDTO;
import com.zis.storage.dto.StockDTO;
import com.zis.storage.dto.StorageLacknessOpDTO;
import com.zis.storage.entity.StorageIoBatch;
import com.zis.storage.entity.StorageIoDetail;
import com.zis.storage.entity.StorageOrder;
import com.zis.storage.entity.StoragePosStock;
import com.zis.storage.entity.StoragePosition;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.entity.StorageRepoInfo;
public interface StorageService {


public StorageOrder createOrder(CreateOrderDTO request)
;

public StorageProduct findBySkuIdAndRepoId(Integer skuId,Integer repoId)
;

public void confirmInStorage(Integer batchId,Integer operator)
;

public List<StorageIoDetail> findStorageIoDetailByRepoIdAndBatchIdInAndIoDetailTypeAndDetailStatusIn(Integer repoId,List<Integer> batchIds,String ioDetailType,List<String> DetailStatusList)
;

public StorageIoDetail pickupDoneAndLockNext(Integer ioDetailId,Integer operator)
;

public StorageLacknessOpDTO lackPart(Integer ioDetailId,Integer operator,Integer actualAmt)
;

public void cancelOrder(Integer repoId,Integer outOrderId)
;

public StoragePosition createStoragePosition(Integer repoId,String posLabel)
;

public void directSend(Integer repoId,Integer skuId,Integer amount,String posLabel,Integer operator)
;

public List<StorageProduct> findByPosStockList(List<StoragePosStock> posStockList)
;

public StoragePosition findByLabelAndRepoId(String label,Integer repoId)
;

public StorageLacknessOpDTO lackAll(Integer ioDetailId,Integer operator)
;

public Page<StorageIoDetail> findStorageIoDetailByProductIdAndPosId(Integer productId,Integer posId,Pageable page)
;

public int arrangeOrder(Integer repoId,List<Integer> outOrderIds,Integer operator)
;

public Page<StorageIoDetail> findStorageIoDetailByProductId(Integer productId,Pageable page)
;

public StorageIoDetail findByIoDetailId(Integer ioDetailId)
;

public List<StockDTO> findAllStockByProductId(Integer productId)
;

public StorageIoDetail addInStorageDetail(Integer batchId,Integer skuId,Integer amount,String posLabel,Integer operator)
;

public StorageProduct findStorageProductBySkuIdAndRepoId(Integer skuId,Integer repoId)
;

public StorageIoBatch createInStorage(Integer repoId,String memo,Integer operator)
;

public void updatePosition(Integer posId,String label,Integer repoId)
;

public List<StorageProduct> findStorageProductBySkuIdsAndRepoId(List<Integer> skuIds,Integer repoId)
;

public StoragePosition findByPosIdAndRepoId(Integer posId,Integer repoId)
;

public List<StorageProduct> findByUpdateTimeBetweenStartTimeAndEndTimeAndRepoId(Date startTime,Date endTime,Integer repoId)
;

public void directInStorage(Integer repoId,Integer skuId,Integer amount,String posLabel,Integer operator)
;

public StorageIoDetail pickupLock(Integer batchId,Integer operator)
;

public Page<StoragePosStock> findByPosId(Integer posId,Pageable page)
;

public List<StorageRepoInfo> findStorageRepoInfoByCompanyId(Integer companyId)
;

public List<Integer> finishBatchSend(Integer batchId)
;

public void cancelInStorage(Integer batchId,Integer operator)
;

public void savePosition(String label,Integer repoId)
;

}