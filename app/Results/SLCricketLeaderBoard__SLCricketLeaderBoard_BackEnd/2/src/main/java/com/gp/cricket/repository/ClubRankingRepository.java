package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.ClubRanking;
public interface ClubRankingRepository extends JpaRepository<ClubRanking, Integer>{


@Query("FROM ClubRanking c WHERE c.clubId.status = 1 AND c.matchTypeId.matchTypeId = :matchType ORDER BY c.rating DESC")
public List<ClubRanking> findByMatchType(Integer matchType)
;

@Query("FROM ClubRanking c WHERE c.clubId.clubId = :clubId AND c.matchTypeId.matchTypeId = :matchType")
public ClubRanking findByClubIdANDMatchType(Integer clubId,Integer matchType)
;

}