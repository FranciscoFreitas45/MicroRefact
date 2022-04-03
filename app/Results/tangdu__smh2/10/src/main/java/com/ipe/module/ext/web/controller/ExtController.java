package com.ipe.module.ext.web.controller;
 import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ipe.common.util.CollectionSort;
import com.ipe.common.util.SpringContextHolder;
import com.ipe.module.ext.pojo.ExtColumn;
import com.ipe.module.ext.pojo.ExtTable;
import com.ipe.module.ext.web.ExtUtil;
import org.hibernate.ejb.metamodel.EntityTypeImpl;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("ext")
public class ExtController {


@RequestMapping(value = "generate_execute")
@ResponseBody
public String extgenerateExecute(String type,String dt,String entityName,String mpackage,Boolean createFile,String path){
    List<ExtColumn> columns = JSON.parseArray(dt, ExtColumn.class);
    // 排序
    CollectionSort.sortList(columns, "sno", true);
    Map<String, Object> objectMap = Maps.newHashMap();
    objectMap.put("entityName", entityName);
    objectMap.put("mpackage", mpackage);
    objectMap.put("formItem", columns);
    return ExtUtil.generate(objectMap, type, createFile, path);
}


@RequestMapping(value = "generate_view")
public String extGenerate(ModelMap mp){
    // step1:得到系统所有实体对象
    LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = SpringContextHolder.getBean(LocalContainerEntityManagerFactoryBean.class);
    EntityManagerFactory entityManagerFactory = localContainerEntityManagerFactoryBean.getNativeEntityManagerFactory();
    Metamodel metamodel = entityManagerFactory.getMetamodel();
    Iterator<EntityType<?>> entityTypes = metamodel.getEntities().iterator();
    List<ExtTable> extTables = Lists.newArrayList();
    while (entityTypes.hasNext()) {
        EntityTypeImpl entityType = (EntityTypeImpl) entityTypes.next();
        Iterator<Attribute> attributes = entityType.getDeclaredAttributes().iterator();
        String entity = entityType.getJavaType().getName();
        ExtTable extTable = new ExtTable();
        extTable.setPackageName(entity.substring(0, entity.lastIndexOf(".")));
        extTable.setEntityName(entity.substring(entity.lastIndexOf(".") + 1));
        List<ExtColumn> columns = Lists.newArrayList();
        while (attributes.hasNext()) {
            Attribute attribute = attributes.next();
            ExtColumn extColumn = new ExtColumn();
            extColumn.setName(attribute.getName());
            extColumn.setMaxLength(0);
            extColumn.setJavaType(attribute.getJavaType().getSimpleName());
            if ("String".equals(extColumn.getJavaType())) {
                extColumn.setXtype("textfield");
            } else if ("Date".equals(extColumn.getJavaType()) || "Timestamp".equals(extColumn.getJavaType())) {
                extColumn.setXtype("datefield");
            } else if ("Integer".equals(extColumn.getJavaType()) || "Long".equals(extColumn.getJavaType())) {
                extColumn.setXtype("numberfield");
            } else if ("Double".equals(extColumn.getJavaType()) || "BigDecimal".equals(extColumn.getJavaType())) {
                extColumn.setXtype("numberfield");
            } else if ("Set".equals(extColumn.getJavaType()) || "List".equals(extColumn.getJavaType()) || "Collection".equals(extColumn.getJavaType())) {
                continue;
            }
            columns.add(extColumn);
        }
        extTable.setColumns(columns);
        extTables.add(extTable);
    }
    // step2:
    mp.put("entities", extTables);
    mp.put("entitiesStr", JSON.toJSONString(extTables));
    return "tools/extgenerate";
}


@RequestMapping(value = "code_view")
public String extCodeView(){
    return "tools/extgenerateview";
}


}