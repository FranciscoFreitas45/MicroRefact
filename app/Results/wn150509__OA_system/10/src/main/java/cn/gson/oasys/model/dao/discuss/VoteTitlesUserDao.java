package cn.gson.oasys.model.dao.discuss;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import cn.gson.oasys.model.entity.discuss.VoteTitleUser;
import cn.gson.oasys.model.entity.discuss.VoteTitles;
import cn.gson.oasys.model.entity.user.User;
public interface VoteTitlesUserDao extends JpaRepository<VoteTitleUser, Long>{


public VoteTitleUser findByVoteTitlesAndUser(VoteTitles voteTitles,User user)
;

public List<VoteTitleUser> findByVoteId(Long voteId)
;

public List<VoteTitleUser> findByVoteTitles(VoteTitles voteTitles)
;

}