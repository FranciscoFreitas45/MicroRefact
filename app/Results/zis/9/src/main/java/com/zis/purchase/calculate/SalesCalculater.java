package com.zis.purchase.calculate;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.zis.common.cache.SysVarCache;
import com.zis.common.cache.SysVarConstant;
import com.zis.purchase.bean.Storesales;
import com.zis.purchase.repository.StoreSalesDao;
@Component(value = "salesCalculater")
public class SalesCalculater implements BookAmountCalculateInterface{

@Autowired
 private  StoreSalesDao storeSalesDao;

@Autowired
 private  SysVarCache sysVarCache;


public Integer getSalesAmount(int bookId){
    if (bookId <= 0) {
        return 0;
    }
    List<Storesales> list = storeSalesDao.findByBookId(bookId);
    if (list == null || list.isEmpty()) {
        return 0;
    }
    return list.get(0).getSales();
}


public Integer calculate(int bookId,Integer repoId){
    Integer portio = sysVarCache.getSystemVar(SysVarConstant.PURCHASE_SALES_PORTIO.getKeyName());
    return getSalesAmount(bookId) * portio / 100;
}


}