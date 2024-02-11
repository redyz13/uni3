package ejb;

import javax.enterprise.event.Event;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/javaee7/Topic", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
})
public class VinoMDB implements MessageListener {
    @Inject @Bottle
    private Event<Vino> onBottleNumberUpdate;
    
    @Inject @Purchase
    private Event<Vino> onPurchaseUpdate;
    
    @Inject
    private VinoEJB vinoEJB;
    
    public VinoMDB() {}
    
    public void onMessage(Message message) {
        try {
            Vino vino = message.getBody(Vino.class);
            vinoEJB.updateBottleNumber(vino.getId());
            onBottleNumberUpdate.fire(vino);
            
            if (vino.getBottiglieDisponibili() < 10 && !vino.isAcquisto()) {
                vinoEJB.updatePurchase(vino.getId());
                onPurchaseUpdate.fire(vino);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
