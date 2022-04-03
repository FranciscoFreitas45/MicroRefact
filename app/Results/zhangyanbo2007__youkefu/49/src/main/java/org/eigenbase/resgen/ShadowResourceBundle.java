package org.eigenbase.resgen;
 import java.io.IOException;
import java.io.InputStream;
import java.util;
public class ShadowResourceBundle extends ResourceBundle{

 private  PropertyResourceBundle bundle;

 private  ThreadLocal mapThreadToLocale;

 protected  Object[] emptyObjectArray;

/**
 * Creates a <code>ShadowResourceBundle</code>, and reads resources from
 * a <code>.properties</code> file with the same name as the current class.
 * For example, if the class is called <code>foo.MyResource_en_US</code>,
 * reads from <code>foo/MyResource_en_US.properties</code>, then
 * <code>foo/MyResource_en.properties</code>, then
 * <code>foo/MyResource.properties</code>.
 */
protected ShadowResourceBundle() throws IOException {
    super();
    Class clazz = getClass();
    InputStream stream = openPropertiesFile(clazz);
    if (stream == null) {
        throw new IOException("could not open properties file for " + getClass());
    }
    MyPropertyResourceBundle previousBundle = new MyPropertyResourceBundle(stream);
    bundle = previousBundle;
    stream.close();
    // Now load properties files for parent locales, which we deduce from
    // the names of our super-class, and its super-class.
    while (true) {
        clazz = clazz.getSuperclass();
        if (clazz == null || clazz == ShadowResourceBundle.class || !ResourceBundle.class.isAssignableFrom(clazz)) {
            break;
        }
        stream = openPropertiesFile(clazz);
        if (stream == null) {
            continue;
        }
        MyPropertyResourceBundle newBundle = new MyPropertyResourceBundle(stream);
        stream.close();
        if (previousBundle != null) {
            previousBundle.setParentTrojan(newBundle);
        } else {
            bundle = newBundle;
        }
        previousBundle = newBundle;
    }
}
public Locale getThreadLocale(){
    return (Locale) mapThreadToLocale.get();
}


public Object handleGetObject(String key){
    return bundle.getObject(key);
}


public ShadowResourceBundle instance(String baseName,Locale locale,ResourceBundle bundle){
    if (bundle instanceof PropertyResourceBundle) {
        throw new ClassCastException("ShadowResourceBundle.instance('" + baseName + "','" + locale + "') found " + baseName + "_" + locale + ".properties but not " + baseName + "_" + locale + ".class");
    }
    return (ShadowResourceBundle) bundle;
}


public InputStream openPropertiesFile(Class clazz){
    final ClassLoader loader = clazz.getClassLoader();
    final String resName = clazz.getName().replace('.', '/') + ".properties";
    return (InputStream) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction() {

        public Object run() {
            if (loader != null) {
                return loader.getResourceAsStream(resName);
            } else {
                return ClassLoader.getSystemResourceAsStream(resName);
            }
        }
    });
}


public void setThreadLocale(Locale locale){
    mapThreadToLocale.set(locale);
}


public void setParentTrojan(ResourceBundle parent){
    super.setParent(parent);
}


public Object run(){
    if (loader != null) {
        return loader.getResourceAsStream(resName);
    } else {
        return ClassLoader.getSystemResourceAsStream(resName);
    }
}


public Enumeration getKeys(){
    return bundle.getKeys();
}


public Locale getThreadOrDefaultLocale(){
    Locale locale = getThreadLocale();
    if (locale == null) {
        return Locale.getDefault();
    } else {
        return locale;
    }
}


}