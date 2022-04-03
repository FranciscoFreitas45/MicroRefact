package goorum.goorum.service;
 import goorum.goorum.domain.Member;
import java.security.NoSuchAlgorithmException;
import java.util.List;
public interface MemberService {


public List<Member> getList()
;

public Member join(String id,String pwd,String email,String nick)
;

public Member login(String id,String pwd)
;

public boolean isDuplicate(String id)
;

public boolean setProfilePhoto(long memberId,String profilePath)
;

}