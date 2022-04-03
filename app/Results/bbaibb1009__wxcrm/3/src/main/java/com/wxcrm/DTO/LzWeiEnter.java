package com.wxcrm.DTO;
 import java.util.List;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
public class LzWeiEnter {

 private  long serialVersionUID;

 private  Integer wecId;

 private  String wecAppName;

 private  String wecAppId;

 private  String wecAppSecret;

 private  String wecRederectUrl;

 private  String wecToken;

 private  String wecEncodingAesKey;

 private  String wecAesType;

 private  String wecAppType;

 private  String wecCusType;

 private  String wecAccountType;

 private  Integer wecEnterId;

 private  String wecStatus;

 private  String wecDesc;

 private  Integer wecRegistor;

 private  String wecRegistdate;

 private  Integer wecDefaultMsg;

 private  Integer wecSubscribeMsg;

 private  String wecDefaultMsgDesc;

 private  String wecSubscribeMsgDesc;

 private  String currentPage;

 private  String pageSize;

 private  String[] wecIds;

 private  List<Map<String,Object>> listKeyWords;

 private  String wecAppName_Q;

 private  String wecAppId_Q;

 private  String wecAesType_Q;

 private  String wecAppType_Q;

 private  String wecCusType_Q;

 private  String wecAccountType_Q;

 private  String wecEnterName_Q;

 private  String wecCustOpenId_Q;

 private  String wetOpenId_Q;

// Constructors
/**
 * default constructor
 */
public LzWeiEnter() {
}/**
 * minimal constructor
 */
public LzWeiEnter(Integer wecId, String wecAppId, String wecAppSecret) {
    this.wecId = wecId;
    this.wecAppId = wecAppId;
    this.wecAppSecret = wecAppSecret;
}/**
 * full constructor
 */
public LzWeiEnter(Integer wecId, String wecAppName, String wecAppId, String wecAppSecret, String wecRederectUrl, String wecToken, String wecEncodingAesKey, String wecAesType, String wecAppType, String wecCusType, String wecAccountType, Integer wecEnterId, String wecStatus, String wecDesc, Integer wecRegistor, String wecRegistdate) {
    this.wecId = wecId;
    this.wecAppName = wecAppName;
    this.wecAppId = wecAppId;
    this.wecAppSecret = wecAppSecret;
    this.wecRederectUrl = wecRederectUrl;
    this.wecToken = wecToken;
    this.wecEncodingAesKey = wecEncodingAesKey;
    this.wecAesType = wecAesType;
    this.wecAppType = wecAppType;
    this.wecCusType = wecCusType;
    this.wecAccountType = wecAccountType;
    this.wecEnterId = wecEnterId;
    this.wecStatus = wecStatus;
    this.wecDesc = wecDesc;
    this.wecRegistor = wecRegistor;
    this.wecRegistdate = wecRegistdate;
}
@Column(name = "WEC_APP_TYPE", length = 1)
public String getWecAppType(){
    return this.wecAppType;
}


@Column(name = "WEC_TOKEN", length = 200)
public String getWecToken(){
    return this.wecToken;
}


@Transient
public String getWetOpenId_Q(){
    return wetOpenId_Q;
}


@Column(name = "WEC_REGISTDATE", length = 23)
public String getWecRegistdate(){
    return this.wecRegistdate;
}


@Column(name = "WEC_ENCODING_AES_KEY", length = 200)
public String getWecEncodingAesKey(){
    return this.wecEncodingAesKey;
}


@Transient
public String getWecAppId_Q(){
    return wecAppId_Q;
}


@Transient
public String getWecCustOpenId_Q(){
    return wecCustOpenId_Q;
}


@Column(name = "WEC_CUS_TYPE", length = 1)
public String getWecCusType(){
    return this.wecCusType;
}


@Transient
public String getWecAesType_Q(){
    return wecAesType_Q;
}


@Column(name = "WEC_APP_ID", nullable = false, length = 80)
public String getWecAppId(){
    return this.wecAppId;
}


@Column(name = "WEC_REDERECT_URL", length = 100)
public String getWecRederectUrl(){
    return this.wecRederectUrl;
}


@Transient
public String getPageSize(){
    return pageSize;
}


@Transient
public String getWecSubscribeMsgDesc(){
    return wecSubscribeMsgDesc;
}


@Column(name = "WEC_ENTER_ID")
public Integer getWecEnterId(){
    return this.wecEnterId;
}


@Transient
public String getWecEnterName_Q(){
    return wecEnterName_Q;
}


@Column(name = "WEC_STATUS", length = 20)
public String getWecStatus(){
    return this.wecStatus;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "WEC_ID", unique = true, nullable = false)
public Integer getWecId(){
    return this.wecId;
}


@Column(name = "WEC_AES_TYPE", length = 1)
public String getWecAesType(){
    return this.wecAesType;
}


@Transient
public List<Map<String,Object>> getListKeyWords(){
    return listKeyWords;
}


@Column(name = "WEC_REGISTOR")
public Integer getWecRegistor(){
    return this.wecRegistor;
}


@Transient
public String getWecAppType_Q(){
    return wecAppType_Q;
}


@Transient
public String getWecCusType_Q(){
    return wecCusType_Q;
}


@Transient
public String getWecAppName_Q(){
    return wecAppName_Q;
}


@Transient
public String getCurrentPage(){
    return currentPage;
}


@Column(name = "WEC_DEFAULT_MSG")
public Integer getWecDefaultMsg(){
    return wecDefaultMsg;
}


@Transient
public String getWecDefaultMsgDesc(){
    return wecDefaultMsgDesc;
}


@Column(name = "WEC_DESC", length = 200)
public String getWecDesc(){
    return this.wecDesc;
}


@Transient
public long getSerialVersionUID(){
    return serialVersionUID;
}


@Column(name = "WEC_APP_NAME", length = 100)
public String getWecAppName(){
    return this.wecAppName;
}


@Transient
public String getWecAccountType_Q(){
    return wecAccountType_Q;
}


@Column(name = "WEC_APP_SECRET", nullable = false, length = 100)
public String getWecAppSecret(){
    return this.wecAppSecret;
}


@Column(name = "WEC_ACCOUNT_TYPE", length = 1)
public String getWecAccountType(){
    return this.wecAccountType;
}


@Column(name = "WEC_SUBSCRIBE_MSG")
public Integer getWecSubscribeMsg(){
    return wecSubscribeMsg;
}


@Transient
public String[] getWecIds(){
    return wecIds;
}


}