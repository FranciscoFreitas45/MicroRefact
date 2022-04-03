package com.kingen.service.supplier;
 import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import com.google.common.collect.Maps;
import com.kingen.bean.Lookup;
import com.kingen.bean.Supplier;
import com.kingen.service.CommonService;
import com.kingen.util.Page;
@Component
@Transactional
public class SupplierService extends CommonService<Supplier, String>{

 private  Logger logger;


public Page<Supplier> data(Page<Supplier> page,String type){
    Map<String, Object> params = Maps.newHashMap();
    // params.put("type", type);
    return find(page, params);
}


}