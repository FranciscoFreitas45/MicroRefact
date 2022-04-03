package goorum.goorum.service;
 import goorum.goorum.domain.Member;
import goorum.goorum.domain.MemberLikeBoard;
import java.util.List;
public interface MemberLikeBoardService {


public boolean isLike(long boardId,long memberId)
;

public boolean like(long memberId,long boardId)
;

public void dislike(long memberId,long boardId)
;

public List<MemberLikeBoard> likeBoard(long memberId)
;

}