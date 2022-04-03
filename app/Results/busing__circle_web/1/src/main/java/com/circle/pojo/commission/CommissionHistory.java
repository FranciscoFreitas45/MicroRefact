package com.circle.pojo.commission;
 public class CommissionHistory {

 private  int id;

 private  int userId;

 private  int fromUserId;

 private  double commission;

 private  int commissionType;

 private  int circleId;

 private  String createDate;

 private  int sourceId;

/**
 * @param userId 佣金获得用户
 * @param fromUserId 佣金来源用户
 * @param commission 佣金额度
 * @param commissionType 佣金类型
 * @param circleId 圈子id
 * @param sourceId 原始来源数据id
 */
public CommissionHistory(int userId, int fromUserId, double commission, int commissionType, int circleId, int sourceId) {
    super();
    this.userId = userId;
    this.fromUserId = fromUserId;
    this.commission = commission;
    this.commissionType = commissionType;
    this.circleId = circleId;
    this.sourceId = sourceId;
}
public void setFromUserId(int fromUserId){
    this.fromUserId = fromUserId;
}


public int getId(){
    return id;
}


public int getCircleId(){
    return circleId;
}


public void setCircleId(int circleId){
    this.circleId = circleId;
}


public int getFromUserId(){
    return fromUserId;
}


public void setSourceId(int sourceId){
    this.sourceId = sourceId;
}


public double getCommission(){
    return commission;
}


public String getCreateDate(){
    return createDate;
}


public void setCreateDate(String createDate){
    this.createDate = createDate;
}


public void setId(int id){
    this.id = id;
}


public int getCommissionType(){
    return commissionType;
}


public void setCommission(double commission){
    this.commission = commission;
}


public int getSourceId(){
    return sourceId;
}


public int getUserId(){
    return userId;
}


public void setCommissionType(int commissionType){
    this.commissionType = commissionType;
}


public void setUserId(int userId){
    this.userId = userId;
}


}