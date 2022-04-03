package com.ukefu.webim.web.model;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.ukefu.core.UKDataContext;
import com.ukefu.webim.service.cache.CacheHelper;
public class UKeFuDic extends HashMap<K, V>{

 private  long serialVersionUID;

 private  UKeFuDic<Object,Object> uKeFuDic;


@SuppressWarnings("unchecked")
public List<SysDic> getDic(String code,String id){
    List<SysDic> dicList = new ArrayList<SysDic>();
    List<SysDic> sysDicList = (List<SysDic>) CacheHelper.getSystemCacheBean().getCacheObject(code, UKDataContext.SYSTEM_ORGI);
    if (sysDicList != null) {
        for (SysDic dic : sysDicList) {
            if (dic.getParentid().equals(id)) {
                dicList.add(dic);
            }
        }
    }
    return dicList;
}


public SysDic getDicItem(String id){
    return (SysDic) CacheHelper.getSystemCacheBean().getCacheObject(id, UKDataContext.SYSTEM_ORGI);
}


@SuppressWarnings("unchecked")
public List<SysDic> getSysDic(String key){
    return (List<SysDic>) CacheHelper.getSystemCacheBean().getCacheObject(key, UKDataContext.SYSTEM_ORGI);
}


@Override
@SuppressWarnings("unchecked")
public V get(Object key){
    Object obj = CacheHelper.getSystemCacheBean().getCacheObject(String.valueOf(key), UKDataContext.SYSTEM_ORGI);
    if (obj != null && obj instanceof List) {
        obj = getDic((String) key);
    } else if (obj == null && (String.valueOf(key)).endsWith(".subdic") && (String.valueOf(key)).lastIndexOf(".subdic") > 0) {
        String id = (String.valueOf(key)).substring(0, (String.valueOf(key)).lastIndexOf(".subdic"));
        SysDic dic = (SysDic) CacheHelper.getSystemCacheBean().getCacheObject(id, UKDataContext.SYSTEM_ORGI);
        if (dic != null) {
            SysDic sysDic = (SysDic) CacheHelper.getSystemCacheBean().getCacheObject(dic.getDicid(), UKDataContext.SYSTEM_ORGI);
            obj = getDic(sysDic.getCode(), dic.getParentid());
        }
    } else if (obj == null && (String.valueOf(key)).endsWith(".subdiccode") && (String.valueOf(key)).lastIndexOf(".subdiccode") > 0) {
        // 以code翻译
        String codeStr = (String.valueOf(key)).substring(0, (String.valueOf(key)).lastIndexOf(".subdiccode"));
        String parentStr = codeStr.substring(0, codeStr.lastIndexOf("."));
        String subdiccode = codeStr.substring(codeStr.lastIndexOf(".") + 1, codeStr.length());
        List<SysDic> dicList = (List) CacheHelper.getSystemCacheBean().getCacheObject(parentStr, UKDataContext.SYSTEM_ORGI);
        if (dicList != null) {
            for (SysDic s : dicList) {
                if (s.getCode().equals(subdiccode)) {
                    obj = s;
                }
            }
        }
    }
    return (V) obj;
}


public UKeFuDic<?,?> getInstance(){
    return uKeFuDic;
}


public List<SysDic> getEpt(){
    return new ArrayList<SysDic>();
}


}