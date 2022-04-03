package edu.nju.careerbridge.youth.bl;
 import org.springframework.stereotype.Service;
import edu.nju.careerbridge.youth.bean.ResultMessageBean;
import edu.nju.careerbridge.youth.blservice.JobBLService;
import edu.nju.careerbridge.youth.dao.DislikedJobRepository;
import edu.nju.careerbridge.youth.dao.LikedJobRepository;
import edu.nju.careerbridge.youth.dao.ScannedJobRepository;
import edu.nju.careerbridge.youth.model.DisLikedJob;
import edu.nju.careerbridge.youth.model.LikedJob;
import edu.nju.careerbridge.youth.model.ScannedJob;
@Service
public class JobBL implements JobBLService{

 private  LikedJobRepository likedJobRepository;

 private  DislikedJobRepository dislikedJobRepository;

 private  ScannedJobRepository scannedJobRepository;

public JobBL(LikedJobRepository likedJobRepository, DislikedJobRepository dislikedJobRepository, ScannedJobRepository scannedJobRepository) {
    this.likedJobRepository = likedJobRepository;
    this.dislikedJobRepository = dislikedJobRepository;
    this.scannedJobRepository = scannedJobRepository;
}
@Override
public ResultMessageBean dislikeJob(String phone,String jobId){
    try {
        dislikedJobRepository.save(new DisLikedJob(phone, jobId));
    } catch (Exception e) {
        return new ResultMessageBean(false, "记录保存失败");
    }
    return new ResultMessageBean(true);
}


@Override
public ResultMessageBean cancelLikeJob(String phone,String jobId){
    try {
        likedJobRepository.deleteByPhoneAndJobId(phone, jobId);
    } catch (Exception e) {
        System.out.println(e);
        return new ResultMessageBean(false, "删除失败");
    }
    return new ResultMessageBean(true);
}


@Override
public ResultMessageBean scanJob(String phone,String jobId){
    try {
        scannedJobRepository.save(new ScannedJob(phone, jobId));
    } catch (Exception e) {
        return new ResultMessageBean(false, "浏览记录保存失败");
    }
    return new ResultMessageBean(true);
}


@Override
public ResultMessageBean likeJob(String phone,String jobId){
    try {
        likedJobRepository.save(new LikedJob(phone, jobId));
    } catch (Exception e) {
        return new ResultMessageBean(false, "保存失败");
    }
    return new ResultMessageBean(true);
}


}