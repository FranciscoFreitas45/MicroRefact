package main.repository;
 import main.model.GlobalSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface GlobalSettingsRepository extends JpaRepository<GlobalSetting, Integer>{


@Query(value = "select value from global_settings where code = " + "'POST_PREMODERATION'", nativeQuery = true)
public String postPremoderation()
;

@Query(value = "select value from global_settings where code = " + "'MULTIUSER_MODE'", nativeQuery = true)
public String multiUser()
;

@Query(value = "select value from global_settings where code = " + "'STATISTICS_IS_PUBLIC'", nativeQuery = true)
public String statisticsIsPublic()
;

}