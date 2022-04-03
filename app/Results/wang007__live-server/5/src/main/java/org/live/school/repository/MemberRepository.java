package org.live.school.repository;
 import org.live.common.base.BaseRepository;
import org.live.live.vo.MobileUserVo;
import org.live.school.entity.Member;
import org.live.school.vo.MemberVo;
import org.live.school.vo.SimpleMemberVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Map;
public interface MemberRepository extends BaseRepository<Member, String>{


public Page<MemberVo> find(PageRequest pageRequest,Map<String,Object> filter)
;

public Member findMemberBymemberNo(String memberNo)
;

@Query("select new org.live.school.vo.SimpleMemberVo(m.id, m.memberNo, m.realName) from Member m where m.realName like %:realName%")
public List<SimpleMemberVo> findByRealNameLike(String realName)
;

public Member getMember(String idU3LS);

public void setMember(String idU3LS,Member member);

}