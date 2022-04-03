package com.gbcom.common.template.xml.oem;
 import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gbcom.system.utils.XmlFileUtil;
public class OemManager implements Serializable{

 private  long serialVersionUID;

 private  Logger LOG;

 private  OemManager instance;

 private  String VENDOR_FILE;

 private  String VENDOR_FILE_PATH;

 private  String FILE_TYPE_XML;

 private  String FILE_LOCALE_SEPARATOR;

 private  Oem oem;

 private  Map<String,Map<String,String>> codeMap;

 private  Map<String,String> vendorModels;

/**
 * TODO description here
 */
private OemManager() {
    initOem();
}
public String getVenModel(String sysModel){
    return vendorModels.get(sysModel);
}


public String getInnerCode(String vedor,String outercode){
    if (codeMap.get(vedor) != null) {
        return codeMap.get(vedor).get(outercode);
    }
    return null;
}


public void initOem(){
    parseFile();
    /**
     * 设置语言环境 {@link config/oem/oem.xml} #<locale>zh_CN</locale>
     */
    initLocale();
    LOG.info("parse oem/vendor file Success!  /n Set JVM Default Locale Sucess");
}


public String getOuterCode(String vedor,String code){
    if (codeMap.get(vedor) != null) {
        return codeMap.get(vedor).get(code);
    }
    return null;
}


public void save(){
    saveOem();
    saveVendor();
}


public Oem getOem(){
    return oem;
}


public void saveVendor(){
    String filePath = this.getClass().getClassLoader().getResource(VENDOR_FILE_PATH + oem.getLocale() + FILE_LOCALE_SEPARATOR + oem.getName() + FILE_TYPE_XML).getFile();
    File file = new File(filePath);
    if (file.exists()) {
        try {
            XmlFileUtil.marshallerObjectToXml(oem.getVendor(), Vendor.class, file);
        } catch (Exception e) {
            LOG.error("failed to parse vendor.xml", e);
            e.printStackTrace();
        }
    } else {
        LOG.error("vendor.xml can't be found");
    }
}


public void main(String[] args){
    Oem oem = OemManager.getInstance().getOem();
    System.out.println(oem.getVendor().getVersion());
    OemManager.getInstance().getOem().getVendor().setVersion("v3.0.0.7");
    OemManager.getInstance().save();
    System.out.println(OemManager.getInstance().getOem().getVendor().getVersion());
}


public void loadOemFile(){
    String filePath = this.getClass().getClassLoader().getResource(VENDOR_FILE).getFile();
    File file = new File(filePath);
    if (file.exists()) {
        try {
            oem = XmlFileUtil.unmarshallerObjectFromXml(Oem.class, file);
        } catch (Exception e) {
            LOG.error("failed to parse vendor.xml", e);
            e.printStackTrace();
            System.exit(0);
        }
    } else {
        LOG.error("oem.xml can't be found");
        System.exit(0);
    }
}


public void parseFile(){
    loadOemFile();
    loadVendorFile();
}


public void initLocale(){
    if (oem.getLocale() != null && !oem.getLocale().equals("")) {
        String[] lanCoun = oem.getLocale().split("_");
        if (lanCoun.length == 1) {
            Locale.setDefault(new Locale(lanCoun[0]));
        } else if (lanCoun.length == 2) {
            Locale.setDefault(new Locale(lanCoun[0], lanCoun[1]));
        }
    }
}


public Map<String,String> getVendorModels(){
    return vendorModels;
}


public void saveOem(){
    String filePath = this.getClass().getClassLoader().getResource(VENDOR_FILE).getFile();
    System.out.println(filePath);
    File file = new File(filePath);
    if (file.exists()) {
        try {
            XmlFileUtil.marshallerObjectToXml(oem, Oem.class, file);
        } catch (Exception e) {
            LOG.error("failed to parse oem.xml", e);
            e.printStackTrace();
        }
    } else {
        LOG.error("oem.xml can't be found");
    }
}


public OemManager getInstance(){
    return instance;
}


public void loadVendorFile(){
    String filePath = null;
    if (oem.getPline() != null && !oem.getPline().trim().equals("")) {
        filePath = this.getClass().getClassLoader().getResource(VENDOR_FILE_PATH + oem.getLocale() + FILE_LOCALE_SEPARATOR + oem.getName() + "_" + oem.getPline() + FILE_TYPE_XML).getFile();
    } else {
        filePath = this.getClass().getClassLoader().getResource(VENDOR_FILE_PATH + oem.getLocale() + FILE_LOCALE_SEPARATOR + oem.getName() + FILE_TYPE_XML).getFile();
    }
    File file = new File(filePath);
    if (file.exists()) {
        try {
            Vendor vendor = XmlFileUtil.unmarshallerObjectFromXml(Vendor.class, file);
            for (VendorModel model : vendor.getModels()) {
                vendorModels.put(model.getSysModel(), model.getVenModel());
            }
            oem.setVendor(vendor);
        /**
         *  	<build>v3.0.8</build>
         * 	<date>20151218</date>
         * 	<version>v3.0.8</version>
         */
        // oem.setBuild(vendor.ge)
        } catch (Exception e) {
            LOG.error("failed to parse xml, file=" + filePath, e);
            System.exit(0);
        }
    } else {
        LOG.error("filePath can't be found <" + filePath + ">");
        System.exit(0);
    }
}


}