package com.zis.trade.dto;
 import java.util.Date;
import java.util.List;
import com.zis.trade.entity.Order;
import com.zis.trade.entity.OrderDetail;
import com.zis.trade.processor.OrderHelper;
public class OrderVO extends Order{

 private  String payStatusDisplay;

 private  String storageStatusDisplay;

 private  String expressStatusDisplay;

 private  List<String> outOrderNumbers;

 private  List<OrderDetailVO> orderDetailVOs;

 private  Integer bookId;

 private  String isbn;

 private  String bookName;

 private  String bookAuthor;

 private  String bookPublisher;

 private  Date publishDate;

 private  Double bookPrice;

 private  String bookEdition;

 private  Boolean isNewEdition;


public void setOrderDetailVOs(List<OrderDetailVO> orderDetailVOs){
    this.orderDetailVOs = orderDetailVOs;
}


public boolean canCancelRefund(){
    return OrderHelper.canCancelRefund(this);
}


public void setBookPrice(Double bookPrice){
    this.bookPrice = bookPrice;
}


public void setPublishDate(Date publishDate){
    this.publishDate = publishDate;
}


public Date getPublishDate(){
    return publishDate;
}


public List<String> getOutOrderNumbers(){
    return outOrderNumbers;
}


public boolean canArrangeOrderToRepo(){
    return OrderHelper.canArrangeOrderToRepo(this);
}


public void setStorageStatusDisplay(String storageStatusDisplay){
    this.storageStatusDisplay = storageStatusDisplay;
}


public void setBookName(String bookName){
    this.bookName = bookName;
}


public boolean canCancelOrder(){
    return OrderHelper.canCancelOrder(this);
}


public boolean canFinishSend(){
    return OrderHelper.canFinishSend(this);
}


public boolean canPrint(){
    return OrderHelper.canPrint(this);
}


public String getUniqueStatusDisplay(String tabType){
    // 未支付、退款中，显示资金状态
    if (PayStatus.UNPAID.getValue().equals(tabType) || PayStatus.REFUNDING.getValue().equals(tabType)) {
        return this.getPayStatusDisplay();
    }
    // 等待分配仓库，显示资金状态+配货状态
    if (StorageStatus.isWaitForArrange(tabType)) {
        return generateStatus(getPayStatusDisplay(), getStorageStatusDisplay());
    }
    // 等待配货、配货中、等待打印、已打印，显示配货状态+物流状态
    if (StorageStatus.ARRANGED.getValue().equals(tabType) || StorageStatus.PICKUP.getValue().equals(tabType) || ExpressStatus.WAIT_FOR_PRINT.getValue().equals(tabType) || ExpressStatus.PRINTED.getValue().equals(tabType)) {
        return generateStatus(getStorageStatusDisplay(), getExpressStatusDisplay());
    }
    return generateStatus(getPayStatusDisplay(), getStorageStatusDisplay(), getExpressStatusDisplay());
}


public void setExpressStatusDisplay(String expressStatusDisplay){
    this.expressStatusDisplay = expressStatusDisplay;
}


public void setBookPublisher(String bookPublisher){
    this.bookPublisher = bookPublisher;
}


public boolean canLackness(){
    return OrderHelper.canLackness(this);
}


public void setBookId(Integer bookId){
    this.bookId = bookId;
}


public void setIsbn(String isbn){
    this.isbn = isbn;
}


public String getBookAuthor(){
    return bookAuthor;
}


public String getExpressStatusDisplay(){
    return expressStatusDisplay;
}


public boolean canFillExpressNumber(){
    return OrderHelper.canFillExpressNumber(this);
}


public boolean canPay(){
    return OrderHelper.canPay(this);
}


public String getShopNameDisplay(){
    return String.format("[%s]%s", this.getpName(), this.getShopName());
}


public boolean canBlock(){
    return OrderHelper.canBlock(this);
}


public void setBookEdition(String bookEdition){
    this.bookEdition = bookEdition;
}


public String getPayStatusDisplay(){
    return payStatusDisplay;
}


public void setBookAuthor(String bookAuthor){
    this.bookAuthor = bookAuthor;
}


public boolean canChangeOrderAddress(){
    return OrderHelper.canChangeOrderAddress(this);
}


public boolean canChangeItems(){
    return OrderHelper.canChangeItems(this);
}


public String getBookName(){
    return bookName;
}


public void setIsNewEdition(Boolean isNewEdition){
    this.isNewEdition = isNewEdition;
}


public Double getBookPrice(){
    return bookPrice;
}


public boolean canCancelArrangeOrder(){
    return OrderHelper.canCancelArrangeOrder(this);
}


public boolean canSplit(){
    return OrderHelper.canSplit(this);
}


public String canSendOutWithMessage(){
    return OrderHelper.canSendOutWithMessage(this);
}


public boolean canSendOut(){
    return OrderHelper.canSendOut(this);
}


public String getBookEdition(){
    return bookEdition;
}


public String getIsbn(){
    return isbn;
}


public boolean canCombined(String newOrderPayStatus){
    return OrderHelper.canCombined(this, newOrderPayStatus);
}


public boolean canUnblock(){
    return OrderHelper.canUnblock(this);
}


public String getStorageStatusDisplay(){
    return storageStatusDisplay;
}


public boolean canArrangeOrderToPos(){
    return OrderHelper.canArrangeOrderToPos(this);
}


public List<OrderDetailVO> getOrderDetailVOs(){
    return orderDetailVOs;
}


public boolean canApplyRefund(){
    return OrderHelper.canApplyRefund(this);
}


public Boolean getIsNewEdition(){
    return isNewEdition;
}


public String generateStatus(String args){
    StringBuilder builder = new StringBuilder();
    for (String str : args) {
        builder.append(str).append("<br/>");
    }
    return builder.substring(0, builder.length() - 5).toString();
}


public void setPayStatusDisplay(String payStatusDisplay){
    this.payStatusDisplay = payStatusDisplay;
}


public Integer getBookId(){
    return bookId;
}


public void setOutOrderNumbers(List<String> outOrderNumbers){
    this.outOrderNumbers = outOrderNumbers;
}


public boolean canAgreeRefund(){
    return OrderHelper.canAgreeRefund(this);
}


public String getBookPublisher(){
    return bookPublisher;
}


}