package cn.com.cnc.fcc.service.impl;
 import cn.com.cnc.fcc.domain;
import cn.com.cnc.fcc.repository;
import cn.com.cnc.fcc.service.QmsMaterielEntryService;
import cn.com.cnc.fcc.service.dto.QmsMaterielDetailsExcelDto;
import cn.com.cnc.fcc.service.dto.QmsMaterielEntryDto;
import cn.com.cnc.fcc.service.dto.QmsMaterielEntryEditDto;
import cn.com.cnc.fcc.service.dto.QmsMaterielEntryExcelDto;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import jdk.nashorn.internal.ir.IfNode;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class QmsMaterielEntryServiceImpl implements QmsMaterielEntryService{

 private  Logger log;

 private  QmsMaterielRepository qmsMaterielRepository;

 private  QmsSupplierRepository qmsSupplierRepository;

 private  QmsMaterielEntryRepository qmsMaterielEntryRepository;

 private  QmsMaterielDetailsRepository qmsMaterielDetailsRepository;

 private  QmsEnclosureRepository qmsEnclosureRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

 private  EntityManagerFactory emf;

public QmsMaterielEntryServiceImpl(QmsMaterielRepository qmsMaterielRepository, EntityManagerFactory emf, QmsControlDetailsRepository qmsControlDetailsRepository, QmsMaterielEntryRepository qmsMaterielEntryRepository, QmsSupplierRepository qmsSupplierRepository, QmsMaterielDetailsRepository qmsMaterielDetailsRepository, QmsEnclosureRepository qmsEnclosureRepository) {
    this.qmsMaterielRepository = qmsMaterielRepository;
    this.qmsSupplierRepository = qmsSupplierRepository;
    this.emf = emf;
    this.qmsMaterielEntryRepository = qmsMaterielEntryRepository;
    this.qmsMaterielDetailsRepository = qmsMaterielDetailsRepository;
    this.qmsEnclosureRepository = qmsEnclosureRepository;
}
@Override
public JSONObject uploadData(MultipartFile files){
    // ???????????????
    JSONObject returnData = new JSONObject();
    // session??????????????????
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // ??????????????????
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // ???????????????????????????????????????QmsControlDetails???????????????????????????????????????
    List<QmsMaterielEntry> qmsMaterielEntries = new ArrayList<QmsMaterielEntry>();
    // ?????????????????????????????????
    qmsMaterielEntries = qmsMaterielEntryRepository.findAll();
    // ????????????????????????
    List<String> errorMess = new ArrayList<String>();
    returnData.put("flag", "1");
    String str = "([0-9]+)$";
    // ??????????????????
    if (files.isEmpty()) {
        returnData.put("status", "error");
        returnData.put("message", "??????????????????");
    } else {
        try {
            // ?????????
            JSONArray data = ExcelUtil.getExcelAllSheetAllData(files.getInputStream(), QmsMaterielEntryExcelDto.class, QmsMaterielDetailsExcelDto.class, 13, 2);
            System.out.println(data);
            if ("longColumns".equals(data.getJSONObject(0).getString("errorColumns"))) {
                // ???????????????????????????????????????
                returnData.put("status", "error");
                returnData.put("message", "???????????????");
                return returnData;
            }
            if (data.size() > 0) {
                for (int i = 0; i < data.size(); i++) {
                    JSONObject sheetData = data.getJSONObject(i);
                    // ????????????????????????
                    JSONObject headerData = sheetData.getJSONObject("entry");
                    // ???????????????
                    JSONArray detailsData = sheetData.getJSONArray("details");
                    if ("".equals(headerData.getString("entryType"))) {
                        errorMess.add("???" + (i + 1) + "sheet???????????????????????????,????????????????????????");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (!"??????".equals(headerData.getString("entryType")) && !"??????".equals(headerData.getString("entryType"))) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,??????????????????????????????????????????");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if ("".equals(headerData.getString("materielCd"))) {
                        errorMess.add("???" + (i + 1) + "sheet???????????????????????????,????????????????????????");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("materielCd").length() > 10) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,??????????????????????????????10");
                        returnData.put("flag", "0");
                        continue;
                    }
                    Optional<QmsMateriel> qmsMateriel = qmsMaterielRepository.findQmsMaterielByMaterielCdAndFlagStatus(headerData.getString("materielCd"), "0");
                    if (!qmsMateriel.isPresent()) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,??????????????????");
                        returnData.put("flag", "0");
                        continue;
                    }
                    Long materielId = qmsMateriel.get().getId();
                    if ("".equals(headerData.getString("supplierCd"))) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,???????????????????????????");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("supplierCd").length() > 10) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,?????????????????????????????????10");
                        returnData.put("flag", "0");
                        continue;
                    }
                    Optional<QmsSupplier> qmsSupplier = qmsSupplierRepository.findQmsSupplierBySupplierCdAndFlagStatus(headerData.getString("supplierCd"), "0");
                    if (!qmsSupplier.isPresent()) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,?????????????????????");
                        returnData.put("flag", "0");
                        continue;
                    }
                    Long supplierId = qmsSupplier.get().getId();
                    if (headerData.getString("specificationType").length() > 100) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,????????????????????????100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("figureNumber").length() > 30) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,??????????????????30");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (!"".equals(headerData.getString("packingQuantity"))) {
                        if (!headerData.getString("packingQuantity").matches(str)) {
                            errorMess.add("???" + (i + 1) + "sheet????????????????????????,??????????????????????????????");
                            returnData.put("flag", "0");
                            continue;
                        }
                    }
                    if (headerData.getString("packingQuantity").length() > 10) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,????????????????????????8");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (!"".equals(headerData.getString("entryQuantity"))) {
                        if (!headerData.getString("entryQuantity").matches(str)) {
                            errorMess.add("???" + (i + 1) + "sheet????????????????????????,??????????????????????????????");
                            returnData.put("flag", "0");
                            continue;
                        }
                    }
                    if (headerData.getString("entryQuantity").length() > 10) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,????????????????????????8");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("purchaseOrderNumber").length() > 50) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,????????????????????????50");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("batchNumber").length() > 50) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,????????????/??????????????????50");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("madeYmd").length() > 10) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,????????????????????????10");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("madeFactoryCd").length() > 10) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,???????????????????????????10");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("texTure").length() > 100) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,??????????????????100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (headerData.getString("castingNum").length() > 100) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,??????????????????100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    QmsMaterielEntry qmsMaterielEntry = new QmsMaterielEntry();
                    qmsMaterielEntry.setMaterielId(materielId.intValue());
                    qmsMaterielEntry.setSpecificationType(headerData.getString("specificationType"));
                    qmsMaterielEntry.setFigureNumber(headerData.getString("figureNumber"));
                    qmsMaterielEntry.setPackingQuantity(Integer.parseInt(headerData.getString("packingQuantity")));
                    qmsMaterielEntry.setSupplierId(supplierId.intValue());
                    qmsMaterielEntry.setEntryQuantity(Integer.parseInt(headerData.getString("entryQuantity")));
                    if ("??????".equals(headerData.getString("entryType"))) {
                        qmsMaterielEntry.setEntryType("0");
                    } else {
                        qmsMaterielEntry.setEntryType("1");
                    }
                    qmsMaterielEntry.setPurchaseOrderNumber(headerData.getString("purchaseOrderNumber"));
                    qmsMaterielEntry.setBatchNumber(headerData.getString("batchNumber"));
                    qmsMaterielEntry.setMadeYmd(headerData.getString("madeYmd"));
                    qmsMaterielEntry.setMadeFactoryCd(headerData.getString("madeFactoryCd"));
                    qmsMaterielEntry.setTexTure(headerData.getString("texTure"));
                    qmsMaterielEntry.setCastingNum(headerData.getString("castingNum"));
                    qmsMaterielEntry.setFlagInspect("0");
                    qmsMaterielEntry.setFlagStatus("0");
                    qmsMaterielEntry.setMakeTime(dateUtil.getDBNowDate());
                    qmsMaterielEntry.setMakeUser(user.getUsername());
                    qmsMaterielEntry.setMakeTime(dateUtil.getDBNowDate());
                    qmsMaterielEntry.setModifyUser(user.getUsername());
                    QmsMaterielEntry qmsMaterielEntrySave = qmsMaterielEntryRepository.save(qmsMaterielEntry);
                    Integer entryId = qmsMaterielEntrySave.getId().intValue();
                    if (detailsData.size() <= 0) {
                        errorMess.add("???" + (i + 1) + "sheet????????????????????????,???????????????");
                        returnData.put("flag", "0");
                        continue;
                    }
                    for (int j = 0; j < detailsData.size(); j++) {
                        JSONObject detail = detailsData.getJSONObject(j);
                        if ("".equals(detail.getString("goodsCd"))) {
                            errorMess.add("???" + (i + 1) + "sheet????????????????????????,???" + (j + 1) + "??????????????????????????????");
                            returnData.put("flag", "0");
                            continue;
                        }
                        if (detail.getString("goodsCd").length() > 30) {
                            errorMess.add("???" + (i + 1) + "sheet????????????????????????,???" + (j + 1) + "????????????????????????????????????30");
                            returnData.put("flag", "0");
                            continue;
                        }
                        if ("".equals(detail.getString("goodsQuantity"))) {
                            errorMess.add("???" + (i + 1) + "sheet????????????????????????,???" + (j + 1) + "?????????????????????????????????");
                            returnData.put("flag", "0");
                            continue;
                        }
                        if (!detail.getString("goodsQuantity").matches(str)) {
                            errorMess.add("???" + (i + 1) + "sheet????????????????????????,???" + (j + 1) + "???????????????????????????????????????");
                            returnData.put("flag", "0");
                            continue;
                        }
                        if (detail.getString("goodsQuantity").length() > 8) {
                            errorMess.add("???" + (i + 1) + "sheet????????????????????????,???" + (j + 1) + "?????????????????????????????????????????????8");
                            returnData.put("flag", "0");
                            continue;
                        }
                        if ("??????".equals(headerData.getString("entryType")) && !"1".equals(detail.getString("goodsQuantity"))) {
                            errorMess.add("???" + (i + 1) + "sheet????????????????????????,?????????????????????, ???" + (j + 1) + "?????????????????????????????????1");
                            returnData.put("flag", "0");
                            continue;
                        }
                        QmsMaterielDetails qmsMaterielDetails = new QmsMaterielDetails();
                        qmsMaterielDetails.setEntryId(entryId);
                        qmsMaterielDetails.setFlagStatus("0");
                        qmsMaterielDetails.setGoodsCd(detail.getString("goodsCd"));
                        qmsMaterielDetails.setGoodsQuantity(Integer.parseInt(detail.getString("goodsQuantity")));
                        qmsMaterielDetails.setMakeTime(dateUtil.getDBNowDate());
                        qmsMaterielDetails.setModifyTime(dateUtil.getDBNowDate());
                        qmsMaterielDetails.setMakeUser(user.getUsername());
                        qmsMaterielDetails.setModifyUser(user.getUsername());
                        qmsMaterielDetailsRepository.save(qmsMaterielDetails);
                    }
                }
                if (returnData.getString("flag") == "1") {
                    returnData.put("status", "success");
                    returnData.put("message", "????????????!??????????????????" + data.size() + "?????????");
                } else {
                    // ????????????????????????????????????????????????
                    String error = "";
                    for (int i = 0; i < errorMess.size(); i++) {
                        error = error + errorMess.get(i) + "???";
                    }
                    returnData.put("status", "success");
                    returnData.put("message1", "????????????:" + (data.size() - errorMess.size()) + "??????" + "????????????:" + errorMess.size() + "??????");
                    returnData.put("message2", "????????????:" + error);
                }
            } else {
                returnData.put("status", "error");
                returnData.put("message", "excel");
                return returnData;
            }
        } catch (Exception e) {
            returnData.put("status", "error");
            returnData.put("message", "?????????????????????");
        }
    }
    return returnData;
}


@Override
public JSONObject getEditHeader(String id){
    JSONObject info = new JSONObject();
    QmsMaterielEntryEditDto qmsMaterielEntryEditDto = new QmsMaterielEntryEditDto();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getEditHeaderSql(id);
        Query query = em.createNativeQuery(sql);
        query.setParameter("id", id);
        if (!"".equals(id) && id != null) {
            query.setParameter("id", id);
        }
        ResultTransformer transformer = Transformers.aliasToBean(QmsMaterielEntryEditDto.class);
        Object result = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("materielId", StandardBasicTypes.STRING).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("figureNumber", StandardBasicTypes.STRING).addScalar("supplierId", StandardBasicTypes.STRING).addScalar("supplierCd", StandardBasicTypes.STRING).addScalar("supplierName", StandardBasicTypes.STRING).addScalar("specificationType", StandardBasicTypes.STRING).addScalar("purchaseOrderNumber", StandardBasicTypes.STRING).addScalar("packingQuantity", StandardBasicTypes.STRING).addScalar("entryQuantity", StandardBasicTypes.STRING).addScalar("entryType", StandardBasicTypes.STRING).addScalar("batchNumber", StandardBasicTypes.STRING).addScalar("madeYmd", StandardBasicTypes.STRING).addScalar("madeFactoryCd", StandardBasicTypes.STRING).addScalar("entryDate", StandardBasicTypes.STRING).addScalar("texTure", StandardBasicTypes.STRING).addScalar("castingNum", StandardBasicTypes.STRING).setResultTransformer(transformer).getSingleResult();
        info.put("result", (QmsMaterielEntryEditDto) result);
    } catch (Exception e) {
        System.out.println(e.getMessage());
    } finally {
        em.close();
    }
    return info;
}


public List<QmsMaterielEntryDto> qmsMaterielEntryFindAll(String materielCd,String materielName,String figureNumber,String supplierCd,String supplierName,String specificationType,String purchaseOrderNumber,String flagInspect,String enclosure){
    List<QmsMaterielEntryDto> qmsMaterielEntrys = new ArrayList<QmsMaterielEntryDto>();
    EntityManager em = emf.createEntityManager();
    try {
        String sql = getMaterielEntrySql(materielCd, materielName, figureNumber, supplierCd, supplierName, specificationType, purchaseOrderNumber, flagInspect, enclosure);
        Query query = em.createNativeQuery(sql);
        if (!materielCd.equals("") && materielCd != null) {
            query.setParameter("materielCd", "%" + materielCd + "%");
        }
        if (!materielName.equals("") && materielName != null) {
            query.setParameter("materielName", "%" + materielName + "%");
        }
        if (!figureNumber.equals("") && figureNumber != null) {
            query.setParameter("figureNumber", "%" + figureNumber + "%");
        }
        if (!supplierCd.equals("") && supplierCd != null) {
            query.setParameter("supplierCd", "%" + supplierCd + "%");
        }
        if (!supplierName.equals("") && supplierName != null) {
            query.setParameter("supplierName", "%" + supplierName + "%");
        }
        if (!specificationType.equals("") && specificationType != null) {
            query.setParameter("specificationType", "%" + specificationType + "%");
        }
        if (!purchaseOrderNumber.equals("") && purchaseOrderNumber != null) {
            query.setParameter("purchaseOrderNumber", "%" + purchaseOrderNumber + "%");
        }
        if (!flagInspect.equals("") && flagInspect != null && !"-1".equals(flagInspect)) {
            if (!"3".equals(flagInspect)) {
                query.setParameter("flagInspect", flagInspect);
            } else {
                query.setParameter("flagInspect", "3");
                query.setParameter("flagInspect1", "4");
            }
        }
        ResultTransformer transformer = Transformers.aliasToBean(QmsMaterielEntryDto.class);
        qmsMaterielEntrys = query.unwrap(org.hibernate.query.NativeQuery.class).addScalar("id", StandardBasicTypes.LONG).addScalar("materielCd", StandardBasicTypes.STRING).addScalar("materielName", StandardBasicTypes.STRING).addScalar("figureNumber", StandardBasicTypes.STRING).addScalar("supplierCd", StandardBasicTypes.STRING).addScalar("supplierName", StandardBasicTypes.STRING).addScalar("specificationType", StandardBasicTypes.STRING).addScalar("purchaseOrderNumber", StandardBasicTypes.STRING).addScalar("flagInspect", StandardBasicTypes.STRING).addScalar("enclosure", StandardBasicTypes.STRING).addScalar("checkType", StandardBasicTypes.STRING).addScalar("entryDate", StandardBasicTypes.STRING).setResultTransformer(transformer).getResultList();
    } catch (Exception e) {
        System.out.println(e.getMessage());
    } finally {
        em.close();
    }
    return qmsMaterielEntrys;
}


public String getEditHeaderSql(String id){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT\n" + "\tt01.id,\n" + "\tt02.id AS materielId,\n" + "\tt02.materiel_cd AS materielCd,\n" + "\tt02.materiel_name AS materielName,\n" + "\tt01.figure_number AS figureNumber,\n" + "\tt03.id AS supplierId,\n" + "\tt03.supplier_cd AS supplierCd,\n" + "\tt03.supplier_name AS supplierName,\n" + "\tt01.specification_type AS specificationType,\n" + "\tt01.purchase_order_number AS purchaseOrderNumber,\n" + "\tt01.packing_quantity AS packingQuantity,\n" + "\tt01.entry_quantity AS entryQuantity,\n" + "\tt01.entry_type AS entryType,\n" + "\tt01.batch_number AS batchNumber,\n" + "\tt01.made_ymd AS madeYmd,\n" + "\tt01.made_factory_cd AS madeFactoryCd,\n" + "\tt01.tex_ture AS texTure,\n" + "\tt01.casting_num AS castingNum,\n" + "\tt01.entry_date AS entryDate\n" + "FROM\n" + "\tqms_materiel_entry t01\n" + "LEFT JOIN qms_materiel t02 ON t01.materiel_id = t02.id\n" + "AND t02.flag_status = '0'\n" + "LEFT JOIN qms_supplier t03 ON t01.supplier_id = t03.id\n" + "AND t03.flag_status = '0'\n" + "WHERE\n" + "\t1 = 1\n");
    if (!"".equals(id) && id != null) {
        sql.append("AND t01.id = :id\n");
    }
    return sql.toString();
}


@Override
@Transactional
public JSONObject saveData(JSONObject body){
    JSONObject info = new JSONObject();
    // session??????????????????
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // ??????????????????
    UserDetails user = (UserDetails) authentication.getPrincipal();
    try {
        System.out.println(body.get("id"));
        // ??????
        if (body.get("id") != null) {
            Long id = body.getLong("id");
            Optional<QmsMaterielEntry> qmsMaterielEntryOptional = this.qmsMaterielEntryRepository.findByIdAndFlagStatus(id, "0");
            if (qmsMaterielEntryOptional.isPresent()) {
                QmsMaterielEntry qmsMaterielEntry = qmsMaterielEntryOptional.get();
                if (!"".equals(body.getString("specificationType")) && body.getString("specificationType") != null) {
                    qmsMaterielEntry.setSpecificationType(body.getString("specificationType"));
                }
                if (!"".equals(body.getString("figureNumber")) && body.getString("figureNumber") != null) {
                    qmsMaterielEntry.setFigureNumber(body.getString("figureNumber"));
                }
                if (!"".equals(body.getString("packingQuantity")) && body.getString("packingQuantity") != null) {
                    qmsMaterielEntry.setPackingQuantity(body.getInteger("packingQuantity"));
                }
                if (!"".equals(body.getString("entryQuantity")) && body.getString("entryQuantity") != null) {
                    qmsMaterielEntry.setEntryQuantity(body.getInteger("entryQuantity"));
                }
                if (!"".equals(body.getString("entryType")) && body.getString("entryType") != null) {
                    qmsMaterielEntry.setEntryType(body.getString("entryType"));
                }
                if (!"".equals(body.getString("purchaseOrderNumber")) && body.getString("purchaseOrderNumber") != null) {
                    qmsMaterielEntry.setPurchaseOrderNumber(body.getString("purchaseOrderNumber"));
                }
                if (!"".equals(body.getString("batchNumber")) && body.getString("batchNumber") != null) {
                    qmsMaterielEntry.setBatchNumber(body.getString("batchNumber"));
                }
                if (!"".equals(body.getString("madeYmd")) && body.getString("madeYmd") != null) {
                    qmsMaterielEntry.setMadeYmd(body.getString("madeYmd"));
                }
                if (!"".equals(body.getString("madeFactoryCd")) && body.getString("madeFactoryCd") != null) {
                    qmsMaterielEntry.setMadeFactoryCd(body.getString("madeFactoryCd"));
                }
                if (!"".equals(body.getString("texTure")) && body.getString("texTure") != null) {
                    qmsMaterielEntry.setTexTure(body.getString("texTure"));
                }
                if (!"".equals(body.getString("castingNum")) && body.getString("castingNum") != null) {
                    qmsMaterielEntry.setCastingNum(body.getString("castingNum"));
                }
                if (!"".equals(body.getString("materielId")) && body.getString("materielId") != null) {
                    qmsMaterielEntry.setMaterielId(body.getInteger("materielId"));
                }
                if (!"".equals(body.getString("supplierId")) && body.getString("supplierId") != null) {
                    qmsMaterielEntry.setSupplierId(body.getInteger("supplierId"));
                }
                if (!"".equals(body.getString("entryDate")) && body.getString("entryDate") != null) {
                    String entryDate = body.getString("entryDate");
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    entryDate = entryDate.replace("T", " ").replace("Z", "").replace(".000", "");
                    qmsMaterielEntry.setEntryDate(dateUtil.getZonedDateByTime(entryDate));
                }
                qmsMaterielEntry.setModifyUser(user.getUsername());
                qmsMaterielEntry.setModifyTime(dateUtil.getDBNowDate());
                qmsMaterielEntry = this.qmsMaterielEntryRepository.save(qmsMaterielEntry);
                JSONArray qmsMaterielDetails = body.getJSONArray("qmsMaterielDetails");
                JSONArray enclosures = body.getJSONArray("enclosures");
                JSONArray deleteEnclosures = body.getJSONArray("deleteList");
                if (qmsMaterielDetails.size() > 0) {
                    for (int i = 0; i < qmsMaterielDetails.size(); i++) {
                        JSONObject qmsMaterielDetailsObj = qmsMaterielDetails.getJSONObject(i);
                        // ????????????????????????
                        if (qmsMaterielDetailsObj.get("id") != null) {
                            QmsMaterielDetails qmsMaterielDetailsUpdate = this.qmsMaterielDetailsRepository.findByIdAndFlagStatus(qmsMaterielDetailsObj.getLong("id"), "0").get();
                            qmsMaterielDetailsUpdate.setEntryId(qmsMaterielEntry.getId().intValue());
                            if (!"".equals(qmsMaterielDetailsObj.getString("goodsCd")) && qmsMaterielDetailsObj.getString("goodsCd") != null) {
                                qmsMaterielDetailsUpdate.setGoodsCd(qmsMaterielDetailsObj.getString("goodsCd"));
                            }
                            if (!"".equals(qmsMaterielDetailsObj.getString("goodsQuantity")) && qmsMaterielDetailsObj.getString("goodsQuantity") != null) {
                                qmsMaterielDetailsUpdate.setGoodsQuantity(Integer.parseInt(qmsMaterielDetailsObj.getString("goodsQuantity")));
                            }
                            qmsMaterielDetailsUpdate.setModifyUser(user.getUsername());
                            qmsMaterielDetailsUpdate.setModifyTime(dateUtil.getDBNowDate());
                            qmsMaterielDetailsRepository.save(qmsMaterielDetailsUpdate);
                        } else {
                            // ????????????????????????
                            QmsMaterielDetails qmsMaterielDetailsNew = new QmsMaterielDetails();
                            qmsMaterielDetailsNew.setEntryId(qmsMaterielEntry.getId().intValue());
                            if (!"".equals(qmsMaterielDetailsObj.getString("goodsCd")) && qmsMaterielDetailsObj.getString("goodsCd") != null) {
                                qmsMaterielDetailsNew.setGoodsCd(qmsMaterielDetailsObj.getString("goodsCd"));
                            }
                            if (!"".equals(qmsMaterielDetailsObj.getString("goodsQuantity")) && qmsMaterielDetailsObj.getString("goodsQuantity") != null) {
                                qmsMaterielDetailsNew.setGoodsQuantity(qmsMaterielDetailsObj.getInteger("goodsQuantity"));
                            }
                            qmsMaterielDetailsNew.setFlagStatus("0");
                            qmsMaterielDetailsNew.setModifyUser(user.getUsername());
                            qmsMaterielDetailsNew.setModifyTime(dateUtil.getDBNowDate());
                            qmsMaterielDetailsNew.setMakeUser(user.getUsername());
                            qmsMaterielDetailsNew.setMakeTime(dateUtil.getDBNowDate());
                            qmsMaterielDetailsRepository.save(qmsMaterielDetailsNew);
                        }
                    }
                }
                if (enclosures.size() > 0) {
                    for (int i = 0; i < enclosures.size(); i++) {
                        JSONObject enclosure = enclosures.getJSONObject(i);
                        // ??????
                        if (enclosure.get("id") != null) {
                            Optional<QmsEnclosure> qmsEnclosure = this.qmsEnclosureRepository.findById(enclosure.getLong("id"));
                            if (qmsEnclosure.isPresent()) {
                                QmsEnclosure qmsEnclosureUpdate = qmsEnclosure.get();
                                qmsEnclosureUpdate.setInspectionInfoId(qmsMaterielEntry.getId().intValue());
                                if (!"".equals(enclosure.getString("enclosureAddress")) && enclosure.getString("enclosureAddress") != null) {
                                    qmsEnclosureUpdate.setEnclosureAddress(enclosure.getString("enclosureAddress"));
                                }
                                if (!"".equals(enclosure.getString("inspectionInfoId")) && enclosure.getString("inspectionInfoId") != null) {
                                    qmsEnclosureUpdate.setInspectionInfoId(enclosure.getInteger("inspectionInfoId"));
                                }
                                qmsEnclosureUpdate.setModifyTime(dateUtil.getDBNowDate());
                                qmsEnclosureUpdate.setModifyUser(user.getUsername());
                                this.qmsEnclosureRepository.save(qmsEnclosureUpdate);
                            }
                        // ???????????????
                        } else {
                            QmsEnclosure qmsEnclosureNew = new QmsEnclosure();
                            qmsEnclosureNew.setInspectionInfoId(qmsMaterielEntry.getId().intValue());
                            if (!"".equals(enclosure.getString("enclosureAddress")) && enclosure.getString("enclosureAddress") != null) {
                                qmsEnclosureNew.setEnclosureAddress(enclosure.getString("enclosureAddress"));
                            }
                            if (!"".equals(enclosure.getString("inspectionInfoId")) && enclosure.getString("inspectionInfoId") != null) {
                                qmsEnclosureNew.setInspectionInfoId(enclosure.getInteger("inspectionInfoId"));
                            }
                            qmsEnclosureNew.setInspectionKbn("1");
                            qmsEnclosureNew.setMakeTime(dateUtil.getDBNowDate());
                            qmsEnclosureNew.setModifyTime(dateUtil.getDBNowDate());
                            qmsEnclosureNew.setMakeUser(user.getUsername());
                            qmsEnclosureNew.setModifyUser(user.getUsername());
                            this.qmsEnclosureRepository.save(qmsEnclosureNew);
                        }
                    }
                }
                // ????????????
                if (deleteEnclosures.size() > 0) {
                    for (int i = 0; i < deleteEnclosures.size(); i++) {
                        JSONObject deleteEnclosure = deleteEnclosures.getJSONObject(i);
                        Long deleteEnclosureId = deleteEnclosure.getLong("id");
                        qmsEnclosureRepository.deleteById(deleteEnclosureId);
                    }
                }
            }
        // ??????
        } else {
            QmsMaterielEntry qmsMaterielEntry = new QmsMaterielEntry();
            if (!"".equals(body.getString("specificationType")) && body.getString("specificationType") != null) {
                qmsMaterielEntry.setSpecificationType(body.getString("specificationType"));
            }
            if (!"".equals(body.getString("figureNumber")) && body.getString("figureNumber") != null) {
                qmsMaterielEntry.setFigureNumber(body.getString("figureNumber"));
            }
            if (!"".equals(body.getString("packingQuantity")) && body.getString("packingQuantity") != null) {
                qmsMaterielEntry.setPackingQuantity(body.getInteger("packingQuantity"));
            }
            if (!"".equals(body.getString("entryQuantity")) && body.getString("entryQuantity") != null) {
                qmsMaterielEntry.setEntryQuantity(body.getInteger("entryQuantity"));
            }
            if (!"".equals(body.getString("entryType")) && body.getString("entryType") != null) {
                qmsMaterielEntry.setEntryType(body.getString("entryType"));
            }
            if (!"".equals(body.getString("purchaseOrderNumber")) && body.getString("purchaseOrderNumber") != null) {
                qmsMaterielEntry.setPurchaseOrderNumber(body.getString("purchaseOrderNumber"));
            }
            if (!"".equals(body.getString("batchNumber")) && body.getString("batchNumber") != null) {
                qmsMaterielEntry.setBatchNumber(body.getString("batchNumber"));
            }
            if (!"".equals(body.getString("madeYmd")) && body.getString("madeYmd") != null) {
                qmsMaterielEntry.setMadeYmd(body.getString("madeYmd"));
            }
            if (!"".equals(body.getString("madeFactoryCd")) && body.getString("madeFactoryCd") != null) {
                qmsMaterielEntry.setMadeFactoryCd(body.getString("madeFactoryCd"));
            }
            if (!"".equals(body.getString("texTure")) && body.getString("texTure") != null) {
                qmsMaterielEntry.setTexTure(body.getString("texTure"));
            }
            if (!"".equals(body.getString("castingNum")) && body.getString("castingNum") != null) {
                qmsMaterielEntry.setCastingNum(body.getString("castingNum"));
            }
            if (!"".equals(body.getString("materielId")) && body.getString("materielId") != null) {
                qmsMaterielEntry.setMaterielId(body.getInteger("materielId"));
            }
            if (!"".equals(body.getString("supplierId")) && body.getString("supplierId") != null) {
                qmsMaterielEntry.setSupplierId(body.getInteger("supplierId"));
            }
            if (!"".equals(body.getString("entryDate")) && body.getString("entryDate") != null) {
                String entryDate = body.getString("entryDate");
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                entryDate = entryDate.replace("T", " ").replace("Z", "").replace(".000", "");
                qmsMaterielEntry.setEntryDate(dateUtil.getZonedDateByTime(entryDate));
            }
            qmsMaterielEntry.setFlagInspect("0");
            qmsMaterielEntry.setFlagStatus("0");
            qmsMaterielEntry.setMakeUser(user.getUsername());
            qmsMaterielEntry.setMakeTime(dateUtil.getDBNowDate());
            qmsMaterielEntry.setModifyUser(user.getUsername());
            qmsMaterielEntry.setModifyTime(dateUtil.getDBNowDate());
            qmsMaterielEntry = this.qmsMaterielEntryRepository.save(qmsMaterielEntry);
            JSONArray qmsMaterielDetails = body.getJSONArray("qmsMaterielDetails");
            JSONArray enclosures = body.getJSONArray("enclosures");
            if (qmsMaterielDetails.size() > 0) {
                for (int i = 0; i < qmsMaterielDetails.size(); i++) {
                    JSONObject qmsMaterielDetailsObj = qmsMaterielDetails.getJSONObject(i);
                    // ????????????????????????
                    if (qmsMaterielDetailsObj.get("id") != null) {
                        QmsMaterielDetails qmsMaterielDetailsUpdate = this.qmsMaterielDetailsRepository.findByIdAndFlagStatus(qmsMaterielDetailsObj.getLong("id"), "0").get();
                        qmsMaterielDetailsUpdate.setEntryId(qmsMaterielEntry.getId().intValue());
                        if (!"".equals(qmsMaterielDetailsObj.getString("goodsCd")) && qmsMaterielDetailsObj.getString("goodsCd") != null) {
                            qmsMaterielDetailsUpdate.setGoodsCd(qmsMaterielDetailsObj.getString("goodsCd"));
                        }
                        if (!"".equals(qmsMaterielDetailsObj.getString("goodsQuantity")) && qmsMaterielDetailsObj.getString("goodsQuantity") != null) {
                            qmsMaterielDetailsUpdate.setGoodsQuantity(Integer.parseInt(qmsMaterielDetailsObj.getString("goodsQuantity")));
                        }
                        qmsMaterielDetailsUpdate.setModifyUser(user.getUsername());
                        qmsMaterielDetailsUpdate.setModifyTime(dateUtil.getDBNowDate());
                        qmsMaterielDetailsRepository.save(qmsMaterielDetailsUpdate);
                    } else {
                        // ????????????????????????
                        QmsMaterielDetails qmsMaterielDetailsNew = new QmsMaterielDetails();
                        qmsMaterielDetailsNew.setEntryId(qmsMaterielEntry.getId().intValue());
                        if (!"".equals(qmsMaterielDetailsObj.getString("goodsCd")) && qmsMaterielDetailsObj.getString("goodsCd") != null) {
                            qmsMaterielDetailsNew.setGoodsCd(qmsMaterielDetailsObj.getString("goodsCd"));
                        }
                        if (!"".equals(qmsMaterielDetailsObj.getString("goodsQuantity")) && qmsMaterielDetailsObj.getString("goodsQuantity") != null) {
                            qmsMaterielDetailsNew.setGoodsQuantity(qmsMaterielDetailsObj.getInteger("goodsQuantity"));
                        }
                        qmsMaterielDetailsNew.setFlagStatus("0");
                        qmsMaterielDetailsNew.setModifyUser(user.getUsername());
                        qmsMaterielDetailsNew.setModifyTime(dateUtil.getDBNowDate());
                        qmsMaterielDetailsNew.setMakeUser(user.getUsername());
                        qmsMaterielDetailsNew.setMakeTime(dateUtil.getDBNowDate());
                        qmsMaterielDetailsRepository.save(qmsMaterielDetailsNew);
                    }
                }
            }
            if (enclosures.size() > 0) {
                for (int i = 0; i < enclosures.size(); i++) {
                    JSONObject enclosure = enclosures.getJSONObject(i);
                    // ??????
                    if (enclosure.get("id") != null) {
                        Optional<QmsEnclosure> qmsEnclosure = this.qmsEnclosureRepository.findById(enclosure.getLong("id"));
                        if (qmsEnclosure.isPresent()) {
                            QmsEnclosure qmsEnclosureUpdate = qmsEnclosure.get();
                            qmsEnclosureUpdate.setInspectionInfoId(qmsMaterielEntry.getId().intValue());
                            if (!"".equals(enclosure.getString("enclosureAddress")) && enclosure.getString("enclosureAddress") != null) {
                                qmsEnclosureUpdate.setEnclosureAddress(enclosure.getString("enclosureAddress"));
                            }
                            if (!"".equals(enclosure.getString("inspectionInfoId")) && enclosure.getString("inspectionInfoId") != null) {
                                qmsEnclosureUpdate.setInspectionInfoId(enclosure.getInteger("inspectionInfoId"));
                            }
                            qmsEnclosureUpdate.setModifyTime(dateUtil.getDBNowDate());
                            qmsEnclosureUpdate.setModifyUser(user.getUsername());
                            this.qmsEnclosureRepository.save(qmsEnclosureUpdate);
                        }
                    // ???????????????
                    } else {
                        QmsEnclosure qmsEnclosureNew = new QmsEnclosure();
                        qmsEnclosureNew.setInspectionInfoId(qmsMaterielEntry.getId().intValue());
                        if (!"".equals(enclosure.getString("enclosureAddress")) && enclosure.getString("enclosureAddress") != null) {
                            qmsEnclosureNew.setEnclosureAddress(enclosure.getString("enclosureAddress"));
                        }
                        if (!"".equals(enclosure.getString("inspectionInfoId")) && enclosure.getString("inspectionInfoId") != null) {
                            qmsEnclosureNew.setInspectionInfoId(enclosure.getInteger("inspectionInfoId"));
                        }
                        qmsEnclosureNew.setInspectionKbn("1");
                        qmsEnclosureNew.setMakeTime(dateUtil.getDBNowDate());
                        qmsEnclosureNew.setModifyTime(dateUtil.getDBNowDate());
                        qmsEnclosureNew.setMakeUser(user.getUsername());
                        qmsEnclosureNew.setModifyUser(user.getUsername());
                        this.qmsEnclosureRepository.save(qmsEnclosureNew);
                    }
                }
            }
        }
        info.put("status", "1");
    } catch (Exception e) {
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        System.out.println(e);
        info.put("status", "2");
    }
    return info;
}


public String getMaterielEntrySql(String materielCd,String materielName,String figureNumber,String supplierCd,String supplierName,String specificationType,String purchaseOrderNumber,String flagInspect,String enclosure){
    StringBuffer sql = new StringBuffer();
    sql.append("SELECT DISTINCT\n" + "\tt01.id,\n" + "\tt06.prj_name AS checkType,\n" + "\tt02.materiel_cd AS materielCd,\n" + "\tt02.materiel_name AS materielName,\n" + "\tt01.figure_number AS figureNumber,\n" + "\tt01.entry_date AS entryDate,\n" + "\tt03.supplier_cd AS supplierCd,\n" + "\tt03.supplier_name AS supplierName,\n" + "\tt01.specification_type AS specificationType,\n" + "\tt01.purchase_order_number AS purchaseOrderNumber,\n" + "\tCASE\n" + "WHEN t02.check_type = '3' THEN\n" + "\t'-'\n" + "ELSE\n" + "\t(t07.prj_name)\n" + "END AS flagInspect,\n" + " CASE\n" + "WHEN t05.id IS NULL THEN\n" + "\t'???'\n" + "ELSE\n" + "\t'???'\n" + "END AS enclosure\n" + "FROM\n" + "\tqms_materiel_entry t01\n" + "LEFT JOIN qms_materiel t02 ON t01.materiel_id = t02.id\n" + "AND t02.flag_status = '0'\n" + "LEFT JOIN qms_supplier t03 ON t01.supplier_id = t03.id\n" + "AND t03.flag_status = '0'\n" + "LEFT JOIN qms_enclosure t05 ON t05.inspection_info_id = t01.id\n" + "AND t05.inspection_kbn = '1'\n" + "LEFT JOIN qms_master t06 ON t02.check_type = t06.prj_cd\n" + "AND t06.kbn_cd = 'M15'\n" + "LEFT JOIN qms_master t07 ON t01.flag_inspect = t07.prj_cd\n" + "AND t07.kbn_cd = 'M21'\n" + "WHERE\n" + "\tt01.flag_status = '0'\n");
    if (!materielCd.equals("") && materielCd != null) {
        sql.append("AND t02.materiel_cd like :materielCd\n");
    }
    if (!materielName.equals("") && materielName != null) {
        sql.append("AND t02.materiel_name like :materielName\n");
    }
    if (!figureNumber.equals("") && figureNumber != null) {
        sql.append("AND t01.figure_number like :figureNumber\n");
    }
    if (!supplierCd.equals("") && supplierCd != null) {
        sql.append("AND t03.supplier_cd like :supplierCd\n");
    }
    if (!supplierName.equals("") && supplierName != null) {
        sql.append("AND t03.supplier_name like :supplierName\n");
    }
    if (!specificationType.equals("") && specificationType != null) {
        sql.append("AND t01.specification_type like :specificationType\n");
    }
    if (!purchaseOrderNumber.equals("") && purchaseOrderNumber != null) {
        sql.append("AND t01.purchase_order_number like :purchaseOrderNumber\n");
    }
    if (!flagInspect.equals("") && flagInspect != null && !"-1".equals(flagInspect)) {
        if (!"3".equals(flagInspect)) {
            sql.append("AND t01.flag_inspect = :flagInspect AND case when t02.check_type is not null then T02.check_type <> '3' else 1 = 1 end\n");
        } else {
            sql.append("AND (t01.flag_inspect = :flagInspect OR t01.flag_inspect = :flagInspect1) AND case when t02.check_type is not null then T02.check_type <> '3' else 1 = 1 end\n");
        }
    }
    if (!enclosure.equals("") && enclosure != null && !"null".equals(enclosure)) {
        if ("0".equals(enclosure)) {
            sql.append("AND t05.id is null\n");
        } else {
            sql.append("AND t05.id is not null\n");
        }
    }
    sql.append("ORDER BY t01.id\n");
    return sql.toString();
}


}