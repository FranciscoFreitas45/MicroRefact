package com.lingxiang2014.util;
 import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.io.IOUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.core.io.ClassPathResource;
import com.lingxiang2014.CommonAttributes;
import com.lingxiang2014.EnumConverter;
import com.lingxiang2014.Setting;
@SuppressWarnings("unchecked")
public class SettingUtils {

 private  CacheManager cacheManager;

 private  BeanUtilsBean beanUtils;

private SettingUtils() {
}
public void set(Setting setting){
    try {
        File shopxxXmlFile = new ClassPathResource(CommonAttributes.SHOPXX_XML_PATH).getFile();
        Document document = new SAXReader().read(shopxxXmlFile);
        List<Element> elements = document.selectNodes("/shopxx/setting");
        for (Element element : elements) {
            try {
                String name = element.attributeValue("name");
                String value = beanUtils.getProperty(setting, name);
                Attribute attribute = element.attribute("value");
                attribute.setValue(value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fileOutputStream = null;
        XMLWriter xmlWriter = null;
        try {
            OutputFormat outputFormat = OutputFormat.createPrettyPrint();
            outputFormat.setEncoding("UTF-8");
            outputFormat.setIndent(true);
            outputFormat.setIndent("	");
            outputFormat.setNewlines(true);
            fileOutputStream = new FileOutputStream(shopxxXmlFile);
            xmlWriter = new XMLWriter(fileOutputStream, outputFormat);
            xmlWriter.write(document);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (xmlWriter != null) {
                try {
                    xmlWriter.close();
                } catch (IOException e) {
                }
            }
            IOUtils.closeQuietly(fileOutputStream);
        }
        Ehcache cache = cacheManager.getEhcache(Setting.CACHE_NAME);
        cache.put(new net.sf.ehcache.Element(Setting.CACHE_KEY, setting));
    } catch (Exception e) {
        e.printStackTrace();
    }
}


public Setting get(){
    Ehcache cache = cacheManager.getEhcache(Setting.CACHE_NAME);
    net.sf.ehcache.Element cacheElement = cache.get(Setting.CACHE_KEY);
    Setting setting;
    if (cacheElement != null) {
        setting = (Setting) cacheElement.getObjectValue();
    } else {
        setting = new Setting();
        try {
            File shopxxXmlFile = new ClassPathResource(CommonAttributes.SHOPXX_XML_PATH).getFile();
            Document document = new SAXReader().read(shopxxXmlFile);
            List<Element> elements = document.selectNodes("/shopxx/setting");
            for (Element element : elements) {
                String name = element.attributeValue("name");
                String value = element.attributeValue("value");
                try {
                    beanUtils.setProperty(setting, name, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cache.put(new net.sf.ehcache.Element(Setting.CACHE_KEY, setting));
    }
    return setting;
}


@SuppressWarnings("rawtypes")
@Override
public Object convert(Object value,Class targetType){
    if (super.lookup(targetType) == null) {
        if (targetType.isEnum()) {
            super.register(new EnumConverter(targetType), targetType);
        } else if (targetType.isArray() && targetType.getComponentType().isEnum()) {
            ArrayConverter arrayConverter = new ArrayConverter(targetType, new EnumConverter(targetType.getComponentType()), 0);
            arrayConverter.setOnlyFirstToString(false);
            super.register(arrayConverter, targetType);
        }
    }
    return super.convert(value, targetType);
}


}