package run.halo.app.event.theme;
 import org.springframework.context.ApplicationEvent;
public class ThemeActivatedEvent extends ApplicationEvent{

/**
 * Creates a new ApplicationEvent.
 *
 * @param source the object on which the event initially occurred (never {@code null})
 */
public ThemeActivatedEvent(Object source) {
    super(source);
}
}