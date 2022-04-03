package com.uec.imonitor.es.bean;
 import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component("esClient")
public class EsClient {

 private  Logger logger;

@Value("${imonitor.es.cluster.name}")
 private  String clusterName;

@Value("${imonitor.es.cluster.ips}")
 private  String clusterIps;

@Value("${imonitor.es.cluster.port}")
 private  String clusterPort;

 private  Map<String,String> m;

 private  TransportClient client;


public void init(){
    // 设置client.transport.sniff为true来使客户端去嗅探整个集群的状态，把集群中其它机器的ip地址加到客户端中，
    Settings settings = Settings.settingsBuilder().put(m).put("cluster.name", clusterName).put("client.transport.sniff", true).build();
    try {
        this.client = TransportClient.builder().settings(settings).build();
        String[] clusterIpArray = clusterIps.split(";|；");
        for (int i = 0; i < clusterIpArray.length; i++) {
            this.client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(clusterIpArray[i]), Integer.parseInt(clusterPort)));
        }
    } catch (Exception e) {
        logger.error("TransportClient客户端实例化异常：" + e);
    }
}


public TransportClient getTransportClient(){
    if (null == client) {
        init();
    }
    return client;
}


}