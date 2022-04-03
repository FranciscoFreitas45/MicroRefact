package goorum.goorum.service;
 import goorum.goorum.domain.Member;
import goorum.goorum.domain.MemberLikeBoard;
import goorum.goorum.repository.MemberLikeBoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Slf4j
@Service
public class MemberLikeBoardServiceImpl implements MemberLikeBoardService{

@Autowired
 private  MemberLikeBoardRepository memberLikeBoardRepository;


@Override
public boolean isLike(long boardId,long memberId){
    return memberLikeBoardRepository.findByBoardIdAndMemberId(boardId, memberId).isPresent();
}


@Override
public boolean like(long memberId,long boardId){
    MemberLikeBoard mlb = MemberLikeBoard.builder().memberId(memberId).boardId(boardId).build();
    try {
        memberLikeBoardRepository.save(mlb);
    } catch (Exception e) {
        log.error(e.toString());
        return false;
    }
    return true;
}


@Override
public void dislike(long memberId,long boardId){
    Optional<MemberLikeBoard> mlb = memberLikeBoardRepository.findByBoardIdAndMemberId(boardId, memberId);
    mlb.ifPresent(memberLikeBoardRepository::delete);
}


@Override
public List<MemberLikeBoard> likeBoard(long memberId){
    return memberLikeBoardRepository.findByMemberId(memberId);
}


}