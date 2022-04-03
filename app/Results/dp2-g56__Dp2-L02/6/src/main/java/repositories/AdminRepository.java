package repositories;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Admin;
import domain.Brotherhood;
import domain.Chapter;
import domain.Member;
import domain.Sponsor;
@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{


@Query("select (cast((select count(a) from Brotherhood a where a.area = b) as float)/(select count(c) from Area c)) from Area b")
public List<Float> ratioBrotherhoodPerArea()
;

@Query("select avg(b.parades.size) from Brotherhood b, Chapter c where c.area = b.area")
public Float avgParadesCoordinated()
;

@Query("select a from Admin a join a.userAccount u where u.username = 'system'")
public Admin getSystem()
;

@Query("select min(cast((select count(a) from Sponsorship a where a member of b.sponsorships and a.isActivated = true) as float)) from Sponsor b")
public Float minSponsorshipsPerSponsor()
;

@Query("select m from Admin m join m.userAccount u where u.username = ?1")
public Admin getAdminByUsername(String username)
;

@Query("select max(cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float)) from Brotherhood a")
public Float maxNumberMembersPerBrotherhood()
;

@Query("select avg(cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float)) from Brotherhood a")
public Float avgMembersPerBrotherhood()
;

@Query("select b from Brotherhood b join b.history h where (1+h.legalRecords.size+h.linkRecords.size+h.periodRecords.size+h.miscellaneousRecords.size) > (select avg(1+i.legalRecords.size+i.linkRecords.size+i.periodRecords.size+i.miscellaneousRecords.size) from Brotherhood a join a.history i)")
public List<Brotherhood> broHistoryLargerThanAvg()
;

@Query("select avg(cast((select count(a) from Sponsorship a where a member of b.sponsorships and a.isActivated = true) as float)) from Sponsor b")
public Float avgSponsorshipsPerSponsor()
;

@Query("select avg(a.polarity) from Brotherhood a")
public Float avgBrotherhoodPolarity()
;

@Query("select max(cast((select count(a) from Sponsorship a where a member of b.sponsorships and a.isActivated = true) as float)) from Sponsor b")
public Float maxSponsorshipsPerSponsor()
;

@Query("select max(b.parades.size) from Brotherhood b, Chapter c where c.area = b.area")
public Float maxParadesCoordinated()
;

@Query("select (cast((select count(a1.status) from Request a1 where status='PENDING' and a1.parade = a) as float)/ (a.requests.size) * 100) from Parade a")
public List<Float> ratioPendingRequestsByParades()
;

@Query("select  a from Member a where cast((select count(b) from Request b where b.status = 'APPROVED' and b.member = a) as float) >= (cast((select count(b) from Request b where b.member = a) as float) * 0.1) and (select count(b) from Request b where b.member = a) > 0")
public List<Member> listMembersAtLeastTenPercentRequestApproved()
;

@Query("select distinct (cast((select count(a1.paradeStatus) from Parade a1 where paradeStatus='REJECTED') as float)/ (select count(a2) from Parade a2 where a2.isDraftMode = false) * 100) from Configuration a")
public Float ratioParadesRejectedRequests()
;

@Query("select avg(a.polarity) from Member a")
public Float avgMemberPolarity()
;

@Query("select min(b.parades.size) from Brotherhood b, Chapter c where c.area = b.area")
public Float minParadesCoordinated()
;

@Query("select cast((select count(b) from Enrolment b where b.position = a and b.statusEnrolment = 'ACCEPTED') as float) from Position a")
public List<Float> numberPositions()
;

@Query("select distinct(cast((select count(p) from Parade p where p.isDraftMode = true) as float)/(select count(u) from Parade u where u.isDraftMode = false)*100) from Configuration d")
public Float paradesDraftVSFinal()
;

@Query("select (cast((select count(a) from Finder a where a.parades.size = 0) as float)/(select count(c) from Finder c where c.parades.size > 0)) from Configuration b")
public Float ratioEmptyFinder()
;

@Query("select stddev(cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float)) from Brotherhood a")
public Float stddevMembersPerBrotherhood()
;

@Query("select cast((select count(a) from Brotherhood a where a.area = b) as float) from Area b")
public List<Float> numberBrotherhoodsPerArea()
;

@Query("select min(1+h.legalRecords.size+h.linkRecords.size+h.periodRecords.size+h.miscellaneousRecords.size) from Brotherhood b join b.history h")
public Float minNumberRecordsPerHistory()
;

@Query("select avg(a.parades.size) from Finder a")
public Float avgResultFinders()
;

@Query("select stddev(b.parades.size) from Brotherhood b, Chapter c where c.area = b.area")
public Float stddevParadesCoordinated()
;

@Query("select cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float) from Brotherhood a")
public List<Float> listNumberMembersPerBrotherhood()
;

@Query("select max(cast((select count(a) from Brotherhood a where a.area = b) as float)) from Area b")
public Float maxNumberBrotherhoodPerArea()
;

@Query("select a.title from Brotherhood a where cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float) = (select max(cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float)) from Brotherhood a)")
public List<String> largestBrotherhoods()
;

@Query("select distinct(s) from Sponsor s order by (cast((select count(a) from Sponsorship a where a member of s.sponsorships and a.isActivated = true) as float)) desc")
public List<Sponsor> top5SponsorNumberActiveSponsorships()
;

@Query("select ((select count(a) from Area a where a not in (select c.area from Chapter c where c.area is not null))/(select count(b) from Area b)*100) from Configuration d")
public Float ratioAreasNotCoordinated()
;

@Query("select min(cast((select count(a) from Brotherhood a where a.area = b) as float)) from Area b")
public Float minNumberBrotherhoodPerArea()
;

@Query("select distinct (cast((select count(a1.status) from Request a1 where status='PENDING') as float)/ (select count(a2) from Request a2) * 100) from Configuration a")
public Float ratioPendingRequests()
;

@Query("select distinct (cast((select count(a1.paradeStatus) from Parade a1 where paradeStatus='ACCEPTED') as float)/ (select count(a2) from Parade a2 where a2.isDraftMode = false) * 100) from Configuration a")
public Float ratioParadesAcceptedRequests()
;

@Query("select (cast((select count(a1.status) from Request a1 where status='APPROVED' and a1.parade = a) as float)/ (a.requests.size) * 100) from Parade a")
public List<Float> ratioApprovedRequestsByParades()
;

@Query("select avg(a.polarity) from Admin a")
public Float avgAdminPolarity()
;

@Query("select stddev(1+h.legalRecords.size+h.linkRecords.size+h.periodRecords.size+h.miscellaneousRecords.size) from Brotherhood b join b.history h")
public Float stddevRecordsPerHistory()
;

@Query("select avg(1+h.legalRecords.size+h.linkRecords.size+h.periodRecords.size+h.miscellaneousRecords.size) from Brotherhood b join b.history h")
public Float avgRecordsPerHistory()
;

@Query("select a.title from Brotherhood a where cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float) = (select min(cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float)) from Brotherhood a)")
public List<String> smallestBrotherhoods()
;

@Query("select stddev(a.parades.size) from Finder a")
public Float stddevResultFinders()
;

@Query("select distinct (cast((select count(a1.status) from Request a1 where status='APPROVED') as float)/ (select count(a2) from Request a2) * 100) from Configuration a")
public Float ratioApprovedRequests()
;

@Query("select b from Brotherhood b join b.history h where (1+h.legalRecords.size+h.linkRecords.size+h.periodRecords.size+h.miscellaneousRecords.size) = (select max(1+i.legalRecords.size+i.linkRecords.size+i.periodRecords.size+i.miscellaneousRecords.size) from Brotherhood a join a.history i)")
public List<Brotherhood> broLargestHistory()
;

@Query("select distinct (cast((select count(a1.status) from Request a1 where status='REJECTED') as float)/ (select count(a2) from Request a2) * 100) from Configuration a")
public Float ratioRejectedRequests()
;

@Query("select distinct (cast((select count(a1.hasSpam) from Actor a1 where hasSpam=false) as float)/ (select count(a2.hasSpam) from Actor a2) * 100) from Configuration a")
public Float getRatioNonSpammers()
;

@Query("select c from Chapter c, Brotherhood b where (c.area = b.area) and (b.parades.size > (select avg(b.parades.size) from Brotherhood b, Chapter c where c.area = b.area)*1.1)")
public List<Chapter> chaptersThatCoordinateAtLeast()
;

@Query("select distinct (cast((select count(a1) from Sponsorship a1 where isActivated = true) as float)/ (select count(a2) from Sponsorship a2) * 100) from Configuration a")
public Float ratioActiveSponsorships()
;

@Query("select a.title from Parade a where a.isDraftMode = false and a.moment between (NOW()) and (select (NOW() + 30000000) from Configuration c)")
public List<String> listParadeNext30Days()
;

@Query("select stddev(cast((select count(a) from Brotherhood a where a.area = b) as float)) from Area b")
public Float stddevNumberBrotherhoodPerArea()
;

@Query("select count(c) from Finder c where c.parades.size > 0")
public Integer numberNonEmptyFinders()
;

@Query("select (cast((select count(a1.status) from Request a1 where status='REJECTED' and a1.parade = a) as float)/ (a.requests.size) * 100) from Parade a")
public List<Float> ratioRejectedRequestsByParades()
;

@Query("select max(1+h.legalRecords.size+h.linkRecords.size+h.periodRecords.size+h.miscellaneousRecords.size) from Brotherhood b join b.history h")
public Float maxNumberRecordsPerHistory()
;

@Query("select min(a.parades.size) from Finder a")
public Float minResultFinders()
;

@Query("select max(a.parades.size) from Finder a")
public Float maxResultFinders()
;

@Query("select distinct (cast((select count(a1.paradeStatus) from Parade a1 where paradeStatus='SUBMITTED' and a1.isDraftMode = false) as float)/ (select count(a2) from Parade a2 where a2.isDraftMode = false) * 100) from Configuration a")
public Float ratioParadesSubmittedRequests()
;

@Query("select stddev(cast((select count(a) from Sponsorship a where a member of b.sponsorships and a.isActivated = true) as float)) from Sponsor b")
public Float stddevSponsorshipsPerSponsor()
;

@Query("select min(cast((select count(b) from Enrolment b where b.brotherhood = a AND b.statusEnrolment = 'ACCEPTED') as float)) from Brotherhood a")
public Float minNumberMembersPerBrotherhood()
;

@Query("select distinct (cast((select count(a1.hasSpam) from Actor a1 where hasSpam=true) as float)/ (select count(a2.hasSpam) from Actor a2) * 100) from Configuration a")
public Float getRatioSpammers()
;

@Query("select avg(cast((select count(a) from Brotherhood a where a.area = b) as float)) from Area b")
public Float avgNumberBrotherhoodPerArea()
;

}