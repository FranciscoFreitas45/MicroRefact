package com.zis.storage.controller;
 import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import com.zis.storage.dto.FastTakeGoodsView;
import com.zis.storage.dto.StockDTO;
import com.zis.storage.dto.TakeGood;
import com.zis.storage.entity.StorageProduct;
import com.zis.storage.service.StorageService;
import com.zis.storage.util.StorageUtil;
@Controller
public class FastTakeGoodsController {

@Autowired
 private  StorageService storageService;


public FastTakeGoodsView gotoHaveAmountGoods(Integer skuId,String bookInfoStr,Integer amount){
    try {
        if (skuId == null) {
            throw new RuntimeException("skuId为空");
        }
        // 查询库存
        StorageProduct storageProd = this.storageService.findStorageProductBySkuIdAndRepoId(skuId, StorageUtil.getRepoId());
        if (storageProd == null) {
            throw new RuntimeException("仓库中未找到此商品");
        }
        List<StockDTO> list = this.storageService.findAllStockByProductId(storageProd.getProductId());
        if (CollectionUtils.isEmpty(list)) {
            throw new RuntimeException("库位中未找到商品");
        }
        FastTakeGoodsView view = new FastTakeGoodsView();
        List<TakeGood> takeList = new ArrayList<TakeGood>();
        for (StockDTO dto : list) {
            Integer takeAmount = dto.getTotalAmt() - dto.getOccupyAmt();
            if ((amount == null && takeAmount > 0) || (amount != null && takeAmount >= amount)) {
                TakeGood good = new TakeGood();
                good.setAmount(takeAmount);
                good.setPosLabel(dto.getPosLabel());
                takeList.add(good);
            }
        }
        if (takeList.isEmpty()) {
            throw new RuntimeException("库位中未找到您需要数量的商品");
        }
        // 如果大于20条记录只取前20条
        if (takeList.size() > 20) {
            view.setGoods(takeList.subList(0, 20));
        } else {
            view.setGoods(takeList);
        }
        view.setBookInfoStr(bookInfoStr);
        view.setSkuId(skuId);
        view.setSuccess(true);
        return view;
    } catch (Exception e) {
        FastTakeGoodsView view = new FastTakeGoodsView();
        view.setSuccess(false);
        view.setFailReason(e.getMessage());
        return view;
    }
}


}