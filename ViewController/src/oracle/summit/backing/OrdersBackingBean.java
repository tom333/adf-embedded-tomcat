package oracle.summit.backing;

import org.apache.myfaces.trinidad.context.RequestContext;
import org.apache.myfaces.trinidad.event.ReturnEvent;


public class OrdersBackingBean {
    public OrdersBackingBean() {
    }

    public void onShuttleTaskFlowReturn(ReturnEvent returnEvent)
    {
        RequestContext.getCurrentInstance().addPartialTarget(returnEvent.getComponent().getParent().getParent());
    }
}
