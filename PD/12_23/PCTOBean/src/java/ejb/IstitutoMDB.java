package ejb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "jms/javaee7/Topic", activationConfig = {
    @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
})
public class IstitutoMDB implements MessageListener {
    @Inject @Studenti
    private Event<Istituto> onUpdateNumStudenti;
    
    @Inject @GoldCore
    private Event<Istituto> onUpdateGoldCore;
    
    @Inject
    private IstitutoEJB istitutoEJB;
    
    public IstitutoMDB() {}
    
    @Override
    public void onMessage(Message message) {
        try {
            Istituto istituto = message.getBody(Istituto.class);
            istitutoEJB.updateNumeroStudenti(istituto.getId());
            onUpdateNumStudenti.fire(istituto);
            
            if (istituto.getStudentiAmmessi() > 40) {
                istitutoEJB.updateGoldCore(istituto.getId());
                onUpdateGoldCore.fire(istituto);
            }
        } catch (JMSException e) {
            e.printStackTrace();
            
        }
    }
}
