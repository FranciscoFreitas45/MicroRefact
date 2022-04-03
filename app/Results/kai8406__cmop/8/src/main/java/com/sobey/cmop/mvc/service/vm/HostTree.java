package com.sobey.cmop.mvc.service.vm;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.xml.ws.BindingProvider;
import com.vmware.vim25.DynamicProperty;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.ObjectContent;
import com.vmware.vim25.ObjectSpec;
import com.vmware.vim25.PropertyFilterSpec;
import com.vmware.vim25.PropertySpec;
import com.vmware.vim25.RetrieveOptions;
import com.vmware.vim25.RetrieveResult;
import com.vmware.vim25.SelectionSpec;
import com.vmware.vim25.ServiceContent;
import com.vmware.vim25.TraversalSpec;
import com.vmware.vim25.VimPortType;
import com.vmware.vim25.VimService;
import com.vmware.vim25.VirtualDevice;
import com.vmware.vim25.VirtualDisk;
import com.vmware.vim25.VirtualHardware;
import com.vmware.vim25.VirtualMachineConfigInfo;
import com.vmware.vim25.VirtualMachineRuntimeInfo;
public class HostTree {


public ObjectSpec init(ServiceContent sContent){
    // 设置数据中心节点
    TraversalSpec datacenterHostTraversalSpec = new TraversalSpec();
    datacenterHostTraversalSpec.setName("datacenterHostTraversalSpec");
    datacenterHostTraversalSpec.setType("Datacenter");
    datacenterHostTraversalSpec.setPath("hostFolder");
    datacenterHostTraversalSpec.setSkip(false);
    SelectionSpec sSpecF = new SelectionSpec();
    sSpecF.setName("folderTraversalSpec");
    datacenterHostTraversalSpec.getSelectSet().add(sSpecF);
    // 设置文件夹节点
    TraversalSpec folderTraversalSpec = new TraversalSpec();
    folderTraversalSpec.setName("folderTraversalSpec");
    folderTraversalSpec.setType("Folder");
    folderTraversalSpec.setPath("childEntity");
    folderTraversalSpec.setSkip(false);
    folderTraversalSpec.getSelectSet().add(sSpecF);
    folderTraversalSpec.getSelectSet().add(datacenterHostTraversalSpec);
    // 设置计算资源节点
    TraversalSpec computeResourceHostTraversalSpec = new TraversalSpec();
    computeResourceHostTraversalSpec.setName("computeResourceHostTraversalSpec");
    computeResourceHostTraversalSpec.setType("ComputeResource");
    computeResourceHostTraversalSpec.setPath("host");
    computeResourceHostTraversalSpec.setSkip(false);
    folderTraversalSpec.getSelectSet().add(computeResourceHostTraversalSpec);
    ObjectSpec oSpec = new ObjectSpec();
    oSpec.setObj(sContent.getRootFolder());
    oSpec.setSkip(false);
    oSpec.getSelectSet().add(folderTraversalSpec);
    return oSpec;
}


public void checkServerTrusted(java.security.cert.X509Certificate[] certs,String authType){
    return;
}


public boolean isServerTrusted(java.security.cert.X509Certificate[] certs){
    return true;
}


public void main(String[] args){
    call();
}


public List<PropertyFilterSpec> createClusterFilterSpec(ManagedObjectReference mObj){
    ObjectSpec oSpec = new ObjectSpec();
    oSpec.setObj(mObj);
    oSpec.setSkip(Boolean.FALSE);
    TraversalSpec tSpec = new TraversalSpec();
    tSpec.setType("ClusterComputeResource");
    tSpec.setPath("host");
    tSpec.setSkip(Boolean.FALSE);
    oSpec.getSelectSet().add(tSpec);
    PropertySpec pSpec = new PropertySpec();
    pSpec.setType("HostSystem");
    pSpec.getPathSet().add("name");
    pSpec.setAll(Boolean.FALSE);
    PropertyFilterSpec pfSpec = new PropertyFilterSpec();
    pfSpec.getObjectSet().add(oSpec);
    pfSpec.getPropSet().add(pSpec);
    List<PropertyFilterSpec> fSpecList = new ArrayList<PropertyFilterSpec>();
    fSpecList.add(pfSpec);
    return fSpecList;
}


public boolean isClientTrusted(java.security.cert.X509Certificate[] certs){
    return true;
}


public List call(){
    long start = System.currentTimeMillis();
    String url = "https://172.20.0.20/sdk/vimService";
    ManagedObjectReference SVC_INST_REF = new ManagedObjectReference();
    VimService vimService;
    VimPortType vimPort;
    ServiceContent serviceContent;
    HostnameVerifier hv = new HostnameVerifier() {

        public boolean verify(String urlHostName, SSLSession session) {
            return true;
        }
    };
    javax.net.ssl.TrustManager[] trustAllCerts = new javax.net.ssl.TrustManager[1];
    javax.net.ssl.TrustManager tm = new TrustAllTrustManager();
    trustAllCerts[0] = tm;
    javax.net.ssl.SSLContext sc = javax.net.ssl.SSLContext.getInstance("SSL");
    javax.net.ssl.SSLSessionContext sslsc = sc.getServerSessionContext();
    sslsc.setSessionTimeout(0);
    sc.init(null, trustAllCerts, null);
    javax.net.ssl.HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    HttpsURLConnection.setDefaultHostnameVerifier(hv);
    SVC_INST_REF.setType("ServiceInstance");
    SVC_INST_REF.setValue("ServiceInstance");
    vimService = new VimService();
    vimPort = vimService.getVimPort();
    Map<String, Object> ctxt = ((BindingProvider) vimPort).getRequestContext();
    ctxt.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, url);
    ctxt.put(BindingProvider.SESSION_MAINTAIN_PROPERTY, true);
    serviceContent = vimPort.retrieveServiceContent(SVC_INST_REF);
    vimPort.login(serviceContent.getSessionManager(), "administrator", "Newmed!ad3v@s0bey", null);
    // List list1 = collectHostProperties(vimPort, serviceContent);
    // 云生产集群
    List list2 = collectClusterProperties(vimPort, serviceContent, "domain-c1543");
    // SMG集群
    List list3 = collectClusterProperties(vimPort, serviceContent, "domain-c1508");
    List list = new ArrayList();
    // list.add(list1);
    list.add(list2);
    list.add(list3);
    vimPort.logout(serviceContent.getSessionManager());
    System.out.println("同步耗时：" + (System.currentTimeMillis() - start) / 1000 + "s");
    return list;
}


public void checkClientTrusted(java.security.cert.X509Certificate[] certs,String authType){
    return;
}


public List collectHostProperties(VimPortType methods,ServiceContent sContent){
    ObjectSpec oSpec = init(sContent);
    PropertySpec pSpec = new PropertySpec();
    pSpec.setType("HostSystem");
    pSpec.getPathSet().add("name");
    PropertyFilterSpec spec = new PropertyFilterSpec();
    spec.getPropSet().add(pSpec);
    spec.getObjectSet().add(oSpec);
    List<PropertyFilterSpec> fSpecList = new ArrayList<PropertyFilterSpec>();
    fSpecList.add(spec);
    // 调用接口，获取查询结果
    RetrieveOptions ro = new RetrieveOptions();
    RetrieveResult hosts = methods.retrievePropertiesEx(sContent.getPropertyCollector(), fSpecList, ro);
    List<Map> hostListMap = new ArrayList<Map>();
    List<String> hostList = new ArrayList<String>();
    if (hosts != null) {
        // 解析宿主机
        int countHost = 0;
        for (ObjectContent oc : hosts.getObjects()) {
            ManagedObjectReference mor = oc.getObj();
            String host = null;
            List<DynamicProperty> dps = oc.getPropSet();
            if (dps != null) {
                for (DynamicProperty dp : dps) {
                    host = (String) dp.getVal();
                    hostList.add(host);
                    System.out.println(host);
                }
                // 查询每个宿主机下的虚拟机
                try {
                    List<PropertyFilterSpec> hostFilterSpec = createHostFilterSpec(mor);
                    RetrieveResult vms = methods.retrievePropertiesEx(sContent.getPropertyCollector(), hostFilterSpec, ro);
                    // 解析虚拟机
                    VirtualMachineConfigInfo vmConfig;
                    VirtualHardware vHardware;
                    List<VirtualDevice> vDevice;
                    VirtualDisk vDisk = null;
                    int countVm = 0;
                    String ecs;
                    List<String> ecsList = new ArrayList<String>();
                    if (vms != null) {
                        ecsList = new ArrayList<String>();
                        for (ObjectContent ocHost : vms.getObjects()) {
                            List<DynamicProperty> dpsHost = ocHost.getPropSet();
                            if (dpsHost != null) {
                                for (DynamicProperty dp : dpsHost) {
                                    if ("config".equals(dp.getName())) {
                                        vmConfig = (VirtualMachineConfigInfo) dp.getVal();
                                        vHardware = vmConfig.getHardware();
                                        vDevice = vHardware.getDevice();
                                        for (int i = 0; i < vDevice.size(); i++) {
                                            if (vDevice.get(i) instanceof VirtualDisk) {
                                                vDisk = (VirtualDisk) vDevice.get(i);
                                                break;
                                            }
                                        }
                                        ecs = vmConfig.getName();
                                        System.out.print("--" + ecs);
                                        // 过滤不符合要求的虚拟机，名称规则：IP_说明
                                        if (ecs.length() > 0 && ecs.indexOf("_") > 0) {
                                            ecs = ecs.substring(0, ecs.indexOf("_"));
                                        }
                                        if (ecs.length() > 0 && ecs.length() <= 15 && ecs.split("\\.").length == 4 && ecs.replaceAll("\\.", "").matches("[0-9]+")) {
                                            ecsList.add(ecs);
                                            System.out.println("-->" + ecs);
                                        } else {
                                            System.out.println();
                                        }
                                    }
                                }
                            }
                            countVm++;
                        }
                    }
                    Map hostMap = new HashMap();
                    hostMap.put(host, ecsList);
                    hostListMap.add(hostMap);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            countHost++;
        }
    }
    List list = new ArrayList();
    list.add(hostList);
    list.add(hostListMap);
    return list;
}


public boolean verify(String urlHostName,SSLSession session){
    return true;
}


public List collectClusterProperties(VimPortType methods,ServiceContent sContent,String cluster){
    ManagedObjectReference mor = new ManagedObjectReference();
    mor.setType("ClusterComputeResource");
    mor.setValue(cluster);
    List<PropertyFilterSpec> hostFilterSpec = createClusterFilterSpec(mor);
    RetrieveOptions ro = new RetrieveOptions();
    RetrieveResult hosts = methods.retrievePropertiesEx(sContent.getPropertyCollector(), hostFilterSpec, ro);
    List<Map> hostListMap = new ArrayList<Map>();
    List<String> hostList = new ArrayList<String>();
    StringBuffer sb = new StringBuffer();
    int countHost = 0;
    String host = "";
    for (ObjectContent ocHost : hosts.getObjects()) {
        ManagedObjectReference morHost = ocHost.getObj();
        List<DynamicProperty> dpHost = ocHost.getPropSet();
        if (dpHost != null) {
            for (DynamicProperty dp : dpHost) {
                host = (String) dp.getVal();
                hostList.add(host);
                System.out.println(host);
            }
            // 查询每个宿主机下的虚拟机
            try {
                List<PropertyFilterSpec> vmFilterSpec = createHostFilterSpec(morHost);
                RetrieveResult vms = methods.retrievePropertiesEx(sContent.getPropertyCollector(), vmFilterSpec, ro);
                VirtualMachineConfigInfo vmConfig;
                VirtualMachineRuntimeInfo vmRuntime;
                VirtualHardware vHardware;
                List<VirtualDevice> vDevice;
                VirtualDisk vDisk = null;
                int countVm = 0;
                String ecs;
                List<String> ecsList = new ArrayList<String>();
                if (vms != null) {
                    for (ObjectContent ocVm : vms.getObjects()) {
                        List<DynamicProperty> dpVms = ocVm.getPropSet();
                        if (dpVms != null) {
                            for (DynamicProperty dpVm : dpVms) {
                                if ("config".equals(dpVm.getName())) {
                                    vmConfig = (VirtualMachineConfigInfo) dpVm.getVal();
                                    vHardware = vmConfig.getHardware();
                                    vDevice = vHardware.getDevice();
                                    for (int i = 0; i < vDevice.size(); i++) {
                                        if (vDevice.get(i) instanceof VirtualDisk) {
                                            vDisk = (VirtualDisk) vDevice.get(i);
                                            break;
                                        }
                                    }
                                    ecs = vmConfig.getName();
                                    System.out.print("--" + ecs);
                                    // 过滤不符合要求的虚拟机，名称规则：IP_说明
                                    if (ecs.length() > 0 && ecs.indexOf("_") > 0) {
                                        ecs = ecs.substring(0, ecs.indexOf("_"));
                                    }
                                    if (ecs.length() > 0 && ecs.length() <= 15 && ecs.split("\\.").length == 4 && ecs.replaceAll("\\.", "").matches("[0-9]+")) {
                                        ecsList.add(ecs);
                                        System.out.println("-->" + ecs);
                                    } else {
                                        System.out.println();
                                    }
                                }
                            }
                        }
                        countVm++;
                    }
                }
                Map hostMap = new HashMap();
                hostMap.put(host, ecsList);
                hostListMap.add(hostMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countHost++;
    }
    List list = new ArrayList();
    list.add(hostList);
    list.add(hostListMap);
    return list;
}


public java.security.cert.X509Certificate[] getAcceptedIssuers(){
    return null;
}


public List<PropertyFilterSpec> createHostFilterSpec(ManagedObjectReference mObj){
    PropertySpec pSpec = new PropertySpec();
    pSpec.setAll(Boolean.FALSE);
    pSpec.setType("VirtualMachine");
    pSpec.getPathSet().add("runtime");
    pSpec.getPathSet().add("config");
    pSpec.getPathSet().add("network");
    ObjectSpec oSpec = new ObjectSpec();
    oSpec.setObj(mObj);
    oSpec.setSkip(Boolean.FALSE);
    TraversalSpec tSpec = new TraversalSpec();
    tSpec.setType("HostSystem");
    tSpec.setPath("vm");
    tSpec.setSkip(Boolean.FALSE);
    oSpec.getSelectSet().add(tSpec);
    PropertyFilterSpec pfSpec = new PropertyFilterSpec();
    pfSpec.getObjectSet().add(oSpec);
    pfSpec.getPropSet().add(pSpec);
    List<PropertyFilterSpec> fSpecList = new ArrayList<PropertyFilterSpec>();
    fSpecList.add(pfSpec);
    return fSpecList;
}


}