package cn.com.cnc.fcc.service.impl;
 import cn.com.cnc.fcc.domain.QmsProcess;
import cn.com.cnc.fcc.domain.QmsProcessExcelDto;
import cn.com.cnc.fcc.domain.QmsSupplierExcelDto;
import cn.com.cnc.fcc.repository.QmsProcessRepository;
import cn.com.cnc.fcc.service.QmsProcessService;
import cn.com.cnc.fcc.service.util.DateUtil;
import cn.com.cnc.fcc.service.util.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class QmsProcessServiceImpl implements QmsProcessService{

 private  Logger log;

 private  QmsProcessRepository qmsProcessRepository;

 private  String dataFormat;

@Resource
 private  DateUtil dateUtil;

public QmsProcessServiceImpl(QmsProcessRepository qmsProcessRepository) {
    this.qmsProcessRepository = qmsProcessRepository;
}
@Override
public JSONObject uploadData(MultipartFile files){
    // 返回值设置
    JSONObject returnData = new JSONObject();
    // session取得用户信息
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    // 取得用户信息
    UserDetails user = (UserDetails) authentication.getPrincipal();
    // 用于将得到的数据存在真实的QmsProcess中，之后进行数据插入的操作
    List<QmsProcess> QmsProcess = new ArrayList<QmsProcess>();
    // 获取所有的工序编码
    QmsProcess = qmsProcessRepository.findAll();
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
            JSONArray data = ExcelUtil.getExcelAllData(files.getInputStream(), QmsProcessExcelDto.class, 3);
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
                    QmsProcess qmsProcess = new QmsProcess();
                    // 首先进行(工序编码长度)以及(主键不能重复)以及(不能为空)的Check
                    if ("".equals(data.getJSONObject(i).getString("processCd"))) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,工序编码不能为空");
                        returnData.put("flag", "0");
                        continue;
                    }
                    if (data.getJSONObject(i).getString("processCd").length() > 10) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,工序编码长度不能超过10");
                        returnData.put("flag", "0");
                        continue;
                    }
                    List<QmsProcess> q1 = qmsProcessRepository.findByProcessCd(data.getJSONObject(i).getString("processCd"));
                    if (q1.size() != 0) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,工序编码不能重复");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsProcess.setProcessCd(data.getJSONObject(i).getString("processCd"));
                    // 工序名称
                    if (data.getJSONObject(i).getString("processName").length() > 100) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,工序名称长度不能超过100");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsProcess.setProcessName(data.getJSONObject(i).getString("processName"));
                    // 备注
                    if (data.getJSONObject(i).getString("remark").length() > 200) {
                        errorMess.add("第" + (i + 1) + "条数据有错误,备注长度不能超过200");
                        returnData.put("flag", "0");
                        continue;
                    }
                    qmsProcess.setRemark(data.getJSONObject(i).getString("remark"));
                    // 删除flag
                    qmsProcess.setFlagStatus("0");
                    // 插入数据人
                    qmsProcess.setMakeUser(user.getUsername());
                    // 更新数据人
                    qmsProcess.setModifyUser(user.getUsername());
                    // 插入时间
                    qmsProcess.setMakeTime(dateUtil.getDBNowDate());
                    // 更新时间
                    qmsProcess.setModifyTime(dateUtil.getDBNowDate());
                    // 其他为""
                    qmsProcess.setCompPkid("");
                    qmsProcessRepository.save(qmsProcess);
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


@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
@Override
public Page<QmsProcess> qmsProcessFindAll(String bianMa,String gongName,Pageable pageable){
    // 初始化结果集
    Page<QmsProcess> resultInfo = null;
    Specification querySpecifi = new Specification<QmsProcess>() {

        @Override
        public Predicate toPredicate(Root<QmsProcess> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            try {
                // 判断模糊查询供应商编码是否为空
                if (!"".equals(bianMa)) {
                    predicates.add(criteriaBuilder.like(root.get("processCd"), "%" + bianMa + "%"));
                }
                // 判断模糊查询供应商名称是否为空
                if (!"".equals(gongName)) {
                    predicates.add(criteriaBuilder.like(root.get("processName"), "%" + gongName + "%"));
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
    resultInfo = qmsProcessRepository.findAll(querySpecifi, pageable);
    // 返回结果
    return resultInfo;
}


@Override
public Predicate toPredicate(Root<QmsProcess> root,CriteriaQuery<?> criteriaQuery,CriteriaBuilder criteriaBuilder){
    List<Predicate> predicates = new ArrayList<Predicate>();
    try {
        // 判断模糊查询供应商编码是否为空
        if (!"".equals(bianMa)) {
            predicates.add(criteriaBuilder.like(root.get("processCd"), "%" + bianMa + "%"));
        }
        // 判断模糊查询供应商名称是否为空
        if (!"".equals(gongName)) {
            predicates.add(criteriaBuilder.like(root.get("processName"), "%" + gongName + "%"));
        }
        // 检索未删除的数据
        predicates.add(criteriaBuilder.equal(root.get("flagStatus"), "0"));
    } catch (Exception e) {
        log.info(e.getMessage());
    }
    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
}


}