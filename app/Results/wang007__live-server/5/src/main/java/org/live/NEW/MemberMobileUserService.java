package org.live.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.school.repository.MemberRepository;
import org.live.school.entity.Member;
@Service
public class MemberMobileUserService {

@Autowired
 private MemberRepository memberrepository;


public Member getMember(String idU3LS){
return memberrepository.getMember(idU3LS);
}


public void setMember(String idU3LS,Member member){
memberrepository.setMember(idU3LS,member);
}


}