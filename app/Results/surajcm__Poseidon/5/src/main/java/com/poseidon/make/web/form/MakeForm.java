package com.poseidon.make.web.form;
 import com.poseidon.make.domain.MakeAndModelVO;
import com.poseidon.make.domain.MakeVO;
import java.util.List;
import java.util.StringJoiner;
public class MakeForm {

 private  List<MakeAndModelVO> makeAndModelVOs;

 private  MakeAndModelVO currentMakeAndModeVO;

 private  MakeAndModelVO searchMakeAndModelVO;

 private  String loggedInRole;

 private  String loggedInUser;

 private  Long id;

 private  MakeVO makeVO;

 private  List<MakeVO> makeVOs;

 private  String statusMessage;

 private  String statusMessageType;


public void setCurrentMakeAndModeVO(MakeAndModelVO currentMakeAndModeVO){
    this.currentMakeAndModeVO = currentMakeAndModeVO;
}


public void setStatusMessage(String statusMessage){
    this.statusMessage = statusMessage;
}


public String getStatusMessage(){
    return statusMessage;
}


public List<MakeAndModelVO> getMakeAndModelVOs(){
    return makeAndModelVOs;
}


public void setLoggedInRole(String loggedInRole){
    this.loggedInRole = loggedInRole;
}


public Long getId(){
    return id;
}


public void setLoggedInUser(String loggedInUser){
    this.loggedInUser = loggedInUser;
}


public void setStatusMessageType(String statusMessageType){
    this.statusMessageType = statusMessageType;
}


public void setMakeAndModelVOs(List<MakeAndModelVO> makeAndModelVOs){
    this.makeAndModelVOs = makeAndModelVOs;
}


public void setMakeVOs(List<MakeVO> makeVOs){
    this.makeVOs = makeVOs;
}


public MakeAndModelVO getSearchMakeAndModelVO(){
    return searchMakeAndModelVO;
}


public String getStatusMessageType(){
    return statusMessageType;
}


public void setSearchMakeAndModelVO(MakeAndModelVO searchMakeAndModelVO){
    this.searchMakeAndModelVO = searchMakeAndModelVO;
}


public String getLoggedInUser(){
    return loggedInUser;
}


public void setId(Long id){
    this.id = id;
}


@Override
public String toString(){
    return new StringJoiner(", ", MakeForm.class.getSimpleName() + "[", "]").add("makeAndModelVOs=" + makeAndModelVOs).add("currentMakeAndModeVO=" + currentMakeAndModeVO).add("searchMakeAndModelVO=" + searchMakeAndModelVO).add("loggedInRole='" + loggedInRole + "'").add("loggedInUser='" + loggedInUser + "'").add("id=" + id).add("makeVO=" + makeVO).add("makeVOs=" + makeVOs).add("statusMessage='" + statusMessage + "'").add("statusMessageType='" + statusMessageType + "'").toString();
}


public MakeAndModelVO getCurrentMakeAndModeVO(){
    return currentMakeAndModeVO;
}


public List<MakeVO> getMakeVOs(){
    return makeVOs;
}


public String getLoggedInRole(){
    return loggedInRole;
}


public void setMakeVO(MakeVO makeVO){
    this.makeVO = makeVO;
}


public MakeVO getMakeVO(){
    return makeVO;
}


}