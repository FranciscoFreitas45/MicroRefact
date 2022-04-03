package com.github.haseoo.courier.DTO;
 import lombok.Data;
import javax.persistence;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.FetchType.LAZY;
import javax.persistence.GenerationType.IDENTITY;
import com.github.haseoo.courier.Request.ParcelModelRequest;
import com.github.haseoo.courier.Request.Impl.ParcelModelRequestImpl;
import com.github.haseoo.courier.DTO.ParcelModel;
public class ParcelTypeModel {

 private  Long id;

 private  String name;

 private  String description;

 private  BigDecimal price;

 private  Boolean active;

 private List<ParcelModel> parcels;


}