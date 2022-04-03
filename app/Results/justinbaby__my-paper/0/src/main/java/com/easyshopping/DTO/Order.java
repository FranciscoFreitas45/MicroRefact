package com.easyshopping.DTO;
 import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.easyshopping.Setting;
import com.easyshopping.util.SettingUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
public class Order extends BaseEntity{

 private  long serialVersionUID;

 private  String NAME_SEPARATOR;

 private  String sn;

 private  OrderStatus orderStatus;

 private  PaymentStatus paymentStatus;

 private  ShippingStatus shippingStatus;

 private  BigDecimal fee;

 private  BigDecimal freight;

 private  BigDecimal promotionDiscount;

 private  BigDecimal couponDiscount;

 private  BigDecimal offsetAmount;

 private  BigDecimal amountPaid;

 private  Long point;

 private  String consignee;

 private  String areaName;

 private  String address;

 private  String zipCode;

 private  String phone;

 private  Boolean isInvoice;

 private  String invoiceTitle;

 private  BigDecimal tax;

 private  String memo;

 private  String promotion;

 private  Date expire;

 private  Date lockExpire;

 private  Boolean isAllocatedStock;

 private  String paymentMethodName;

 private  String shippingMethodName;

 private  Area area;

 private  PaymentMethod paymentMethod;

 private  ShippingMethod shippingMethod;

 private  Admin operator;

 private  Member member;

 private  CouponCode couponCode;

 private  List<Coupon> coupons;

 private  List<OrderItem> orderItems;

 private  Set<OrderLog> orderLogs;

 private  Set<Deposit> deposits;

 private  Set<Payment> payments;

 private  Set<Refunds> refunds;

 private  Set<Shipping> shippings;

 private  Set<Returns> returns;


public void setZipCode(String zipCode){
    this.zipCode = zipCode;
}


@Transient
public String getName(){
    StringBuffer name = new StringBuffer();
    if (getOrderItems() != null) {
        for (OrderItem orderItem : getOrderItems()) {
            if (orderItem != null && orderItem.getFullName() != null) {
                name.append(NAME_SEPARATOR).append(orderItem.getFullName());
            }
        }
        if (name.length() > 0) {
            name.deleteCharAt(0);
        }
    }
    return name.toString();
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getZipCode(){
    return zipCode;
}


public void setCouponCode(CouponCode couponCode){
    this.couponCode = couponCode;
}


@Transient
public int getWeight(){
    int weight = 0;
    if (getOrderItems() != null) {
        for (OrderItem orderItem : getOrderItems()) {
            if (orderItem != null) {
                weight += orderItem.getTotalWeight();
            }
        }
    }
    return weight;
}


@Length(max = 200)
public String getInvoiceTitle(){
    return invoiceTitle;
}


@PrePersist
public void prePersist(){
    if (getArea() != null) {
        setAreaName(getArea().getFullName());
    }
    if (getPaymentMethod() != null) {
        setPaymentMethodName(getPaymentMethod().getName());
    }
    if (getShippingMethod() != null) {
        setShippingMethodName(getShippingMethod().getName());
    }
}


@Column(nullable = false, updatable = false, unique = true, length = 100)
public String getSn(){
    return sn;
}


@Transient
public BigDecimal calculateTax(){
    BigDecimal tax = new BigDecimal(0);
    Setting setting = SettingUtils.get();
    if (setting.getIsTaxPriceEnabled()) {
        BigDecimal amount = getPrice();
        if (getPromotionDiscount() != null) {
            amount = amount.subtract(getPromotionDiscount());
        }
        if (getCouponDiscount() != null) {
            amount = amount.subtract(getCouponDiscount());
        }
        if (getOffsetAmount() != null) {
            amount = amount.add(getOffsetAmount());
        }
        tax = amount.multiply(new BigDecimal(setting.getTaxRate().toString()));
    }
    return setting.setScale(tax);
}


@Column(nullable = false)
public ShippingStatus getShippingStatus(){
    return shippingStatus;
}


public void setMember(Member member){
    this.member = member;
}


public void setPaymentMethodName(String paymentMethodName){
    this.paymentMethodName = paymentMethodName;
}


@Transient
public boolean isLocked(Admin operator){
    return getLockExpire() != null && new Date().before(getLockExpire()) && ((operator != null && !operator.equals(getOperator())) || (operator == null && getOperator() != null));
}


public void setOperator(Admin operator){
    this.operator = operator;
}


public void setCoupons(List<Coupon> coupons){
    this.coupons = coupons;
}


@ManyToOne(fetch = FetchType.LAZY)
public Admin getOperator(){
    return operator;
}


@OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
public Set<Deposit> getDeposits(){
    return deposits;
}


@Transient
public BigDecimal getAmount(){
    BigDecimal amount = getPrice();
    if (getFee() != null) {
        amount = amount.add(getFee());
    }
    if (getFreight() != null) {
        amount = amount.add(getFreight());
    }
    if (getPromotionDiscount() != null) {
        amount = amount.subtract(getPromotionDiscount());
    }
    if (getCouponDiscount() != null) {
        amount = amount.subtract(getCouponDiscount());
    }
    if (getOffsetAmount() != null) {
        amount = amount.add(getOffsetAmount());
    }
    if (getTax() != null) {
        amount = amount.add(getTax());
    }
    return amount.compareTo(new BigDecimal(0)) > 0 ? amount : new BigDecimal(0);
}


public void setConsignee(String consignee){
    this.consignee = consignee;
}


@NotNull
@Min(0)
@Column(nullable = false)
public Long getPoint(){
    return point;
}


public void setShippings(Set<Shipping> shippings){
    this.shippings = shippings;
}


@Transient
public int getReturnQuantity(){
    int returnQuantity = 0;
    if (getOrderItems() != null) {
        for (OrderItem orderItem : getOrderItems()) {
            if (orderItem != null && orderItem.getReturnQuantity() != null) {
                returnQuantity += orderItem.getReturnQuantity();
            }
        }
    }
    return returnQuantity;
}


@NotNull
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getOffsetAmount(){
    return offsetAmount;
}


@OneToOne(fetch = FetchType.LAZY)
public CouponCode getCouponCode(){
    return couponCode;
}


@Column(updatable = false)
public String getPromotion(){
    return promotion;
}


public void setShippingMethod(ShippingMethod shippingMethod){
    this.shippingMethod = shippingMethod;
}


@Valid
@NotEmpty
@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
@OrderBy("isGift asc")
public List<OrderItem> getOrderItems(){
    return orderItems;
}


@Column(nullable = false)
public String getAreaName(){
    return areaName;
}


public void setPaymentStatus(PaymentStatus paymentStatus){
    this.paymentStatus = paymentStatus;
}


public void setPhone(String phone){
    this.phone = phone;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getCouponDiscount(){
    return couponDiscount;
}


@Column(nullable = false)
public String getPaymentMethodName(){
    return paymentMethodName;
}


public void setPaymentMethod(PaymentMethod paymentMethod){
    this.paymentMethod = paymentMethod;
}


@Length(max = 200)
public String getMemo(){
    return memo;
}


@ManyToMany(fetch = FetchType.LAZY)
@JoinTable(name = "xx_order_coupon")
public List<Coupon> getCoupons(){
    return coupons;
}


public void setAmountPaid(BigDecimal amountPaid){
    this.amountPaid = amountPaid;
}


public void setReturns(Set<Returns> returns){
    this.returns = returns;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
public ShippingMethod getShippingMethod(){
    return shippingMethod;
}


@PreRemove
public void preRemove(){
    Set<Deposit> deposits = getDeposits();
    if (deposits != null) {
        for (Deposit deposit : deposits) {
            deposit.setOrder(null);
        }
    }
}


public void setOrderLogs(Set<OrderLog> orderLogs){
    this.orderLogs = orderLogs;
}


@PreUpdate
public void preUpdate(){
    if (getArea() != null) {
        setAreaName(getArea().getFullName());
    }
    if (getPaymentMethod() != null) {
        setPaymentMethodName(getPaymentMethod().getName());
    }
    if (getShippingMethod() != null) {
        setShippingMethodName(getShippingMethod().getName());
    }
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getConsignee(){
    return consignee;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getPhone(){
    return phone;
}


@Transient
public int getShippedQuantity(){
    int shippedQuantity = 0;
    if (getOrderItems() != null) {
        for (OrderItem orderItem : getOrderItems()) {
            if (orderItem != null && orderItem.getShippedQuantity() != null) {
                shippedQuantity += orderItem.getShippedQuantity();
            }
        }
    }
    return shippedQuantity;
}


@Column(nullable = false)
public Boolean getIsAllocatedStock(){
    return isAllocatedStock;
}


@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate asc")
public Set<Shipping> getShippings(){
    return shippings;
}


public void setFreight(BigDecimal freight){
    this.freight = freight;
}


@Transient
public int getQuantity(){
    int quantity = 0;
    if (getOrderItems() != null) {
        for (OrderItem orderItem : getOrderItems()) {
            if (orderItem != null && orderItem.getQuantity() != null) {
                quantity += orderItem.getQuantity();
            }
        }
    }
    return quantity;
}


@Column(nullable = false)
public PaymentStatus getPaymentStatus(){
    return paymentStatus;
}


public void setPoint(Long point){
    this.point = point;
}


@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate asc")
public Set<Refunds> getRefunds(){
    return refunds;
}


@Column(nullable = false, updatable = false, precision = 21, scale = 6)
public BigDecimal getPromotionDiscount(){
    return promotionDiscount;
}


public void setLockExpire(Date lockExpire){
    this.lockExpire = lockExpire;
}


public void setFee(BigDecimal fee){
    this.fee = fee;
}


public void setArea(Area area){
    this.area = area;
}


@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(nullable = false, updatable = false)
public Member getMember(){
    return member;
}


public void setRefunds(Set<Refunds> refunds){
    this.refunds = refunds;
}


@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate asc")
public Set<Returns> getReturns(){
    return returns;
}


public void setCouponDiscount(BigDecimal couponDiscount){
    this.couponDiscount = couponDiscount;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
public PaymentMethod getPaymentMethod(){
    return paymentMethod;
}


public void setIsAllocatedStock(Boolean isAllocatedStock){
    this.isAllocatedStock = isAllocatedStock;
}


public void setOrderItems(List<OrderItem> orderItems){
    this.orderItems = orderItems;
}


public void setPayments(Set<Payment> payments){
    this.payments = payments;
}


public void setExpire(Date expire){
    this.expire = expire;
}


@Column(nullable = false)
public OrderStatus getOrderStatus(){
    return orderStatus;
}


public Date getExpire(){
    return expire;
}


@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getFee(){
    return fee;
}


public void setSn(String sn){
    this.sn = sn;
}


@NotNull
@Column(nullable = false)
public Boolean getIsInvoice(){
    return isInvoice;
}


public void setDeposits(Set<Deposit> deposits){
    this.deposits = deposits;
}


@NotEmpty
@Length(max = 200)
@Column(nullable = false)
public String getAddress(){
    return address;
}


@Transient
public OrderItem getOrderItem(String sn){
    if (sn != null && getOrderItems() != null) {
        for (OrderItem orderItem : getOrderItems()) {
            if (orderItem != null && sn.equalsIgnoreCase(orderItem.getSn())) {
                return orderItem;
            }
        }
    }
    return null;
}


public void setShippingStatus(ShippingStatus shippingStatus){
    this.shippingStatus = shippingStatus;
}


@NotNull
@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getFreight(){
    return freight;
}


public void setInvoiceTitle(String invoiceTitle){
    this.invoiceTitle = invoiceTitle;
}


@Column(nullable = false)
public String getShippingMethodName(){
    return shippingMethodName;
}


public void setPromotionDiscount(BigDecimal promotionDiscount){
    this.promotionDiscount = promotionDiscount;
}


public void setAddress(String address){
    this.address = address;
}


public Date getLockExpire(){
    return lockExpire;
}


public void setOffsetAmount(BigDecimal offsetAmount){
    this.offsetAmount = offsetAmount;
}


public void setShippingMethodName(String shippingMethodName){
    this.shippingMethodName = shippingMethodName;
}


@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate asc")
public Set<Payment> getPayments(){
    return payments;
}


public void setIsInvoice(Boolean isInvoice){
    this.isInvoice = isInvoice;
}


@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getAmountPaid(){
    return amountPaid;
}


public void setTax(BigDecimal tax){
    this.tax = tax;
}


@Transient
public BigDecimal getPrice(){
    BigDecimal price = new BigDecimal(0);
    if (getOrderItems() != null) {
        for (OrderItem orderItem : getOrderItems()) {
            if (orderItem != null && orderItem.getSubtotal() != null) {
                price = price.add(orderItem.getSubtotal());
            }
        }
    }
    return price;
}


public void setMemo(String memo){
    this.memo = memo;
}


@OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
@OrderBy("createDate asc")
public Set<OrderLog> getOrderLogs(){
    return orderLogs;
}


@Transient
public BigDecimal getAmountPayable(){
    BigDecimal amountPayable = getAmount().subtract(getAmountPaid());
    return amountPayable.compareTo(new BigDecimal(0)) > 0 ? amountPayable : new BigDecimal(0);
}


public void setOrderStatus(OrderStatus orderStatus){
    this.orderStatus = orderStatus;
}


public void setAreaName(String areaName){
    this.areaName = areaName;
}


@Transient
public boolean isExpired(){
    return getExpire() != null && new Date().after(getExpire());
}


@Min(0)
@Digits(integer = 12, fraction = 3)
@Column(nullable = false, precision = 21, scale = 6)
public BigDecimal getTax(){
    return tax;
}


@NotNull
@ManyToOne(fetch = FetchType.LAZY)
public Area getArea(){
    return area;
}


public void setPromotion(String promotion){
    this.promotion = promotion;
}


}