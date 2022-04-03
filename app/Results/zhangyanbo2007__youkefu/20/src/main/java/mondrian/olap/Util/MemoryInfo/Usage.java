package mondrian.olap.Util.MemoryInfo;
 import mondrian.mdx;
import mondrian.olap.fun.FunUtil;
import mondrian.olap.fun.Resolver;
import mondrian.olap.type.Type;
import mondrian.resource.MondrianResource;
import mondrian.rolap;
import mondrian.spi.UserDefinedFunction;
import mondrian.util;
import org.apache.commons.collections.keyvalue.AbstractMapEntry;
import org.apache.commons.vfs;
import org.apache.commons.vfs.provider.http.HttpFileObject;
import org.apache.log4j.Logger;
import org.eigenbase.xom.XOMUtil;
import org.olap4j.impl.Olap4jUtil;
import org.olap4j.mdx;
import java.io;
import java.lang.ref.Reference;
import java.lang.reflect;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql;
import java.sql.Connection;
import java.util;
import java.util.concurrent;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public interface Usage {


public long getUsed()
;

public long getMax()
;

public long getCommitted()
;

}