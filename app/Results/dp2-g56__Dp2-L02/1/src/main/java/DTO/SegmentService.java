package DTO;
 import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import domain.Brotherhood;
import domain.Parade;
import domain.ParadeStatus;
import domain.Path;
import domain.Segment;
import repositories.SegmentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import Interface.ParadeService;
import Interface.BrotherhoodService;
import Interface.ChapterService;
import Interface.ActorService;
public class SegmentService {

 private  SegmentRepository segmnentRepository;

 private  ParadeService paradeService;

 private  BrotherhoodService brotherhoodService;

 private  PathService pathService;

 private  ChapterService chapterService;

 private  ActorService actorService;

 private  Validator validator;


public List<Segment> getSegmentByParade(Integer paradeId){
    Boolean logguedBro = false;
    Boolean logguedChapter = false;
    Parade parade = this.paradeService.findOne(paradeId);
    if (this.actorService.loggedAsActorBoolean()) {
        UserAccount userAccount = LoginService.getPrincipal();
        List<Authority> auth = (List<Authority>) userAccount.getAuthorities();
        logguedBro = auth.get(0).toString().equals("BROTHERHOOD");
        logguedChapter = auth.get(0).toString().equals("CHAPTER");
    }
    if (parade.getIsDraftMode() && logguedBro)
        Assert.isTrue(this.brotherhoodService.loggedBrotherhood().getParades().contains(parade));
    else if (logguedChapter && (parade.getParadeStatus().equals(ParadeStatus.SUBMITTED) || parade.getParadeStatus().equals(ParadeStatus.REJECTED)))
        this.chapterService.paradeSecurity(parade);
    else if ((!parade.getIsDraftMode() && parade.getParadeStatus().equals(ParadeStatus.SUBMITTED)) || (!parade.getIsDraftMode() && parade.getParadeStatus().equals(ParadeStatus.REJECTED)))
        Assert.isTrue(this.brotherhoodService.loggedBrotherhood().getParades().contains(parade));
    else
        Assert.isTrue(parade.getParadeStatus().equals(ParadeStatus.ACCEPTED));
    return parade.getPath().getSegments();
}


}