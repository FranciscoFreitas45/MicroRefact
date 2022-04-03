package edu.nju.careerbridge.youth.bl;
 import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import edu.nju.careerbridge.util.FirstLetterUtil;
import edu.nju.careerbridge.util.LocationUtils;
import edu.nju.careerbridge.youth.bean.JobListBean;
import edu.nju.careerbridge.youth.bean.SearchBean;
import edu.nju.careerbridge.youth.blservice.JobListBLService;
import edu.nju.careerbridge.youth.dao;
import edu.nju.careerbridge.youth.model;
import javax.persistence.criteria;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class JobListBL implements JobListBLService{

 private  Map<String,Integer> educationDegreeMap;

 private  Map<String,Integer> companyNatureMap;

@Autowired
 private  JobDetailRepository jobDetailRepository;

@Autowired
 private  JobVectorRepository jobVectorRepository;

@Autowired
 private  KeywordsRepository keywordsRepository;

@Autowired
 private  JobClassificationRepository jobClassificationRepository;


@Override
public edu.nju.careerbridge.util.Page<JobListBean> search(String keyword,int page,int num){
    String jp = FirstLetterUtil.toJP(keyword);
    // int classification=0;
    List<Keywords> keywordsList = keywordsRepository.findThroughKey(jp);
    // System.out.println(keywordsList);
    // Keywords keywords= keywordsRepository.findByKeywords(jp);
    // if(keywords==null){
    // classification=22;
    // }else{
    // classification=keywords.getClassification();
    // }
    final List<Integer> classificationList = new ArrayList<Integer>();
    if (keywordsList.size() == 0) {
        classificationList.add(new Integer(22));
    } else {
        for (Keywords keywords : keywordsList) {
            classificationList.add(keywords.getClassification());
        }
    }
    System.out.println("【开始】" + classificationList.size());
    edu.nju.careerbridge.util.Page<JobListBean> res = new edu.nju.careerbridge.util.Page<JobListBean>();
    res.setSize(num);
    res.setPage(page);
    // final JobClassification jobClassification=new JobClassification();
    // jobClassification.setClassification(classification);
    org.springframework.data.domain.Page<JobClassification> result = null;
    result = jobClassificationRepository.findAll(new Specification<JobClassification>() {

        @Override
        public Predicate toPredicate(Root<JobClassification> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> list = new ArrayList<Predicate>();
            List<Predicate> list2 = new ArrayList<Predicate>();
            Path<Integer> dbClassification = root.get("classification");
            for (Integer myInteger : classificationList) {
                list2.add(cb.equal(dbClassification, myInteger));
            }
            Predicate[] p2 = new Predicate[list2.size()];
            list.add(cb.or(list2.toArray(p2)));
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        }
    }, PageRequest.of(page - 1, num));
    List<JobListBean> jobListBeans = new ArrayList<JobListBean>();
    for (JobClassification jobClassification1 : result.getContent()) {
        JobListBean jobListBean = new JobListBean();
        BeanUtils.copyProperties(jobDetailRepository.findByJobId(jobClassification1.getJobId()), jobListBean);
        jobListBeans.add(jobListBean);
    }
    res.setResult(jobListBeans);
    res.setTotalCount((int) result.getTotalElements());
    System.out.println("【开始】" + result.getTotalElements());
    return res;
}


@Override
public List<JobListBean> getRecomandJobList(String phone){
    return null;
}


@Override
public edu.nju.careerbridge.util.Page<JobListBean> searchJob(SearchBean searchBean){
    // searchBean里面的属性在这里默认不为空，所以前端要在搜索时必须保证所有选项都不为空
    edu.nju.careerbridge.util.Page<JobListBean> res = new edu.nju.careerbridge.util.Page<JobListBean>();
    res.setSize(searchBean.getNum());
    res.setPage(searchBean.getPage());
    final JobVector jobVector = new JobVector();
    jobVector.setCompanyNature(companyNatureMap.get(searchBean.getCompanyNature()));
    jobVector.setEducationDegree(educationDegreeMap.get(searchBean.getEducationDegree()));
    jobVector.setJobExp(searchBean.getJobExperience());
    org.springframework.data.domain.Page<JobVector> result = null;
    result = jobVectorRepository.findAll(new Specification<JobVector>() {

        @Override
        public Predicate toPredicate(Root<JobVector> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> list = new ArrayList<Predicate>();
            List<Predicate> list2 = new ArrayList<Predicate>();
            // System.out.println("我就在这里面啊，真没事");
            // Path<>
            Path<Integer> companyNature = root.get("companyNature");
            Path<Integer> educationDegree = root.get("educationDegree");
            Path<Double> jobExp = root.get("jobExp");
            Path<String> jobName = root.get("jobName");
            Path<String> jobCity = root.get("jobCity");
            if (searchBean.getCompanyNature() != null && !"".equals(searchBean.getCompanyNature())) {
                list.add(cb.equal(companyNature, jobVector.getCompanyNature()));
            }
            if (searchBean.getEducationDegree() != null && !"".equals(searchBean.getEducationDegree())) {
                list.add(cb.ge(educationDegree, jobVector.getEducationDegree()));
            }
            if (searchBean.getJobExperience() != null) {
                list.add(cb.ge(jobExp, jobVector.getJobExp()));
            }
            if (searchBean.getJobName() != null && !"".equals(searchBean.getJobName())) {
                list.add(cb.like(jobName, "%" + searchBean.getJobName() + "%"));
            }
            if (searchBean.getJobCityList() != null) {
                for (String s : searchBean.getJobCityList()) {
                    list2.add(cb.equal(jobCity, s));
                }
                Predicate[] p2 = new Predicate[list2.size()];
                list.add(cb.or(list2.toArray(p2)));
            }
            Predicate[] p = new Predicate[list.size()];
            return cb.and(list.toArray(p));
        }
    }, PageRequest.of(searchBean.getPage() - 1, searchBean.getNum()));
    List<JobListBean> jobListBeans = new ArrayList<JobListBean>();
    for (JobVector jobVector1 : result.getContent()) {
        JobListBean jobListBean = new JobListBean();
        BeanUtils.copyProperties(jobDetailRepository.findByJobId(Integer.toString(jobVector1.getJobId())), jobListBean);
        // 计算matchingDegree
        double matchingDegree = 0.0;
        // double min=100000000;//10万公里足够了
        // for(String s:searchBean.getJobCityList()){
        // JobVector jobVector2=jobVectorRepository.findByJobCity(s);
        // double lat=jobVector2.getLat();
        // double lng=jobVector2.getLng();
        // double dis=LocationUtils.getDistance(jobVector1.getLat(),jobVector1.getLng(),lat,lng);
        // if( dis<min) {
        // min = dis;
        // }
        // }
        double cityMatch = 1;
        int dbSalary = jobVector1.getSalaryMid();
        int expectSalary = searchBean.getSalary();
        double salaryMatch = 1 - (double) Math.abs(dbSalary - expectSalary) / (double) (dbSalary > expectSalary ? dbSalary : expectSalary);
        int expectEdu = jobVector.getEducationDegree();
        int dbEdu = jobVector1.getEducationDegree();
        double eduMatch = 0;
        if (expectEdu == dbEdu) {
            eduMatch = 1;
        } else {
            eduMatch = 0.5;
        }
        matchingDegree = cityMatch * 0.22 + salaryMatch * 0.25 + 0.18 + eduMatch * 0.21 + 0.13;
        jobListBean.setMatchingDegree(matchingDegree);
        jobListBeans.add(jobListBean);
    }
    res.setResult(jobListBeans);
    res.setTotalCount((int) result.getTotalElements());
    System.out.println("【开始】" + result.getTotalElements());
    return res;
}


@Override
public List<JobListBean> getSavedJobList(String phone){
    ArrayList<JobDetail> jobDetails = (ArrayList<JobDetail>) jobDetailRepository.getSavedJob(phone);
    ArrayList<JobListBean> jobListBeans = new ArrayList<JobListBean>();
    for (JobDetail jobDetail : jobDetails) {
        JobListBean jobListBean = new JobListBean();
        System.out.println("jobId" + jobDetail.getJobId());
        BeanUtils.copyProperties(jobDetail, jobListBean);
        jobListBeans.add(jobListBean);
    }
    return jobListBeans;
}


@Override
public Predicate toPredicate(Root<JobClassification> root,CriteriaQuery<?> query,CriteriaBuilder cb){
    List<Predicate> list = new ArrayList<Predicate>();
    List<Predicate> list2 = new ArrayList<Predicate>();
    Path<Integer> dbClassification = root.get("classification");
    for (Integer myInteger : classificationList) {
        list2.add(cb.equal(dbClassification, myInteger));
    }
    Predicate[] p2 = new Predicate[list2.size()];
    list.add(cb.or(list2.toArray(p2)));
    Predicate[] p = new Predicate[list.size()];
    return cb.and(list.toArray(p));
}


}