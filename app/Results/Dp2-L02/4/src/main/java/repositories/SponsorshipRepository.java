package repositories;
 import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import domain.Sponsor;
import domain.Sponsorship;
@Repository
public interface SponsorshipRepository extends JpaRepository<Sponsorship, Integer>{


@Query("select s from Sponsor spo join spo.sponsorships s where spo.id = ?1 and s.isActivated = true")
public Collection<Sponsorship> getActivatedSponsorshipsOfSponsor(int sponsorId)
;

@Query("select s from Sponsorship s")
public Collection<Sponsorship> getAllSponsorshipsAsAdmin()
;

@Query("select s from Sponsorship s where s.isActivated = false")
public Collection<Sponsorship> getDeactivatedSponsorshipsAsAdmin()
;

@Query("select s from Sponsor spo join spo.sponsorships s where spo.id = ?1 and s.isActivated = false")
public Collection<Sponsorship> getDeactivatedSponsorshipsOfSponsor(int sponsorId)
;

@Query("select s from Sponsor spo join spo.sponsorships s where spo.id = ?1")
public Collection<Sponsorship> getAllSponsorshipsOfSponsor(int sponsorId)
;

@Query("select s from Sponsorship s join s.parade p where p.id = ?1")
public List<Sponsorship> getSponsorshipsOfParade(int paradeId)
;

@Query("select s from Sponsorship s where s.isActivated = true")
public Collection<Sponsorship> getActivatedSponsorshipsAsAdmin()
;

@Query("select s from Sponsorship s join s.parade p where p.id = ?1 and s.isActivated = true")
public List<Sponsorship> getActivatedSponsorshipsOfParade(int paradeId)
;

@Query("select spo from Sponsor spo join spo.sponsorships s where s.id = ?1")
public Sponsor getSponsorOfSponsorship(int sponsorshipId)
;

}