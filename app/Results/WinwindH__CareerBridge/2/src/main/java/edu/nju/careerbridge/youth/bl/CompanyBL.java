package edu.nju.careerbridge.youth.bl;
 import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.nju.careerbridge.youth.bean;
import edu.nju.careerbridge.youth.blservice.CompanyBLService;
import edu.nju.careerbridge.youth.dao;
import edu.nju.careerbridge.youth.model;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import edu.nju.careerbridge.Interface.JobDetailRepository;
@Service
public class CompanyBL implements CompanyBLService{

@Autowired
 private  CompanyRemarkRepository companyRemarkRepository;

@Autowired
 private  SingleCompanyRemarkRepository singleCompanyRemarkRepository;

@Autowired
 private  CompanyInterviewRemarkRepository companyInterviewRemarkRepository;

@Autowired
 private  SingleInterviewRemarkRepository singleInterviewRemarkRepository;

@Autowired
 private  JobDetailRepository jobDetailRepository;


@Override
public CompanyRemarkBean getCompanyRemark(String company){
    CompanyRemarkBean companyRemarkBean = new CompanyRemarkBean();
    List<SingleCompanyRemark> list = singleCompanyRemarkRepository.findByCompany(company);
    double avgRecomandScore = 0.0;
    double avgFutureScore = 0.0;
    double avgCeoScore = 0.0;
    List<SingleCompanyRemarkBean> beansList = new ArrayList<SingleCompanyRemarkBean>();
    for (SingleCompanyRemark singleCompanyRemark : list) {
        SingleCompanyRemarkBean singleCompanyRemarkBean = new SingleCompanyRemarkBean();
        BeanUtils.copyProperties(singleCompanyRemark, singleCompanyRemarkBean);
        beansList.add(singleCompanyRemarkBean);
        avgRecomandScore += singleCompanyRemark.getRecomandScore();
        avgFutureScore += singleCompanyRemark.getFutureScore();
        avgCeoScore += singleCompanyRemark.getCeoScore();
    }
    avgRecomandScore /= list.size();
    avgFutureScore /= list.size();
    avgCeoScore /= list.size();
    companyRemarkBean.setCompany(company);
    companyRemarkBean.setAvgRecomandScore(avgRecomandScore);
    companyRemarkBean.setAvgFutureScore(avgFutureScore);
    companyRemarkBean.setAvgCeoScore(avgCeoScore);
    companyRemarkBean.setSingleCompanyRemarkBeans(beansList);
    return companyRemarkBean;
}


@Override
public JobDetailBean getJobDetailByJobId(String jobId){
    JobDetailBean jobDetailBean = new JobDetailBean();
    JobDetail jobDetail = jobDetailRepository.findByJobId(jobId);
    if (jobDetail != null) {
        BeanUtils.copyProperties(jobDetail, jobDetailBean);
    }
    return jobDetailBean;
}


@Override
@Transactional
public ResultMessageBean companyRemark(String phone,String company,int recomandScore,int futureScore,int ceoScore,String remark){
    // 更新single_company_remark表
    SingleCompanyRemark s = new SingleCompanyRemark(phone, company, recomandScore, futureScore, ceoScore, remark);
    try {
        singleCompanyRemarkRepository.save(s);
    } catch (Exception e) {
        return new ResultMessageBean(false, "填写公司评价信息失败");
    }
    // 更新company_remark表
    List<SingleCompanyRemark> list = singleCompanyRemarkRepository.findByCompany(company);
    double avgRecomandScore = 0.0;
    double avgFutureScore = 0.0;
    double avgCeoScore = 0.0;
    for (SingleCompanyRemark singleCompanyRemark : list) {
        avgRecomandScore += singleCompanyRemark.getRecomandScore();
        avgFutureScore += singleCompanyRemark.getFutureScore();
        avgCeoScore += singleCompanyRemark.getCeoScore();
    }
    avgRecomandScore /= list.size();
    avgFutureScore /= list.size();
    avgCeoScore /= list.size();
    CompanyRemark companyRemark = new CompanyRemark(company, avgRecomandScore, avgFutureScore, avgCeoScore);
    try {
        // 先删除再插入=更新
        companyRemarkRepository.deleteByCompany(company);
        companyRemarkRepository.save(companyRemark);
    } catch (Exception e) {
        return new ResultMessageBean(false, "填写公司评价信息失败");
    }
    return new ResultMessageBean(true);
}


@Override
public CompanyInterviewRemarkBean getInterviewRemark(String company){
    CompanyInterviewRemarkBean companyInterviewRemarkBean = new CompanyInterviewRemarkBean();
    List<SingleInterviewRemark> list = singleInterviewRemarkRepository.findByCompany(company);
    double avgDifficulty = 0.0;
    double avgFeeling = 0.0;
    List<SingleInterviewRemarkBean> beansList = new ArrayList<SingleInterviewRemarkBean>();
    for (SingleInterviewRemark singleInterviewRemark : list) {
        SingleInterviewRemarkBean singleInterviewRemarkBean = new SingleInterviewRemarkBean();
        BeanUtils.copyProperties(singleInterviewRemark, singleInterviewRemarkBean);
        beansList.add(singleInterviewRemarkBean);
        avgDifficulty += singleInterviewRemark.getDifficulty();
        avgFeeling += singleInterviewRemark.getFeeling();
    }
    avgDifficulty /= list.size();
    avgFeeling /= list.size();
    companyInterviewRemarkBean.setCompany(company);
    companyInterviewRemarkBean.setAvgDifficulty(avgDifficulty);
    companyInterviewRemarkBean.setAvgFeeling(avgFeeling);
    companyInterviewRemarkBean.setSingleInterviewRemarkBeans(beansList);
    return companyInterviewRemarkBean;
}


@Override
public ResultMessageBean interviewRemark(String phone,String company,String result,int difficulty,int feeling,String remark){
    // 更新single_interview_remark表
    SingleInterviewRemark s = new SingleInterviewRemark(phone, company, result, difficulty, feeling, remark);
    try {
        singleInterviewRemarkRepository.save(s);
    } catch (Exception e) {
        return new ResultMessageBean(false, "填写面试评价信息失败");
    }
    // 更新company_interview_remark表
    List<SingleInterviewRemark> list = singleInterviewRemarkRepository.findByCompany(company);
    double avgDifficulty = 0.0;
    double avgFeeling = 0.0;
    for (SingleInterviewRemark singleInterviewRemark : list) {
        avgDifficulty += singleInterviewRemark.getDifficulty();
        avgFeeling += singleInterviewRemark.getFeeling();
    }
    avgDifficulty /= list.size();
    avgFeeling /= list.size();
    CompanyInterviewRemark companyInterviewRemark = new CompanyInterviewRemark(company, avgDifficulty, avgFeeling);
    try {
        // 先删除再插入=更新
        companyInterviewRemarkRepository.deleteByCompany(company);
        companyInterviewRemarkRepository.save(companyInterviewRemark);
    } catch (Exception e) {
        return new ResultMessageBean(false, "填写面试评价信息失败");
    }
    return new ResultMessageBean(true);
}


}