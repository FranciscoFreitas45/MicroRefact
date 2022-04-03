package edu.nju.careerbridge.youth.bl;
 import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.nju.careerbridge.youth.bean;
import edu.nju.careerbridge.youth.blservice.CompanyBLService;
import edu.nju.careerbridge.youth.blservice.StatisticsBLService;
import edu.nju.careerbridge.youth.dao;
import edu.nju.careerbridge.youth.model;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
public class StatisticsBL implements StatisticsBLService{

@Autowired
 private  CompanyDescriptionOutputRepository companyDescriptionOutputRepository;

@Autowired
 private  JobDescriptionOutputRepository jobDescriptionOutputRepository;


@Override
public List<CompanyDescOutputBean> getCompanyKeywords(String companyName){
    List<CompanyDescOutputBean> beansList = new ArrayList<CompanyDescOutputBean>();
    for (CompanyDescriptionOutput c : companyDescriptionOutputRepository.findByCompanyName(companyName)) {
        CompanyDescOutputBean companyDescOutputBean = new CompanyDescOutputBean();
        BeanUtils.copyProperties(c, companyDescOutputBean);
        beansList.add(companyDescOutputBean);
    }
    return beansList;
}


@Override
public List<JobDescOutputBean> getJobKeywordsByJobId(String jobId){
    List<JobDescOutputBean> beansList = new ArrayList<JobDescOutputBean>();
    for (JobDescriptionOutput c : jobDescriptionOutputRepository.findByJobId(jobId)) {
        JobDescOutputBean jobDescOutputBean = new JobDescOutputBean();
        BeanUtils.copyProperties(c, jobDescOutputBean);
        beansList.add(jobDescOutputBean);
    }
    return beansList;
}


}