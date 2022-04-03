package com.github.haseoo.courier.models;
 import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.persistence;
import javax.persistence.CascadeType.ALL;
import javax.persistence.FetchType.EAGER;
import com.github.haseoo.courier.Request.MagazineModelRequest;
import com.github.haseoo.courier.Request.Impl.MagazineModelRequestImpl;
import com.github.haseoo.courier.DTO.MagazineModel;
@Entity
@Table(name = "logistician")
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "employeeId")
public class LogisticianModel extends EmployeeModel{

@Transient
 private  MagazineModel magazine;

@Column(name = "id")
 private Long id;

@Transient
 private MagazineModelRequest magazinemodelrequest = new MagazineModelRequestImpl();;


}