package com.weflors.DTO;
 import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence;
import java.math.BigDecimal;
import java.util.Collection;
public class ProductEntity {

 private  int productId;

 private  String productName;

 private  String articul;

 private  String pictureUrl;

 private  String originOfProduct;

 private  String invoiceNumber;

 private  BigDecimal productPrice;

 private  Collection<ProcurementEntity> procurementsByProductId;

 private  ContragentsEntity contragentsByContragentId;

 private  ProductTypesEntity productTypesByProductTypeId;

 private  ProductDetailsEntity productDetailsByProductId;

 private  Collection<ProductStatusEntity> productStatusByProductId;

 private  Collection<SaleEntity> salesByProductId;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

// private SaleEntity saleByProductId;
// @Autowired
// ContragentsRepository contragentsRepository;
// 
// public ProductEntity(String productName, String productType, String articul, String pictureUrl, String originOfProduct, Collection<ProcurementEntity> procurementsByProductId, Integer contragentsByContragentId, ProductDetailsEntity productDetailsByProductId, ProductStatusEntity productStatusByProductId, Collection<SaleEntity> salesByProductId) {
// this.productName = productName;
// this.productType = productType;
// this.articul = articul;
// this.pictureUrl = pictureUrl;
// this.originOfProduct = originOfProduct;
// this.procurementsByProductId = procurementsByProductId;
// this.contragentsByContragentId = contragentsRepository.findByContragentID(contragentsByContragentId);
// this.productDetailsByProductId = productDetailsByProductId;
// this.productStatusByProductId = productStatusByProductId;
// this.salesByProductId = salesByProductId;
// }
public ProductEntity() {
}
@Basic
@Column(name = "product_price", nullable = false, precision = 2)
public BigDecimal getProductPrice(){
    return productPrice;
}


@OneToOne(mappedBy = "productByProductId", orphanRemoval = true)
@JsonManagedReference(value = "product-product_details")
public ProductDetailsEntity getProductDetailsByProductId(){
    return productDetailsByProductId;
}


@Basic
@Column(name = "invoice_number", nullable = false, length = 30)
public String getInvoiceNumber(){
    return invoiceNumber;
}


@ManyToOne
@JsonBackReference(value = "product_types-product")
@JoinColumn(name = "product_type_id", referencedColumnName = "product_type_id", nullable = false)
public ProductTypesEntity getProductTypesByProductTypeId(){
    return productTypesByProductTypeId;
}


@Basic
@Column(name = "product_name", nullable = false, length = 50)
public String getProductName(){
    return productName;
}


@Id
// @SequenceGenerator(name = "hibernateSeq", sequenceName = "HIBERNATE_SEQUENCE")
// , generator = "hibernateSeq")
@GeneratedValue(strategy = GenerationType.AUTO)
// @GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "product_id", unique = true, nullable = false)
public int getProductId(){
    return productId;
}


@OneToMany(mappedBy = "productByProductId", orphanRemoval = true)
@JsonManagedReference(value = "product-procurement")
public Collection<ProcurementEntity> getProcurementsByProductId(){
    return procurementsByProductId;
}


@OneToMany(mappedBy = "productByProductId")
@JsonManagedReference(value = "product-sale")
public Collection<SaleEntity> getSalesByProductId(){
    return salesByProductId;
}


@Basic
@Column(name = "origin_of_product", nullable = false, length = 50)
public String getOriginOfProduct(){
    return originOfProduct;
}


@OneToMany(mappedBy = "productByProductId", fetch = FetchType.EAGER)
@JsonManagedReference(value = "product-product_status")
public Collection<ProductStatusEntity> getProductStatusByProductId(){
    return productStatusByProductId;
}


@Basic
@Column(name = "picture_url", nullable = true, length = 100)
public String getPictureUrl(){
    return pictureUrl;
}


@Basic
@Column(name = "articul", nullable = false, length = 50)
public String getArticul(){
    return articul;
}


@ManyToOne
@JsonBackReference(value = "contragents-product")
@JoinColumn(name = "contragent_id", referencedColumnName = "contragent_id")
public ContragentsEntity getContragentsByContragentId(){
    return contragentsByContragentId;
}


public void setProductDetailsByProductId(ProductDetailsEntity productDetailsByProductId){
    this.productDetailsByProductId = productDetailsByProductId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setProductDetailsByProductId"))

.queryParam("productDetailsByProductId",productDetailsByProductId)
;
restTemplate.put(builder.toUriString(),null);
}


}