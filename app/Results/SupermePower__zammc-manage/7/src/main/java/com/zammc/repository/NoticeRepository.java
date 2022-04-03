package com.zammc.repository;
 import com.zammc.domain.notice.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface NoticeRepository extends JpaSpecificationExecutor<NoticeEntity>, JpaRepository<NoticeEntity, Long>{


}