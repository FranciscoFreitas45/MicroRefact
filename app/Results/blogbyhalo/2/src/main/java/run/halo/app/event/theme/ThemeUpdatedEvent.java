package run.halo.app.event.theme;
 import org.springframework.context.ApplicationEvent;
public class ThemeUpdatedEvent extends ApplicationEvent{

/**
 * Create a new ApplicationEvent.
 *
 * @param source the object on which the event initially occurred (never {@code null})
 */
public ThemeUpdatedEvent(Object source) {
    super(source);
}
}