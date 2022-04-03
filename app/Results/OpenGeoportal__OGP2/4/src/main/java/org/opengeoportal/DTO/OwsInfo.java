package org.opengeoportal.DTO;
 import java.util.List;
import java.util.Map;
public class OwsInfo {

 final  OwsType type;

 private OwsProtocol owsProtocol;

 private Map<String,String> infoMap;

 private OwsDescribeInfo owsDescribeInfo;


public OwsType parseOwsType(String type){
    if (type.trim().equalsIgnoreCase("data")) {
        return DATA;
    } else {
        return DISPLAY;
    }
}


public void setOwsDescribeInfo(OwsDescribeInfo owsDescribeInfo){
    this.owsDescribeInfo = owsDescribeInfo;
}


public OwsProtocol parseOwsProtocol(String protocol){
    if (protocol.trim().equalsIgnoreCase("wms")) {
        return WMS;
    } else if (protocol.trim().equalsIgnoreCase("wfs")) {
        return WFS;
    } else if (protocol.trim().equalsIgnoreCase("wcs")) {
        return WCS;
    } else {
        throw new Exception("Unrecognized Protocol: " + protocol);
    }
}


public OwsInfo findWmsInfo(List<OwsInfo> info){
    for (OwsInfo infoBit : info) {
        if (infoBit.getOwsProtocol().equals(OwsProtocol.WMS)) {
            return infoBit;
        }
    }
    throw new Exception("No WMS Info found!");
}


public OwsDescribeInfo getOwsDescribeInfo(){
    return owsDescribeInfo;
}


public OwsInfo findWfsInfo(List<OwsInfo> info){
    for (OwsInfo infoBit : info) {
        if (infoBit.getOwsProtocol().equals(OwsProtocol.WFS)) {
            return infoBit;
        }
    }
    throw new Exception("No WFS Info found!");
}


public Map<String,String> getInfoMap(){
    return infoMap;
}


public void setOwsProtocol(OwsProtocol owsProtocol){
    this.owsProtocol = owsProtocol;
}


public OwsProtocol getOwsProtocol(){
    return owsProtocol;
}


public OwsInfo findWcsInfo(List<OwsInfo> info){
    for (OwsInfo infoBit : info) {
        if (infoBit.getOwsProtocol().equals(OwsProtocol.WCS)) {
            return infoBit;
        }
    }
    throw new Exception("No WCS Info found!");
}


public void setInfoMap(Map<String,String> infoMap){
    this.infoMap = infoMap;
}


}