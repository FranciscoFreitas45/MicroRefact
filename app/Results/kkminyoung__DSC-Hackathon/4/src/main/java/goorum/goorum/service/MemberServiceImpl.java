package goorum.goorum.service;
 import goorum.goorum.domain.Member;
import goorum.goorum.repository.MemberRepository;
import goorum.goorum.util.HashFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
@Service
public class MemberServiceImpl implements MemberService{

@Autowired
 private  MemberRepository memberRepository;


@Override
public List<Member> getList(){
    return memberRepository.findAll();
}


@Override
public Member join(String id,String pwd,String email,String nick){
    return memberRepository.save(Member.builder().id(id).pwd(HashFunction.sha256(pwd)).email(email).nickname(nick).build());
}


@Override
public Member login(String id,String pwd){
    Optional<Member> loginMember = memberRepository.findByIdAndPwd(id, HashFunction.sha256(pwd));
    if (!loginMember.isPresent()) {
        return null;
    }
    Member member = loginMember.get();
    memberRepository.save(member);
    return member;
}


@Override
public boolean isDuplicate(String id){
    Optional<Member> member = memberRepository.findById(id);
    return member.isPresent();
}


@Override
public boolean setProfilePhoto(long memberId,String profilePath){
    Optional<Member> memberOptional = memberRepository.findById(memberId);
    if (!memberOptional.isPresent()) {
        return false;
    }
    Member member = memberOptional.get();
    member.setProfilePhoto(profilePath);
    memberRepository.save(member);
    return true;
}


}