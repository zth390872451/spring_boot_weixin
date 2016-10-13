package root.intefaceTest;

import org.springframework.context.ApplicationEvent;

/**
 * Created by Administrator on 2016/10/13.
 */
public class ApplicationEventTest extends ApplicationEvent{
    private String eventMsg = "";
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public ApplicationEventTest(Object source) {
        super(source);
        eventMsg = "eventMsg";
    }

    public String getEventMsg() {
        return eventMsg;
    }

    public void setEventMsg(String eventMsg) {
        this.eventMsg = eventMsg;
    }
}
