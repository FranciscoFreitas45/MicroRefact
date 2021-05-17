import com.ats.hrmgt.model.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import javax.transaction.Transactional;
import java.util.List;
public interface SettingRepo extends JpaRepository<Setting, Integer> {


public List<Setting> findByGroup(String string)


public Setting findBySettingId(int settingId)


public Setting findByKey(String key)


@Query(value = "SELECT\n" + "        * \n" + "    FROM\n" + "       m_setting\n" + "    WHERE\n" + "        m_setting.editable=1 \n" + "    ORDER BY m_setting.group ASC", nativeQuery = true)
public List<Setting> findAllByEditableLabels()


@Transactional
@Modifying
@Query("update Setting set value=:val  WHERE setting_id=:settingId")
public int settingUpdate(String settingId,String val)


}