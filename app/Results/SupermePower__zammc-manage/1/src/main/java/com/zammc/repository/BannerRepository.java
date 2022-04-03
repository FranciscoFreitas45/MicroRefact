package com.zammc.repository;
 import com.zammc.domain.banner.BannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface BannerRepository extends JpaSpecificationExecutor<BannerEntity>, JpaRepository<BannerEntity, Long>{


}