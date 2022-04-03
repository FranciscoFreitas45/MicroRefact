package com.effective.chapter2.rule1;
 import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
public class Services {

 private  Map<String,Provider> providers;

 public  String DEFAULT_PROVIDER_NAME;

private Services() {
}
public Service newInstance(String name){
    Provider p = providers.get(name);
    if (p == null) {
        throw new IllegalArgumentException("No provider registered with name: " + name);
    }
    return p.newService();
}


public void registerProvider(String name,Provider p){
    providers.put(name, p);
}


public void registerDefaultProvider(Provider p){
    registerProvider(DEFAULT_PROVIDER_NAME, p);
}


}