package cn.maxcj.core.beetl;
 import cn.maxcj.core.tag.DictSelectorTag;
import cn.maxcj.core.util.KaptchaUtil;
import cn.stylefeng.roses.core.util.ToolUtil;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
public class BeetlConfiguration extends BeetlGroupUtilConfiguration{

@Autowired
 private  Environment env;

@Autowired
 private  DictSelectorTag dictSelectorTag;


@Override
public String call(Object[] paras,Context ctx){
    String key = (String) paras[0];
    String value = env.getProperty(key);
    if (value != null) {
        return getStr(value);
    }
    if (paras.length == 2) {
        return (String) paras[1];
    }
    return null;
}


@Override
public void initOther(){
    groupTemplate.registerFunctionPackage("shiro", new ShiroExt());
    groupTemplate.registerFunctionPackage("tool", new ToolUtil());
    groupTemplate.registerFunctionPackage("kaptcha", new KaptchaUtil());
    groupTemplate.registerTagFactory("dictSelector", () -> dictSelectorTag);
    groupTemplate.registerFunction("env", new Function() {

        @Override
        public String call(Object[] paras, Context ctx) {
            String key = (String) paras[0];
            String value = env.getProperty(key);
            if (value != null) {
                return getStr(value);
            }
            if (paras.length == 2) {
                return (String) paras[1];
            }
            return null;
        }

        String getStr(String str) {
            try {
                return new String(str.getBytes("iso8859-1"), StandardCharsets.UTF_8);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    });
}


public String getStr(String str){
    try {
        return new String(str.getBytes("iso8859-1"), StandardCharsets.UTF_8);
    } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
    }
}


}