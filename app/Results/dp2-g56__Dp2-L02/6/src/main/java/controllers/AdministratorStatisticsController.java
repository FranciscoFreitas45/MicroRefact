package controllers;
 import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import domain.Brotherhood;
import domain.Chapter;
import domain.Member;
import domain.Position;
import services.AdminService;
@Controller
@RequestMapping("/statistics/administrator")
public class AdministratorStatisticsController extends AbstractController{

@Autowired
 private  AdminService adminService;

// Constructors -----------------------------------------------------------
public AdministratorStatisticsController() {
    super();
}
@RequestMapping(value = "/show", method = RequestMethod.GET)
public ModelAndView statistics(){
    ModelAndView result;
    String locale = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
    List<Float> statistics = this.adminService.showStatistics();
    List<String> top5Sponsor = this.adminService.top5SponsorNumberActiveSponsorships();
    List<Chapter> chaptersThatCoordinateAtLeast = this.adminService.chaptersThatCoordinateAtLeast();
    List<String> broLargestHistory = this.adminService.broLargestHistory();
    List<Brotherhood> broHistoryLargerThanAvg = this.adminService.broHistoryLargerThanAvg();
    List<Member> membersTenPercent = this.adminService.membersAtLeastTenPercentRequestsApproved();
    List<String> largestBrotherhoods = this.adminService.largestBrotherhoods();
    List<String> smallestBrotherhoods = this.adminService.smallestBrotherhoods();
    List<String> paradesOfNextMonth = this.adminService.paradesOfNextMonth();
    Map<String, Float> countBrotherhoodsPerArea = this.adminService.countBrotherhoodsArea();
    Map<String, Float> ratioBrotherhoodsPerArea = this.adminService.ratioBrotherhoodPerArea();
    Set<String> areaNames = ratioBrotherhoodsPerArea.keySet();
    Map<String, Float> ratioRequestApprovedByParade = this.adminService.ratioRequestApprovedByParade();
    Map<String, Float> ratioRequestPendingByParade = this.adminService.ratioRequestPendingByParade();
    Map<String, Float> ratioRequestRejectedByParade = this.adminService.ratioRequestRejectedByParade();
    Set<String> paradeNames = ratioRequestRejectedByParade.keySet();
    Map<Position, Float> countPositions = this.adminService.mapNumberPositions();
    Set<Position> positions = countPositions.keySet();
    result = new ModelAndView("statistics/administrator/show");
    result.addObject("statistics", statistics);
    result.addObject("top5Sponsor", top5Sponsor);
    result.addObject("chaptersThatCoordinateAtLeast", chaptersThatCoordinateAtLeast);
    result.addObject("broLargestHistory", broLargestHistory);
    result.addObject("broHistoryLargerThanAvg", broHistoryLargerThanAvg);
    result.addObject("membersTenPercent", membersTenPercent);
    result.addObject("largestBrotherhoods", largestBrotherhoods);
    result.addObject("smallestBrotherhoods", smallestBrotherhoods);
    result.addObject("paradesOfNextMonth", paradesOfNextMonth);
    result.addObject("countBrotherhoodsPerArea", countBrotherhoodsPerArea);
    result.addObject("ratioBrotherhoodsPerArea", ratioBrotherhoodsPerArea);
    result.addObject("ratioRequestApprovedByParade", ratioRequestApprovedByParade);
    result.addObject("ratioRequestPendingByParade", ratioRequestPendingByParade);
    result.addObject("ratioRequestRejectedByParade", ratioRequestRejectedByParade);
    result.addObject("paradeNames", paradeNames);
    result.addObject("countPositions", countPositions);
    result.addObject("locale", locale);
    result.addObject("positions", positions);
    result.addObject("areaNames", areaNames);
    return result;
}


}