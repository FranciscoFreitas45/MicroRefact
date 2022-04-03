package goorum.goorum.service;
 import goorum.goorum.domain.Mypage;
import goorum.goorum.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class MypageServiceImpl implements MypageService{

@Autowired
 private  MypageRepository mypageRepository;


@Override
public Mypage getMypageInfo(long memberId){
    return mypageRepository.findById(memberId).orElse(null);
}


}