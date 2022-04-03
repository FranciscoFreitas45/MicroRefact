package org.live.school.service.impl;
 import org.live.common.base.BaseRepository;
import org.live.common.base.BaseServiceImpl;
import org.live.common.response.DataTableModel;
import org.live.common.utils.DataTableUtils;
import org.live.school.entity.Member;
import org.live.school.repository.MemberRepository;
import org.live.school.service.MemberService;
import org.live.school.vo.MemberVo;
import org.live.school.vo.SimpleMemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@Service(value = "memberService")
public class MemberServiceImpl extends BaseServiceImpl<Member, String>implements MemberService{

@Autowired
 private  MemberRepository memberRepository;


@Override
public BaseRepository<Member,String> getRepository(){
    return memberRepository;
}


@Override
public List<SimpleMemberVo> findByRealName(String realName){
    return memberRepository.findByRealNameLike(realName);
}


public DataTableModel findPage(HttpServletRequest request){
    // 查询总记录数
    Long recordsTotal = memberRepository.count();
    // 映射请求参数
    Map<String, Object> params = DataTableUtils.parseMap(request);
    // 查询分页数据
    Page<MemberVo> page = memberRepository.find((PageRequest) params.get("pageRequest"), (Map<String, Object>) params.get("filter"));
    // 映射成定制模型
    DataTableModel model = DataTableUtils.parseDataTableModel(page, (Integer) params.get("draw"), recordsTotal);
    return model;
}


@Override
public Member findMemberBymemberNo(String memberNo){
    return memberRepository.findMemberBymemberNo(memberNo);
}


}