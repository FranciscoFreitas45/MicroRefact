package org.jeecgframework.core.common.hibernate.qbc;
 import java.util.Map;
import org.omg.CORBA.PUBLIC_MEMBER;
public class PagerUtil {

public PagerUtil() {
}
public String getBar(String url,int allCounts,int curPageNO,int pageSize,Map<String,Object> map){
    Pager pager = null;
    try {
        if (curPageNO > (int) Math.ceil((double) allCounts / pageSize))
            curPageNO = (int) Math.ceil((double) allCounts / pageSize);
        if (curPageNO <= 1)
            curPageNO = 1;
        // 得到offset
        // 生成工具条
        pager = new Pager(allCounts, curPageNO, pageSize, map);
    } catch (Exception e) {
        org.jeecgframework.core.util.LogUtil.info("生成工具条出错!");
    }
    return pager.getToolBar(url);
}


public int getOffset(int rowCounts,int curPageNO,int pageSize){
    int offset = 0;
    try {
        if (curPageNO > (int) Math.ceil((double) rowCounts / pageSize))
            // update--begin--author:zhangjiaqiang date:20170408 for:修订列表页面序号错误
            curPageNO = (int) Math.ceil((double) rowCounts / pageSize) + 1;
        // update--end--author:zhangjiaqiang date:20170408 for:修订列表页面序号错误
        // 得到第几页
        if (curPageNO <= 1)
            curPageNO = 1;
        // 得到offset
        offset = (curPageNO - 1) * pageSize;
    } catch (Exception e) {
        org.jeecgframework.core.util.LogUtil.info("getOffset出错!");
    }
    return offset;
}


public int getcurPageNo(int rowCounts,int curPageNO,int pageSize){
    try {
        // 得到第几页
        if (curPageNO > (int) Math.ceil((double) rowCounts / pageSize))
            // update--begin--author:zhangjiaqiang date:20170408 for:修订列表页面序号错误
            curPageNO = (int) Math.ceil((double) rowCounts / pageSize) + 1;
        // update--end--author:zhangjiaqiang date:20170408 for:修订列表页面序号错误
        if (curPageNO <= 1)
            curPageNO = 1;
    } catch (Exception e) {
        org.jeecgframework.core.util.LogUtil.info("getOffset出错!");
    }
    return curPageNO;
}


}