package com.lingxiang2014.dao.impl.SnDaoImpl;
 import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import com.lingxiang2014.dao.SnDao;
import com.lingxiang2014.entity.Sn;
import com.lingxiang2014.entity.Sn.Type;
import com.lingxiang2014.util.FreemarkerUtils;
import freemarker.template.TemplateException;
public class HiloOptimizer {

 private  Type type;

 private  String prefix;

 private  int maxLo;

 private  int lo;

 private  long hi;

 private  long lastValue;

public HiloOptimizer(Type type, String prefix, int maxLo) {
    this.type = type;
    this.prefix = prefix != null ? prefix.replace("{", "${") : "";
    this.maxLo = maxLo;
    this.lo = maxLo + 1;
}
public String generate(){
    if (lo > maxLo) {
        lastValue = getLastValue(type);
        lo = lastValue == 0 ? 1 : 0;
        hi = lastValue * (maxLo + 1);
    }
    try {
        return FreemarkerUtils.process(prefix, null) + (hi + lo++);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (TemplateException e) {
        e.printStackTrace();
    }
    return String.valueOf(hi + lo++);
}


}