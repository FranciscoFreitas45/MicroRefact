package com.sobey.cmop.mvc.DTO;
 import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.google.common.collect.Lists;
public class Group {

 private  Integer id;

 private  String name;

 private  List<String> permissionList;

// Constructors
/**
 * default constructor
 */
public Group() {
}/**
 * full constructor
 */
public Group(String name) {
    this.name = name;
}
@Transient
public String getPermissionNames(){
    List<String> permissionNameList = Lists.newArrayList();
    for (String permission : permissionList) {
        permissionNameList.add(Permission.parse(permission).displayName);
    }
    return StringUtils.join(permissionNameList, ",");
}


@Column(name = "name", nullable = false, length = 20)
public String getName(){
    return this.name;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", unique = true, nullable = false)
public Integer getId(){
    return this.id;
}


@ElementCollection
@CollectionTable(name = "group_permission", joinColumns = { @JoinColumn(name = "group_id") })
@Column(name = "permission")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public List<String> getPermissionList(){
    return permissionList;
}


}