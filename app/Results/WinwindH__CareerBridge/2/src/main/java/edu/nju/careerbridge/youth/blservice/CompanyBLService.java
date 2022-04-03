package edu.nju.careerbridge.youth.blservice;
 import edu.nju.careerbridge.youth.bean;
public interface CompanyBLService {


public CompanyRemarkBean getCompanyRemark(String company)
;

public JobDetailBean getJobDetailByJobId(String jobId)
;

public ResultMessageBean companyRemark(String phone,String company,int recomandScore,int futureScore,int ceoScore,String remark)
;

public CompanyInterviewRemarkBean getInterviewRemark(String company)
;

public ResultMessageBean interviewRemark(String phone,String company,String result,int difficulty,int feeling,String remark)
;

}