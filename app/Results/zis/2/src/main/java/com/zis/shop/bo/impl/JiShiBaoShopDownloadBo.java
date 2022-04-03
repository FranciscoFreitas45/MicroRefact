package com.zis.shop.bo.impl;
 import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.zis.common.excel.ExcelImporter;
import com.zis.common.excel.FileImporter;
import com.zis.shop.bean.ShopInfo;
import com.zis.shop.bean.ShopItemMapping;
import com.zis.shop.dto.ShopDownloadInterfaceDto;
import com.zis.shop.dto.ShopDownloadJiShiBaoDto;
import com.zis.shop.dto.TaoBaoImportExcelDto;
public class JiShiBaoShopDownloadBo extends AbstractShopDownloadBo{

 private  Logger logger;


public Map<String,Integer> initPropMapping(){
    Map<String, Integer> map = new HashMap<String, Integer>();
    map.put("numIid", 0);
    map.put("title", 1);
    map.put("outerId", 2);
    return map;
}


@Override
public List<ShopItemMapping> downloadItems(ShopDownloadInterfaceDto dto,ShopInfo shop){
    Integer headerRownums = 1;
    ShopDownloadJiShiBaoDto taobao = (ShopDownloadJiShiBaoDto) dto;
    InputStream in = taobao.getInputStream();
    List<ShopItemMapping> mappingList = new ArrayList<ShopItemMapping>();
    try {
        FileImporter<TaoBaoImportExcelDto> im = new ExcelImporter<TaoBaoImportExcelDto>(in, null);
        im.setHeaderRowNums(headerRownums);
        // 检验导入文件是否合法
        String errMsg = im.validate();
        if (StringUtils.isNotBlank(errMsg)) {
            logger.error(errMsg);
            addErrorDownLoadLog(shop.getShopId(), errMsg);
        }
        // 解析文件并入库
        Map<String, Integer> propMapping = initPropMapping();
        TaoBaoImportExcelDto instance = new TaoBaoImportExcelDto();
        List<TaoBaoImportExcelDto> list1 = im.parse(instance, propMapping);
        StringBuilder sb = new StringBuilder();
        if (list1.isEmpty()) {
            logger.error("解析淘宝上传文件为空");
            addErrorDownLoadLog(shop.getShopId(), "解析淘宝上传文件为空");
        }
        for (TaoBaoImportExcelDto t : list1) {
            ShopItemMapping s = null;
            try {
                s = getShopItemMapping(t, shop);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                addFailDownLoadLog(shop.getShopId(), e.getMessage());
                String msg = String.format("%s  宝贝ID：%s  %s <p/>\n", t.getTitle(), t.getNumIid(), e.getMessage());
                sb.append(msg);
                continue;
            }
            mappingList.add(s);
        }
        String[] mail = { shop.getEmails() };
        if (StringUtils.isNotBlank(sb.toString())) {
            sendFailEmail(mail, sb.toString(), shop);
        }
    } catch (Exception e1) {
        logger.error(e1.getMessage(), e1);
        addErrorDownLoadLog(shop.getShopId(), e1.getMessage());
        throw new RuntimeException(e1.getMessage(), e1);
    }
    return mappingList;
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


}