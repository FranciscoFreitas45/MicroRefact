package run.halo.app.event.user;
 import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
public class UserUpdatedEvent extends ApplicationEvent{

 private  Integer userId;

/**
 * Create a new ApplicationEvent.
 *
 * @param source the object on which the event initially occurred (never {@code null})
 * @param userId user id must not be null
 */
public UserUpdatedEvent(Object source, @NonNull Integer userId) {
    super(source);
    Assert.notNull(userId, "User id must not be null");
    this.userId = userId;
}
@NonNull
public Integer getUserId(){
    return userId;
}


}