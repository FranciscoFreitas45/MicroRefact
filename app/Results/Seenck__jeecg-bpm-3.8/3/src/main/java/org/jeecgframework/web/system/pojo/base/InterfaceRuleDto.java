package org.jeecgframework.web.system.pojo.base;
 import java.util.List;
public class InterfaceRuleDto {

 private  String interfaceCode;

 private  String dataRule;

 private  List<TSInterfaceDdataRuleEntity> interfaceDataRule;


public String getDataRule(){
    return dataRule;
}


public void setInterfaceDataRule(List<TSInterfaceDdataRuleEntity> interfaceDataRule){
    this.interfaceDataRule = interfaceDataRule;
}


public void setInterfaceCode(String interfaceCode){
    this.interfaceCode = interfaceCode;
}


public String getInterfaceCode(){
    return interfaceCode;
}


@Override
public String toString(){
    StringBuilder builder = new StringBuilder();
    builder.append("InterfaceRuleDto [interfaceCode=");
    builder.append(interfaceCode);
    builder.append(", dataRule=");
    builder.append(dataRule);
    builder.append(", interfaceDataRule=");
    builder.append(interfaceDataRule);
    builder.append("]");
    return builder.toString();
}


public void setDataRule(String dataRule){
    this.dataRule = dataRule;
}


public List<TSInterfaceDdataRuleEntity> getInterfaceDataRule(){
    return interfaceDataRule;
}


}