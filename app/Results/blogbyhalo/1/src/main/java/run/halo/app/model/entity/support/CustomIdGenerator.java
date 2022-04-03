package run.halo.app.model.entity.support;
 import java.io.Serializable;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import run.halo.app.utils.ReflectionUtils;
public class CustomIdGenerator extends IdentityGenerator{


@Override
public Serializable generate(SharedSessionContractImplementor session,Object object){
    Object id = ReflectionUtils.getFieldValue("id", object);
    if (id != null) {
        return (Serializable) id;
    }
    return super.generate(session, object);
}


}