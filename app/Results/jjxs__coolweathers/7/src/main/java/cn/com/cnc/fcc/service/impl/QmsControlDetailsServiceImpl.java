package cn.com.cnc.fcc.service.impl;
 import cn.com.cnc.fcc.domain.QmsControlDetails;
import cn.com.cnc.fcc.domain.QmsControlDetailsExcelDto;
import cn.com.cnc.fcc.domain.QmsUnit;
import cn.com.cnc.fcc.domain.QmsUnitExcelDto;
import cn.com.cnc.fcc.repository.QmsControlDetailsRepository;
import cn.com.cnc.fcc.repository.QmsUnitRepository;
import cn.com.cnc.fcc.service.QmsControlDetailsService;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
@Service
public class QmsControlDetailsServiceImpl implements QmsControlDetailsService{

 private  Logger log;

 private  QmsControlDetailsRepository qmsControlDetailsRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

public QmsControlDetailsServiceImpl(QmsControlDetailsRepository qmsControlDetailsRepository) {
    this.qmsControlDetailsRepository = qmsControlDetailsRepository;
}
@Override
public JSONObject uploadData(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 用于将得到的数据存在真实的QmsUnit中，之后进行数据插入的操作
    List<QmsControlDetails> QmsControlDetails = new ArrayList<QmsControlDetails>();
    // 获取所有的检验项目编码
    QmsControlDetails = qmsControlDetailsRepository.findAll();
    // 存错误信息的数组
    List<String> errorMess = new ArrayList<String>();
    returnData.put("flag", "1");
    // 判断文件路径
    if (files.isEmpty()) {
        returnData.put("status", "error");
        returnData.put("message", "请重新上传！");
    } else {
        try {
            // 取数据
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), QmsControlDetailsExcelDto.class, 6);
            System.out.println(data);
            // 判断数据
            if (data.size() <= 0) {
                returnData.put("status", "error");
                returnData.put("message", "表格内无数据！");
                return returnData;
            } else {
                if ("longColumns".equals(data.getJSONObject(0).getString("errorColumns"))) {
                    // 返回错误信息：表格内无数据
                    returnData.put("status", "error");
                    returnData.put("message", "列数不对！");
                    return returnData;
                }
                for (int i = 0; i < data.size(); i++) {
                    // 进行数据的所有Check
                    QmsControlDetails qmsControlDetails = new QmsControlDetails();
                    // 首先进行(检验项目编码长度)以及(主键不能重复)以及(不能为空)的Check
                    if ("".equals(data.getJSONObject(i).getString("inspectionCd"))) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,检验项目编码不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (data.getJSONObject(i).getString("inspectionCd").length() > 10) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,检验项目编码长度不能超过10");
                        returnData.put("flag", "0");
                        continue;
                    }
                    List<QmsControlDetails> q1 = qmsControlDetailsRepository.findByInspectionCd(data.getJSONObject(i).getString("inspectionCd"));
                    if (q1.size() != 0) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,检验项目编码不能重复");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsControlDetails.setInspectionCd(data.getJSONObject(i).getString("inspectionCd"));
                    // 检查项目(不能为空)
                    if (data.getJSONObject(i).getString("inspectionItem").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,检查项目名称长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (data.getJSONObject(i).getString("inspectionItem") == "") {
                        errorMess.add("第" + (i + 1) + "条数据有错误,检查项目不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsControlDetails.setInspectionItem(data.getJSONObject(i).getString("inspectionItem"));
                    // 检查器具
                    if (data.getJSONObject(i).getString("inspectionInstrument").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,检查器具长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsControlDetails.setInspectionInstrument(data.getJSONObject(i).getString("inspectionInstrument"));
                    // 结果区分
                    if (data.getJSONObject(i).getString("inspectionResultDiff") != "") {
                        if (!"0".equals(data.getJSONObject(i).getString("inspectionResultDiff")) && !"1".equals(data.getJSONObject(i).getString("inspectionResultDiff"))) {
                            errorMess.add("第" + (i + 1) + "条数据有错误,结果区分必须为0或1");
                            returnData.put("flag", "0");
                            continue;
                        } else {
                            qmsControlDetails.setInspectionResultDiff(data.getJSONObject(i).getString("inspectionResultDiff"));
                        }
                    } else {
                        errorMess.add("第" + (i + 1) + "条数据有错误,结果区分必须为0或1");
                        returnData.put("flag", "0");
                        continue;
                    }
                    // 技术要求
                    if (data.getJSONObject(i).getString("technicalRequirement").length() > 200) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,技术要求长度不能超过200");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsControlDetails.setTechnicalRequirement(data.getJSONObject(i).getString("technicalRequirement"));
                    // 备注
                    if (data.getJSONObject(i).getString("remark").length() > 200) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,备注长度不能超过200");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsControlDetails.setRemark(data.getJSONObject(i).getString("remark"));
                    // 删除flag
                    qmsControlDetails.setFlagStatus("0");
                    // 插入数据人
                    qmsControlDetails.setMakeUser(user.getUsername());
                    // 更新数据人
                    qmsControlDetails.setModifyUser(user.getUsername());
                    // 插入时间
                    qmsControlDetails.setMakeTime(dateUtil.getDBNowDate());
                    // 更新时间
                    qmsControlDetails.setModifyTime(dateUtil.getDBNowDate());
                    // 其他项目为""
                    qmsControlDetails.setCompPkid("");
                    qmsControlDetails.setReserveFirst("");
                    qmsControlDetails.setReserveSecond("");
                    qmsControlDetails.setReserveThird("");
                    qmsControlDetailsRepository.save(qmsControlDetails);
                }
                if (returnData.getString("flag") == "1") {
                    returnData.put("status", "success");
                    returnData.put("message", "操作成功!本次一共处理" + data.size() + "条数据");
                } else {
                    // 将所有的错误信息拼接成一个字符串
                    String error = "";
                    for (int i = 0; i < errorMess.size(); i++) {
                        error = error + errorMess.get(i) + "。";
                    }
                    returnData.put("status", "success");
                    returnData.put("message1", "成功件数:" + (data.size() - errorMess.size()) + "件。" + "失败件数:" + errorMess.size() + "件。");
                    returnData.put("message2", "失败原因:" + error);
                }
            }
        } catch (Exception e) {
            returnData.put("status", "error");
            returnData.put("message", "数据导入失败！");
        }
    }
    return returnData;
}


@Override
public Page<QmsControlDetails> qmsControlDetailsFindAll(String xiangmu,Pageable pageable){
    // 初始化结果集
    Page<QmsControlDetails> resultInfo = null;
    Specification querySpecifi = new Specification<QmsControlDetails>() {

        @Override
        public Predicate toPredicate(Root<QmsControlDetails> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            try {
                // 判断模糊查询供应商编码是否为空
                if (!"".equals(xiangmu)) {
                    predicates.add(criteriaBuilder.like(root.get("inspectionItem"), "%" + xiangmu + "%"));
                }
                // 检索未删除的数据
                predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
            } catch (Exception e) {
                log.info(e.getMessage());
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }
    };
    // 带检索条件查询数据
    resultInfo = qmsControlDetailsRepository.findAll(querySpecifi, pageable);
    // 返回结果
    return resultInfo;
}


@Override
public Predicate toPredicate(Root<QmsControlDetails> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<Predicate>();
    try {
        // 判断模糊查询供应商编码是否为空
        if (!"".equals(xiangmu)) {
            predicates.add(criteriaBuilder.like(root.get("inspectionItem"), "%" + xiangmu + "%"));
        }
        // 检索未删除的数据
        predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
}


}