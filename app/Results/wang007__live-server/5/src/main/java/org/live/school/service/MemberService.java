package org.live.school.service;
 import org.live.common.base.BaseService;
import org.live.common.response.DataTableModel;
import org.live.school.entity.Member;
import org.live.school.vo.SimpleMemberVo;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
public interface MemberService extends BaseService<Member, String>{


public List<SimpleMemberVo> findByRealName(String realName)
;

public DataTableModel findPage(HttpServletRequest request)
;

public Member findMemberBymemberNo(String memberNo)
;

}