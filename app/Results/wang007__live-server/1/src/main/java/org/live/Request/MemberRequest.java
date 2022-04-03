package org.live.Request;
import org.live.DTO.Member;
public interface MemberRequest {

   public Member getMember(String idU3LS);
   public void setMember(Member member,String idU3LS);
}