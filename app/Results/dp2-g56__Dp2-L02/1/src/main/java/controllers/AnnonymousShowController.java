package controllers;
 import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import domain.Brotherhood;
import domain.Chapter;
import domain.InceptionRecord;
import domain.LegalRecord;
import domain.LinkRecord;
import domain.Member;
import domain.MiscellaneousRecord;
import domain.Parade;
import domain.PeriodRecord;
import domain.Proclaim;
import domain.Segment;
import domain.Sponsorship;
import services.BrotherhoodService;
import services.ChapterService;
import services.FloatService;
import services.InceptionRecordService;
import services.LegalRecordService;
import services.PeriodRecordService;
import services.SegmentService;
import services.SponsorshipService;
import Interface.BrotherhoodService;
import Interface.FloatService;
import Interface.SponsorshipService;
import Interface.ChapterService;
import Interface.SegmentService;
import DTO.BrotherhoodService;
import DTO.ChapterService;
import DTO.FloatService;
import DTO.SegmentService;
import DTO.SponsorshipService;
@Controller
@RequestMapping("/showAll/annonymous")
public class AnnonymousShowController extends AbstractController{

@Autowired
 private  BrotherhoodService brotherhoodService;

@Autowired
 private  FloatService floatService;

@Autowired
 private  LegalRecordService legalRecordService;

@Autowired
 private  InceptionRecordService inceptionRecordService;

@Autowired
 private  PeriodRecordService periodRecordService;

@Autowired
 private  SponsorshipService sponsorshipService;

@Autowired
 private  ChapterService chapterService;

@Autowired
 private  SegmentService segmentService;


@RequestMapping(value = "brotherhood/listByArea", method = RequestMethod.GET)
public ModelAndView chpaterListAnon(Integer areaId){
    ModelAndView result;
    List<Brotherhood> brotherhoods = this.brotherhoodService.getBrotherhoodsByArea(areaId);
    result = new ModelAndView("showAll/annonymous/brotherhood/list");
    result.addObject("requestURI", "showAll/annonymous/brotherhood/listByArea.do");
    result.addObject("brotherhoods", brotherhoods);
    return result;
}


@RequestMapping(value = "/brotherhood/list", method = RequestMethod.GET)
public ModelAndView listBrotherhood(Integer brotherhoodId){
    ModelAndView result;
    List<Brotherhood> brotherhoods = new ArrayList<Brotherhood>();
    boolean cancelButton = false;
    result = new ModelAndView("showAll/annonymous/brotherhood/list");
    if (brotherhoodId == null)
        brotherhoods = this.brotherhoodService.findAll();
    else {
        Brotherhood bro = this.brotherhoodService.findOne(brotherhoodId);
        if (bro == null) {
            brotherhoods = this.brotherhoodService.findAll();
            result.addObject("res", true);
        } else {
            brotherhoods.add(bro);
            cancelButton = true;
        }
    }
    result.addObject("brotherhoods", brotherhoods);
    result.addObject("requestURI", "showAll/annonymous/brotherhood/list.do");
    result.addObject("cancelButton", cancelButton);
    return result;
}


@RequestMapping(value = "proclaim/list", method = RequestMethod.GET)
public ModelAndView proclaimListAnon(Integer chapterId){
    ModelAndView result;
    List<Proclaim> proclaims = this.chapterService.findOne(chapterId).getProclaims();
    result = new ModelAndView("showAll/annonymous/proclaim/list");
    result.addObject("requestURI", "showAll/annonymous/proclaim/list.do");
    result.addObject("proclaims", proclaims);
    return result;
}


@RequestMapping(value = "/picture/list", method = RequestMethod.GET)
public ModelAndView listPicturesFloat(int floatId){
    ModelAndView result;
    List<String> pictures;
    domain.Float floatt;
    floatt = this.floatService.findOne(floatId);
    Assert.notNull(floatt);
    pictures = floatt.getPictures();
    result = new ModelAndView("showAll/annonymous/picturesFloat");
    result.addObject("pictures", pictures);
    result.addObject("requestURI", "showAll/annonymous/picture/list.do");
    result.addObject("floatId", floatId);
    return result;
}


@RequestMapping(value = "/history/periodPhotos/list", method = RequestMethod.GET)
public ModelAndView listPhotosPeriodRecord(int periodRecordId){
    ModelAndView result;
    List<String> photos;
    PeriodRecord periodRecord = this.periodRecordService.findOne(periodRecordId);
    Assert.notNull(periodRecord);
    int brotherhoodId = this.brotherhoodService.getBrotherhoodIdByPeriodRecord(periodRecordId);
    photos = periodRecord.getPhotos();
    result = new ModelAndView("showAll/annonymous/history/photos/list");
    result.addObject("photos", photos);
    result.addObject("requestURI", "showAll/annonymous/history/periodPhotos/list.do");
    result.addObject("periodRecordId", periodRecordId);
    result.addObject("brotherhoodId", brotherhoodId);
    return result;
}


@RequestMapping(value = "/history/list", method = RequestMethod.GET)
public ModelAndView listHistory(int brotherhoodId){
    ModelAndView result;
    Brotherhood brotherhood = new Brotherhood();
    result = new ModelAndView("showAll/annonymous/history/list");
    brotherhood = this.brotherhoodService.findOne(brotherhoodId);
    Boolean showHistory = false;
    if (!(brotherhood.getHistory() == null)) {
        // History
        List<LinkRecord> linkRecords = new ArrayList<>();
        linkRecords = brotherhood.getHistory().getLinkRecords();
        List<MiscellaneousRecord> miscellaneousRecords = new ArrayList<>();
        miscellaneousRecords = brotherhood.getHistory().getMiscellaneousRecords();
        List<LegalRecord> legalRecords = new ArrayList<>();
        legalRecords = brotherhood.getHistory().getLegalRecords();
        List<PeriodRecord> periodRecords = new ArrayList<>();
        periodRecords = brotherhood.getHistory().getPeriodRecords();
        InceptionRecord inceptionRecord = brotherhood.getHistory().getInceptionRecord();
        List<InceptionRecord> inceptionRecords = new ArrayList<>();
        inceptionRecords.add(inceptionRecord);
        result.addObject("linkRecords", linkRecords);
        result.addObject("miscellaneousRecords", miscellaneousRecords);
        result.addObject("legalRecords", legalRecords);
        result.addObject("periodRecords", periodRecords);
        result.addObject("inceptionRecords", inceptionRecords);
        showHistory = true;
    }
    result.addObject("showHistory", showHistory);
    result.addObject("requestURI", "showAll/annonymous/history/list.do");
    return result;
}


@RequestMapping(value = "/history/inceptionPhotos/list", method = RequestMethod.GET)
public ModelAndView listPhotosInceptionRecord(int inceptionRecordId){
    ModelAndView result;
    List<String> photos;
    InceptionRecord inceptionRecord = this.inceptionRecordService.findOne(inceptionRecordId);
    Assert.notNull(inceptionRecord);
    int brotherhoodId = this.brotherhoodService.getBrotherhoodIdByInceptionRecord(inceptionRecordId);
    photos = inceptionRecord.getPhotos();
    result = new ModelAndView("showAll/annonymous/history/photos/list");
    result.addObject("photos", photos);
    result.addObject("requestURI", "showAll/annonymous/history/inceptionPhotos/list.do");
    result.addObject("inceptionRecordId", inceptionRecordId);
    result.addObject("brotherhoodId", brotherhoodId);
    return result;
}


@RequestMapping(value = "path/list", method = RequestMethod.GET)
public ModelAndView pathListAnon(Integer paradeId){
    ModelAndView result;
    List<Segment> segments = this.segmentService.getSegmentByParade(paradeId);
    result = new ModelAndView("path/anon/list");
    result.addObject("requestURI", "showAll/annonymous/path/list.do");
    result.addObject("segments", segments);
    return result;
}


@RequestMapping(value = "/member/list", method = RequestMethod.GET)
public ModelAndView listMember(int brotherhoodId){
    ModelAndView result;
    Brotherhood brotherhood = new Brotherhood();
    List<Member> members = new ArrayList<Member>();
    brotherhood = this.brotherhoodService.findOne(brotherhoodId);
    members = this.brotherhoodService.getMembersOfBrotherhood(brotherhood);
    result = new ModelAndView("showAll/annonymous/member/list");
    result.addObject("members", members);
    result.addObject("requestURI", "showAll/annonymous/member/list.do");
    return result;
}


@RequestMapping(value = "/float/list", method = RequestMethod.GET)
public ModelAndView listFloat(int brotherhoodId){
    ModelAndView result;
    Brotherhood brotherhood = new Brotherhood();
    List<domain.Float> floats = new ArrayList<domain.Float>();
    brotherhood = this.brotherhoodService.findOne(brotherhoodId);
    floats = this.brotherhoodService.getFloatsByBrotherhood(brotherhood);
    result = new ModelAndView("showAll/annonymous/float/list");
    result.addObject("floats", floats);
    result.addObject("requestURI", "showAll/annonymous/float/list.do");
    return result;
}


@RequestMapping(value = "/pictureBrother/list", method = RequestMethod.GET)
public ModelAndView listPicturesBrotherhood(int brotherhoodId){
    ModelAndView result;
    List<String> pictures;
    Brotherhood brotherhoood;
    brotherhoood = this.brotherhoodService.findOne(brotherhoodId);
    Assert.notNull(brotherhoood);
    pictures = brotherhoood.getPictures();
    result = new ModelAndView("showAll/annonymous/picturesBrotherhood");
    result.addObject("picturesBrotherhood", pictures);
    result.addObject("requestURI", "showAll/annonymous/pictureBrother/list.do");
    result.addObject("brotherhoodId", brotherhoodId);
    return result;
}


@RequestMapping(value = "/parade/list", method = RequestMethod.GET)
public ModelAndView listParades(int brotherhoodId){
    ModelAndView result;
    Brotherhood brotherhood = new Brotherhood();
    List<Parade> parades = new ArrayList<Parade>();
    brotherhood = this.brotherhoodService.findOne(brotherhoodId);
    parades = this.brotherhoodService.getParadesByBrotherhoodFinal(brotherhood);
    result = new ModelAndView("showAll/annonymous/parade/list");
    Map<Integer, Sponsorship> randomSpo = new HashMap<Integer, Sponsorship>();
    for (Parade p : parades) {
        Sponsorship spo = this.sponsorshipService.getRandomSponsorship(p.getId());
        randomSpo.put(p.getId(), spo);
        if (spo.getId() > 0)
            this.sponsorshipService.updateSpentMoneyOfSponsorship(p.getId(), spo.getId());
    }
    result.addObject("randomSpo", randomSpo);
    result.addObject("parades", parades);
    result.addObject("requestURI", "showAll/annonymous/parade/list.do");
    return result;
}


@RequestMapping(value = "/history/law/list", method = RequestMethod.GET)
public ModelAndView listLawsLegalRecord(int legalRecordId){
    ModelAndView result;
    List<String> laws;
    LegalRecord legalRecord = this.legalRecordService.findOne(legalRecordId);
    Assert.notNull(legalRecord);
    int brotherhoodId = this.brotherhoodService.getBrotherhoodIdByLegalRecord(legalRecordId);
    laws = legalRecord.getLaws();
    result = new ModelAndView("showAll/annonymous/history/law/list");
    result.addObject("laws", laws);
    result.addObject("requestURI", "showAll/annonymous/history/law/list.do");
    result.addObject("legalRecordId", legalRecordId);
    result.addObject("brotherhoodId", brotherhoodId);
    return result;
}


}