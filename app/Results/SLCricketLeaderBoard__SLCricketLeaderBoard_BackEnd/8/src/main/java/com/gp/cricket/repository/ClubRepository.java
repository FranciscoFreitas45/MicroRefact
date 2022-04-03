package com.gp.cricket.repository;
 import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.gp.cricket.entity.Club;
public interface ClubRepository extends JpaRepository<Club, Integer>{


@Query("FROM Club c WHERE (c.clubName = :clubName OR c.email = :email OR c.contactNumber = :contactNum OR address = :address) AND (c.clubId NOT IN (:clubId) )")
public Club findClubByNotExistClubId(String clubName,String email,String contactNum,String address,Integer clubId)
;

@Query("FROM Club c WHERE c.clubName = :clubName OR c.email = :email OR c.contactNumber = :contactNum OR address = :address")
public Club findClub(String clubName,String email,String contactNum,String address)
;

@Query("FROM Club c WHERE c.clubId = :clubId")
public Club findClubByClubId(Integer clubId)
;

@Query("FROM Club c WHERE c.managerId.managerId = :managerId")
public Club findClubByManagerId(Integer managerId)
;

@Query("FROM Club c WHERE c.managerId.userId.userId = :userId")
public Club findClubByUserId(Integer userId)
;

@Query("FROM Club c WHERE c.status = :status AND c.managerId.userId.status = 1")
public List<Club> findByClubStatus(Byte status)
;

public Club getClubId(Integer clubIdv2);

public void setClubId(Integer clubIdv2,Club clubId);

public Club getClubId(Integer clubIdv2);

public void setClubId(Integer clubIdv2,Club clubId);

public void setClubId(Integer clubIdv2,Club clubId);

public Club getClubId(Integer clubIdv2);

public void setClubId(Integer clubIdv2,Club clubId);

public Club getClubId(Integer clubIdv2);

}