import org.omnifaces.cdi.Push;
import org.omnifaces.cdi.PushContext;
import org.omnifaces.cdi.ViewScoped;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ViewScoped
public class PushBean implements Serializable {
    private static AtomicLong counter = new AtomicLong();

    private boolean connected;

    @Inject
    @Push(channel = "counter")
    private PushContext push;

    public void toggle() {
        connected = !connected;
    }

    public void increment() {
        long newvalue = counter.incrementAndGet();
        push.send(newvalue);
    }

    public boolean isConnected() {
        return connected;
    }

    public Long getCount() {
        return counter.get();
    }
}
