package com.gbcom.common.template.xml.tpl;
 import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gbcom.common.template.xml.oem.Oem;
import com.gbcom.common.template.xml.oem.OemManager;
import com.gbcom.op.scheduler.util.JobException;
import com.gbcom.op.util.xml.XStreamUtil;
public class TplInfoManager {

 private  Logger LOG;

 private  TplInfoContext context;

 private  Map<String,List<Tplclz>> sysmodeMap;

 private  Map<String,String> jspMap;

 private  TplInfoManager instance;

private TplInfoManager() {
    sysmodeMap = new HashMap<String, List<Tplclz>>();
    jspMap = new HashMap<String, String>();
    try {
        final Class<?>[] classContext = { TplInfoContext.class, TplInfo.class, Tplclz.class };
        final URL url = Thread.currentThread().getContextClassLoader().getResource("IM/im_model_case.xml");
        context = XStreamUtil.fromXML(TplInfoContext.class, url.getFile(), classContext);
        Oem oem = OemManager.getInstance().getOem();
        for (TplInfo tpl : context.getList()) {
            for (String sysmodel : tpl.getSysmodels().split(",")) {
                List<Tplclz> sub = new ArrayList<Tplclz>();
                for (Tplclz clz : tpl.getClzList()) {
                    if (clz.getPline() != null && !clz.getPline().trim().equals("")) {
                        LOG.info("the oem pline tpl ,need extend process!, clz=" + clz.getName() + ";getPline " + clz.getPline() + ";getTab1" + clz.getTab1());
                        if (clz.getPline().equalsIgnoreCase(oem.getPline())) {
                            sub.add(clz);
                        }
                    } else {
                        sub.add(clz);
                    }
                }
                sysmodeMap.put(sysmodel.trim(), sub);
                jspMap.put(sysmodel.trim(), tpl.getJsp());
            }
        }
        LOG.info("parse file success ;;; url=" + url);
    } catch (Exception e) {
        LOG.error("parse file failed!", e);
    }
}
public List<TplInfo> getJobWrapperList(){
    return context.getList();
}


public List<Tplclz> getClzBysysmodel(String sysmodel){
    if (sysmodeMap.get(sysmodel.toUpperCase()) == null) {
        throw new Exception("配置模板不存在！");
    }
    return sysmodeMap.get(sysmodel.toUpperCase());
}


public String getJspBysysmodel(String sysmodel){
    return jspMap.get(sysmodel);
}


@SuppressWarnings("unchecked")
public void main(String[] args){
    List list = TplInfoManager.getInstance().getClzBysysmodel("wa2020");
    System.out.println(list);
    String jsp = TplInfoManager.getInstance().getJspBysysmodel("WA2020");
    System.out.println(jsp);
}


public TplInfoManager getInstance(){
    return instance;
}


public boolean isContains(String sysmodel){
    return sysmodeMap.containsKey(sysmodel) && jspMap.containsKey(sysmodel);
}


}