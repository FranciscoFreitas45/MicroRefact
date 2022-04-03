package edu.nju.careerbridge.youth.blservice;
 import edu.nju.careerbridge.youth.bean.ResultMessageBean;
public interface JobBLService {


public ResultMessageBean dislikeJob(String phone,String jobId)
;

public ResultMessageBean cancelLikeJob(String phone,String jobId)
;

public ResultMessageBean scanJob(String phone,String jobId)
;

public ResultMessageBean likeJob(String phone,String jobId)
;

}